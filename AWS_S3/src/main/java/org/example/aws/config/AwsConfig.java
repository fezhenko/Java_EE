package org.example.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsConfig {

    private final String s3Endpoint;
    private final String region;
    private final String key;
    private final String secret;

    public AwsConfig(
            @Value("${aws.endpoints.s3}") final String s3Endpoint,
            @Value("${aws.region}") final String region,
            @Value("${aws.credentials.access-key}") final String key,
            @Value("${aws.credentials.secret-key}") final String secret
    ) {
        this.s3Endpoint = s3Endpoint;
        this.region = region;
        this.key = key;
        this.secret = secret;
    }

    @Bean
    public AmazonS3 amazonS3() {
        AmazonS3ClientBuilder amazonS3Client = AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret)));
        amazonS3Client.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Endpoint, region));
        return amazonS3Client.build();
    }

}

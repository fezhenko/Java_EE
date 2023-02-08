package org.example.aws.service;

import com.amazonaws.services.s3.model.Bucket;
import lombok.RequiredArgsConstructor;
import org.example.aws.client.S3Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class S3BucketService {

    private final S3Client s3Client;


    public List<Bucket> findBuckets() {
        return s3Client.listBuckets();
    }

    public void createBucket(String bucketName) {
        s3Client.createBucket(bucketName);
    }

    public void deleteBucket(String bucketName) {
        s3Client.deleteBucket(bucketName);
    }

}

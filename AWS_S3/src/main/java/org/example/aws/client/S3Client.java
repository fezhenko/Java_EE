package org.example.aws.client;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

@Component
@RequiredArgsConstructor
public class S3Client {

    private final AmazonS3 amazonS3;


    //awslocal s3api create-bucket --bucket "test"
    public void createBucket(String bucketName) {
        amazonS3.createBucket(bucketName);
    }

    public List<Bucket> listBuckets() {
        return amazonS3.listBuckets();
    }


    public void deleteBucket(String bucketName) {
        try {
            amazonS3.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            e.getErrorMessage();
        }
    }

    //awslocal s3api put-object --bucket sample-bucket --key index.html --body index.html
    public void putToBucket(String bucketName, String fileName, File file) {
        amazonS3.putObject(bucketName, fileName, file);
    }

    public ObjectListing getObjectsListFromBucket(String bucketName) {
        return amazonS3.listObjects(bucketName);
    }

    public void downloadDocument(String bucketName, String fileName, String fileToSave) throws IOException {
        S3Object s3object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        copyInputStreamToFile(inputStream, new File(fileToSave));
    }

}

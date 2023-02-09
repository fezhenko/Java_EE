package org.example.aws.service;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.example.aws.client.S3Client;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3FileService {

    private final S3Client s3Client;


    public void uploadDocument(String bucketName, MultipartFile file) {
        String path = StringUtils.cleanPath(file.getResource().getDescription());
        s3Client.putToBucket(bucketName, file.getOriginalFilename(), path);
    }

    public List<S3ObjectSummary> findFilesInBucket(String bucketName) {
        return s3Client.getObjectsListFromBucket(bucketName.toLowerCase()).getObjectSummaries();
    }

    public void downloadFileFromS3(String bucketName, String fileName, String pathToSaveFile) throws IOException {
        s3Client.downloadDocument(bucketName.toLowerCase(), fileName, pathToSaveFile);
    }
}

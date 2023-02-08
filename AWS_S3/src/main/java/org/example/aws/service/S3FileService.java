package org.example.aws.service;

import lombok.RequiredArgsConstructor;
import org.example.aws.client.S3Client;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3FileService {

    private final S3Client s3Client;


    public void uploadDocument(String bucketName, MultipartFile document) throws IOException {
        s3Client.putToBucket(bucketName, document.getOriginalFilename(), document.getResource().getFile());
    }
}

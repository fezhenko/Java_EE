package org.example.aws.controller;

import lombok.RequiredArgsConstructor;
import org.example.aws.service.S3FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/s3")
@RequiredArgsConstructor
public class S3FilesController {

    private final S3FileService s3FileService;

    @PostMapping(value = "/buckets/{bucketName}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> uploadDocument(
            @PathVariable String bucketName,
            @RequestPart MultipartFile document) {
        try {
            s3FileService.uploadDocument(bucketName, document);
        }
        catch (Exception e)
        {
            throw new RuntimeException("file cannot be uploaded");
        }
        return ResponseEntity.ok("file %s is uploaded".formatted(document.getOriginalFilename()));
    }




}

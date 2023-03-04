package org.example.aws.controller;

import org.example.aws.converter.S3FilesConverter;
import org.example.aws.dto.BucketObjectDto;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.example.aws.dto.DataToDownloadDto;
import org.example.aws.service.S3FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/storages/storage/{bucketName}/files")
@RequiredArgsConstructor
public class S3FilesController {

    private final S3FileService s3FileService;
    private final S3FilesConverter s3FilesConverter;

    @GetMapping
    public ResponseEntity<List<BucketObjectDto>> findFilesInBucket(@PathVariable String bucketName) {
        List<S3ObjectSummary> objectSummaries = s3FileService.findFilesInBucket(bucketName);
        return ResponseEntity.status(HttpStatus.OK).body(s3FilesConverter.toDto(objectSummaries));
    }

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadDocument(
            @PathVariable String bucketName,
            @RequestParam("file") MultipartFile file) {
        s3FileService.uploadDocument(bucketName, file);
        return ResponseEntity.ok("file \"%s\" is uploaded".formatted(file.getName()));
    }

    @PostMapping(value = "/download",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void downloadFileFromS3(
            @PathVariable String bucketName,
            @RequestBody DataToDownloadDto dataToDownloadDto) throws IOException {
        s3FileService.downloadFileFromS3(
            bucketName,
            dataToDownloadDto.getFileName(),
            dataToDownloadDto.getPathToSaveFile());
    }

}

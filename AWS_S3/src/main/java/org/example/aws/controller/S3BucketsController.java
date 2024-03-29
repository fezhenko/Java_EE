package org.example.aws.controller;

import com.amazonaws.services.s3.model.Bucket;
import lombok.RequiredArgsConstructor;
import org.example.aws.converter.BucketConverter;
import org.example.aws.dto.BucketDto;
import org.example.aws.dto.CreateBucketDto;
import org.example.aws.service.S3BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1/storages/storage")
@RequiredArgsConstructor
public class S3BucketsController {

    private final S3BucketService s3BucketService;

    private final BucketConverter bucketConverter;

    @GetMapping
    public ResponseEntity<List<BucketDto>> findBuckets() {
        List<Bucket> buckets = s3BucketService.findBuckets();
        try {
            return ResponseEntity.ok(bucketConverter.toDto(buckets));
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBucket(@RequestBody CreateBucketDto createBucketDto) {
        s3BucketService.createBucket(createBucketDto.getBucketName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("bucket %s is created".formatted(createBucketDto.getBucketName()));
    }

    @DeleteMapping("/{bucketName}")
    public ResponseEntity<String> deleteBucket(@PathVariable String bucketName) {
        try {
            s3BucketService.deleteBucket(bucketName);
        }
        catch (Exception e) {
            return ResponseEntity.status(400).body("Bucket is not deleted");
        }
        return ResponseEntity.status(HttpStatus.GONE).body("Bucket successfully deleted");
    }

}

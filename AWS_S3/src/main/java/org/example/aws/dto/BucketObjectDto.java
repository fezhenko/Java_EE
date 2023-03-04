package org.example.aws.dto;

import lombok.Value;

import java.util.Date;

@Value
public class BucketObjectDto {
     String bucketName;
     String key;
     String eTag;
     long size;
     Date lastModified;
     String storageClass;
     String owner;
}

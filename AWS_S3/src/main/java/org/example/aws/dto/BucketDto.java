package org.example.aws.dto;

import lombok.Value;
import java.util.Date;

@Value
public class BucketDto {
    String bucketName;
    String bucketOwner;
    Date creationDate;
}

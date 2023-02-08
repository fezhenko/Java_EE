package org.example.aws.converter;

import com.amazonaws.services.s3.model.Bucket;
import org.example.aws.dto.BucketDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BucketConverter {

    List<BucketDto> toDto(List<Bucket> bucketsList);
}

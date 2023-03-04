package org.example.aws.converter;

import com.amazonaws.services.s3.model.Bucket;
import org.example.aws.dto.BucketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BucketConverter {

    @Mapping(source = "Bucket", target = "BucketDto", qualifiedByName = "BucketToBucketDto")
    @Named("BucketToBucketDto")
    default List<BucketDto> toDto(List<Bucket> bucketsList) {
        return bucketsList.stream()
                .map(bucket -> new BucketDto(
                        bucket.getName(),
                        bucket.getOwner().getDisplayName(),
                        bucket.getCreationDate()))
                .collect(Collectors.toList());

    }
}

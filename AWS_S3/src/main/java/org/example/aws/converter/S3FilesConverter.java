package org.example.aws.converter;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.example.aws.dto.BucketObjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface S3FilesConverter {

    @Mapping(source = "S3ObjectSummary", target = "BucketObjectDto", qualifiedByName = "ObjectsSummariesToDto")
    @Named("ObjectsSummariesToDto")
     default List<BucketObjectDto> toDto(List<S3ObjectSummary> objectSummaries) {
         return objectSummaries.stream()
                 .map(object -> new BucketObjectDto(
                         object.getBucketName(),
                         object.getKey(),
                         object.getETag(),
                         object.getSize(),
                         object.getLastModified(),
                         object.getStorageClass(),
                         object.getOwner().getDisplayName()
                 ))
                 .collect(Collectors.toList());
     }
}

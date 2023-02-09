package org.example.aws.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class DataToDownloadDto {
    String fileName;
    String pathToSaveFile;

}

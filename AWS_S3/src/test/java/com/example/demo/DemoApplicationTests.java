package com.example.demo;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(SpringExtension.class)
class DemoApplicationTests {

	@Test
	void whenConvertingInputStreamToFile_thenCorrect()
			throws IOException {
		InputStream initialStream = FileUtils.openInputStream
				(new File("src/main/resources/sample.txt"));

		File targetFile = new File("src/main/resources/targetFile.tmp");

		FileUtils.copyInputStreamToFile(initialStream, targetFile);
	}
}

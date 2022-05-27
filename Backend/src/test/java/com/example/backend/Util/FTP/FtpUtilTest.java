package com.example.backend.Util.FTP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.mock.web.MockMultipartFile;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FtpUtilTest {


    @ParameterizedTest
    @CsvFileSource(resources = "/testcase/test.csv", numLinesToSkip = 1)
    void uploadFile(String id, String path, String up_file, String expectedOutput) {

        File file = new File("src/test/java/com/example/backend/Util/FTP/test.txt");
        try {
            MultipartFile cMultiFile = new
                    MockMultipartFile("file", file.getName(), null, new FileInputStream(file));

            String fileName = FtpUtil.testuploadFile(path, cMultiFile);

            Assertions.assertEquals(expectedOutput, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
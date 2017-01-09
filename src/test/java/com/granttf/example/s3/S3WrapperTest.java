package com.granttf.example.s3;

import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class S3WrapperTest {


    private S3Wrapper s3Wrapper = new S3Wrapper();


    @Test
    public void upload() throws Exception {

        Path path = Paths.get("D:\\Grant-data\\temp\\Uploads\\Lorem-ipsum.txt");
        String name = "Lorem-ipsum.txt";
        String originalFileName = "Lorem-ipsum.txt";
        String contentType = "text/plain";
        byte[] content = null;
        content = Files.readAllBytes(path);




        MultipartFile multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        MultipartFile[] multipartFiles =   {
                multipartFile
        };

        List<PutObjectResult> result = s3Wrapper.upload(multipartFiles);
        assertFalse(result.isEmpty());

    }

    @Test
    public void download() throws Exception {

    }

    @Test
    public void list() throws Exception {

    }

}
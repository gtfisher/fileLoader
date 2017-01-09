package com.granttf.example.Controllers;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.granttf.example.s3.S3Wrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/aws/s3")
public class AwsS3ApiController {

    @Autowired
    private S3Wrapper s3Wrapper;

    private final Logger logger =Logger.getLogger(this.getClass());

    @RequestMapping("/hello")
    String gethelloWorld() {
        return "Hello World!";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public List<PutObjectResult> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
        return s3Wrapper.upload(multipartFiles);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
        return s3Wrapper.download(key);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<S3ObjectSummary> list() throws IOException {
        return s3Wrapper.list();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public List<S3ObjectSummary> delete(@RequestParam String bucket, @RequestParam String key) throws IOException {
        logger.info("delete called->" + bucket + "-" + key);
        s3Wrapper.delete(bucket,key);
        return s3Wrapper.list();
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public void deleteAll(@RequestParam String bucket) throws IOException {
        logger.info("delete called->" + bucket);

        s3Wrapper.deleteAll(bucket);
    }



}

package com.granttf.example.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AwsS3ApiControllerTest {

//    @Autowired
//    private AwsS3UploadController awsS3UploadController;

    private MediaType textContentType = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(),
            Charset.forName("utf8"));

    private MediaType jsonContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Autowired private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
//    this.mockMvc = MockMvcBuilders.standaloneSetup(awsS3UploadController).build();
      this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void gethelloWorld() throws Exception {
        mockMvc.perform(get("/api/aws/s3/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content(). contentType(textContentType))
                .andExpect(MockMvcResultMatchers.content().string("Hello World!"));


    }

    @Test
    public void upload() throws Exception {

    }

    @Test
    public void download() throws Exception {

    }

    @Test
    public void list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/aws/s3/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content(). contentType(jsonContentType))
                .andReturn();
               // .andExpect(MockMvcResultMatchers.content().string("Hello World!"));

        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(String.format("content type-> $s", mvcResult.getResponse().getContentType()));
        System.out.printf("content->$s \n", content);
    }

}
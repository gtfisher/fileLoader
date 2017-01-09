package com.granttf.example.Controllers;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.granttf.example.s3.S3Wrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/s3home")
public class AwsS3BucketFileController {

//    @Autowired
//    RestOperations restOperations;
    @Autowired
    private S3Wrapper s3Wrapper;

    private final Logger logger =Logger.getLogger(this.getClass());

    public static RedirectView safeRedirect(String url) {
        RedirectView rv = new RedirectView(url);
        rv.setExposeModelAttributes(false);
        return rv;
    }

    //RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "")
    public String home(Model model) {

        List<S3ObjectSummary> s3ObjectSummaries =s3Wrapper.list();

        model.addAttribute("s3Objects",s3ObjectSummaries);
        model.addAttribute("bucketName", s3Wrapper.getBucket());

        logger.info("Home model->" +  model.toString());

        return "s3home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam String bucket, @RequestParam String key) throws IOException {
        logger.info("delete called->" + bucket + "-" + key);
        s3Wrapper.delete(bucket,key);

       Map<String,String> map = new HashMap<>();
        map.put("deletedItem", key);
        map.put("bucketName", s3Wrapper.getBucket());

        return new ModelAndView("deleted", map);
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public ModelAndView deleteAll(@RequestParam String bucket) throws IOException {
        logger.info("delete All called->" + bucket);
        s3Wrapper.deleteAll(bucket);

        Map<String,String> map = new HashMap<>();
        map.put("deletedItem", "All");
        map.put("bucketName", s3Wrapper.getBucket());

        return new ModelAndView("deleted", map);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RedirectView upload(@RequestParam("file") MultipartFile[] multipartFiles) {

        s3Wrapper.upload(multipartFiles);

        return safeRedirect("/s3home");
    }




}

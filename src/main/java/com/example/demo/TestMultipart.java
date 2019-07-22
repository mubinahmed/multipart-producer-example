package com.example.demo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

@RestController
@RequestMapping("/multipart-producer")
public class TestMultipart {


    @PostMapping(consumes = "multipart/form-data")
    public void uploadFiles(MultipartRequest multipartRequest, @RequestPart("payload") String payload) {
        System.out.println("Test");
        System.out.println(multipartRequest.getFileMap());
    }

    @GetMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LinkedMultiValueMap<String, Object>> getFile() throws IOException {
        LinkedMultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("test", "{" +
                "\"id\"" +
                ": \"hi\"}");

        FileSystemResource fileSystemResource = new FileSystemResource("C:\\Users\\p1317868\\Desktop\\HNS_Substance.xlsx");
        System.out.println(fileSystemResource.contentLength());
        formData.add("file-data_5", fileSystemResource);
        return ResponseEntity.ok().body(formData);
    }
}

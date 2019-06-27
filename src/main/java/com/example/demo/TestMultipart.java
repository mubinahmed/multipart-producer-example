package com.example.demo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartRequest;

@RestController
@RequestMapping("/multipart-producer")
public class TestMultipart {


	@PostMapping(consumes = "multipart/form-data")
	public void uploadFiles(MultipartRequest multipartRequest, @RequestPart("payload") String payload) {
		System.out.println("Test");
		System.out.println(multipartRequest.getFileMap());
	}

	@GetMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MultiValueMap<String, Object>> getFile() {
		 MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
		 formData.add("test", "{hi:hi}");
		 formData.add("file-data_5", new FileSystemResource("C:\\Users\\P1321380\\Desktop\\cv_vsl_data.xlsx"));
		return ResponseEntity.ok().body(formData);
	}
	
	@PutMapping
	public void get() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> formData = restTemplate.getForObject("http://localhost:8080/test", MultiValueMap.class);
		System.out.println("test");
	}
}

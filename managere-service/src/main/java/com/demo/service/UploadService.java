package com.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
	boolean upload(MultipartFile file) throws IOException;
}

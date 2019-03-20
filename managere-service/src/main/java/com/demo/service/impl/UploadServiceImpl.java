package com.demo.service.impl;

import com.demo.service.UploadService;
import com.demo.service.util.FtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basePath}")
	private String basePath;
	@Value("${ftpclient.filepath}")
	private String filePath;
	
	@Override
	public boolean upload(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		return FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, file.getInputStream());
	}

}

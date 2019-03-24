package com.demo.control;

import com.demo.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class UploadController {
	@Resource
	private UploadService uploadService;
	@RequestMapping("upload")
	public String upload(MultipartFile file) {
		try {
			boolean result = uploadService.upload(file);
			if(result){
				return "ok";
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
}

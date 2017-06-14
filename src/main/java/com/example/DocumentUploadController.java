package com.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentUploadController {

	@RequestMapping("/file")
	public String file() {
		return "/file";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String handlefileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "Upload Failed:" + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "Upload Failed:" + e.getMessage();
			}
			return "Upload Success";
		} else {
			return "Upload Failed: File is Empty";
		}

	}
}

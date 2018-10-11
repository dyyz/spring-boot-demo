package com.example.demo.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.ArrayUtils;

import com.example.demo.upload.entity.UploadFile;
import com.example.demo.upload.repository.UploadFileRepository;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Value("${uplodapic.path}")
	private String filePath;
	
	@Value("${picture.upload.type}")
	private String[] uploadType;
	
	@Autowired
	private UploadFileRepository uploadFileRepository;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(@ModelAttribute("errorMsg")String errorMsg) {
		return "/upload";
	}
	
	@Transactional
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "multFile", required = false) MultipartFile[] multFiles,
			HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		int length = (multFiles == null || multFiles.length == 1 && multFiles[0].getOriginalFilename().isEmpty()) ? 0 : multFiles.length;
		if((file == null || file.isEmpty()) && length < 1) {
			redirectAttributes.addAttribute("errorMsg", "请选择文件");
			return "redirect:/file/upload";
		}
		List<MultipartFile> newMultFiles = new ArrayList<>();
		if(!file.isEmpty()) {
			newMultFiles.add(file);
		}
		if(length > 0) {
			newMultFiles.addAll(Arrays.asList(multFiles));
		}
		
		String[] picName = new String[length + 1];
		int count = 0;
		List<UploadFile> list = new ArrayList<>();
		
		for(MultipartFile tempFile : newMultFiles) {
			UploadFile uploadFile = new UploadFile(tempFile);
			System.out.println(uploadType.toString());
			if(!ArrayUtils.contains(uploadType, uploadFile.getContentType())) {
				redirectAttributes.addAttribute("errorMsg", "请上传图片格式");
				return "redirect:/file/upload";
			}
	        // 姓名
	        String name = request.getParameter("name");
	        System.out.println("姓名：" + name);

	        String newFileName = uploadFile.getNewFileName();
	        System.out.println("newFileName：" + newFileName);
	        File dest = new File(filePath + newFileName);
	        try {
	        	tempFile.transferTo(dest);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("图片打印绝对路径：" + dest.getAbsolutePath());
	        picName[count] = newFileName;
	        count++;
	        list.add(uploadFile);
//	        model.addAttribute("picName", newFileName);
//	        model.addAttribute("file", file);
//	        model.addAttribute("name", name);
		}
		uploadFileRepository.saveAll(list);
		model.addAttribute("picNames", picName);
        return "/upload";
    }
}

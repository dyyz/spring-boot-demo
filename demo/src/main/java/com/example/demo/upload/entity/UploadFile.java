package com.example.demo.upload.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class UploadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7683200389745811609L;

	// 文件名
	private String fileName;
	// 文件后缀
	private String suffixName;
	// 唯一文件名
	private String newFileName;
	
	private String contentType;
	
	private Date createDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public UploadFile() {
		
	}
	
	public UploadFile(MultipartFile file) {
		fileName = file.getOriginalFilename();
		System.out.println("fileName: " + fileName);
		suffixName = fileName.substring(fileName.lastIndexOf("."));
		System.out.println("suffixName: " + suffixName);
		newFileName = UUID.randomUUID().toString() + suffixName;
		contentType = file.getContentType();
		System.out.println("contentType: " + contentType);
		createDate = new Date();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

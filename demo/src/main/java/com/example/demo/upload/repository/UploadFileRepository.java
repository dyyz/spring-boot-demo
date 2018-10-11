package com.example.demo.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.upload.entity.UploadFile;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long>{

}

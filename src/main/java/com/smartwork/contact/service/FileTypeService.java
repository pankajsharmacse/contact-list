package com.smartwork.contact.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileTypeService {

    public void save(MultipartFile file);

    public void save(InputStream inputStream);
}

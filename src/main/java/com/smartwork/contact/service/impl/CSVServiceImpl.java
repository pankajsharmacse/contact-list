package com.smartwork.contact.service.impl;

import com.smartwork.contact.CSVHelper;
import com.smartwork.contact.model.Contact;
import com.smartwork.contact.repository.ContactRepository;
import com.smartwork.contact.service.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CSVServiceImpl implements FileTypeService{

    @Autowired
    ContactRepository repository;

    public void save(MultipartFile file) {
        try {
            this.save(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void save(InputStream inputStream)
    {
        try {
            List<Contact> contactList = CSVHelper.csvToContacts(inputStream);
            repository.saveAll(contactList);
        }
        catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}

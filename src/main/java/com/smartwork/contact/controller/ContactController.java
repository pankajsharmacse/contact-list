package com.smartwork.contact.controller;

import com.smartwork.contact.CSVHelper;
import com.smartwork.contact.model.ServiceResult;
import com.smartwork.contact.service.ContactService;
import com.smartwork.contact.service.FileTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/contact")
@Slf4j
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    FileTypeService fileService;

    @GetMapping("/{name}")
    public ResponseEntity<ServiceResult<Page>> findContactByName(@PathVariable String name,
                                                                          @RequestParam(value="pageNum", required = false) Integer pageNum)
    {
        ServiceResult<Page> serviceResult = new ServiceResult<>();
        if(pageNum == null)
            pageNum = 0;
        try {
            log.debug("Finding contact by name "+name);
            serviceResult.setPage(contactService.findContactByName(name,pageNum));
            serviceResult.setMessage("Successful");
            serviceResult.setStatus(200);
            log.debug("Found contact by name "+name);
        }
        catch (Exception e)
        {
            log.error("Error while finding contact by name "+name);
            serviceResult.setMessage("Error while fetching the contacts");
            serviceResult.setStatus(500);
        }
        return new ResponseEntity<>(serviceResult, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ServiceResult<Page>> findAllContact(@RequestParam(value="pageNum", required = false) Integer pageNum)
    {
        if(pageNum == null)
            pageNum = 0;
        ServiceResult<Page> serviceResult = new ServiceResult<>();
        try {
            log.debug("Entering Finding all contact by page "+pageNum);
            serviceResult.setPage(contactService.findAllContact(pageNum));
            serviceResult.setMessage("Successful");
            serviceResult.setStatus(200);
            log.debug("Exiting Finding all contact by page "+pageNum);
        }
        catch (Exception e)
        {
            log.error("Error while finding all contact "+e.getMessage());
            serviceResult.setMessage("Error while fetching the contacts");
            serviceResult.setStatus(500);
        }
        return new ResponseEntity<>(serviceResult, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file)
    {
        String message;
        log.debug("Entering upload CSV "+file.getOriginalFilename());
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                log.debug("Uploaded the file successfully: "+file.getOriginalFilename());
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                log.error("Could not upload the file: " + file.getOriginalFilename() + "!");
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}

package com.smartwork.contact;

import com.smartwork.contact.service.FileTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
public class ContactListApplication {

	@Value("${location}")
	String location;
	@Value("${autoRead:true}")
	Boolean autoRead;
	@Autowired
	FileTypeService fileService;

	public static void main(String[] args) {
		SpringApplication.run(ContactListApplication.class, args);
	}

	@PostConstruct
	public ResponseEntity<String> uploadCSV()
	{
		if(autoRead) {
			log.debug("Entering upload CSV at startup");
			Path path = Paths.get(location);
			InputStream inputStream;
			String message;
			try {
				inputStream = Files.newInputStream(path);
				fileService.save(inputStream);
				message = "Uploaded the file successfully: " + path.getFileName();
				log.debug("Uploaded the file successfully: ");
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				log.error("Could not upload the file: " + path.getFileName() + "!");
				message = "Could not upload the file: " + path.getFileName() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}
		return null;
	}
}

package com.smartwork.contact.service;

import com.smartwork.contact.model.Contact;
import org.springframework.data.domain.Page;

public interface ContactService {

    public Page<Contact> findContactByName(String name, int page);

    public Page<Contact> findAllContact(int page);


}

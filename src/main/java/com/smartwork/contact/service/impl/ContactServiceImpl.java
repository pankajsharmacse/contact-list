package com.smartwork.contact.service.impl;

import com.smartwork.contact.model.Contact;
import com.smartwork.contact.repository.ContactRepository;
import com.smartwork.contact.service.ContactService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    @Value("${pageSize}")
    String pageSize;

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Page<Contact> findContactByName(String name, int page)
    {
        return contactRepository.findByNameStartingWithIgnoreCase(name,getPageable(page));
    }

    @Override
    public Page<Contact> findAllContact(int page)
    {
        return contactRepository.findAll(getPageable(page));
    }

    public Pageable getPageable(int page)
    {
        return PageRequest.of(page, Integer.parseInt(pageSize));
    }
}

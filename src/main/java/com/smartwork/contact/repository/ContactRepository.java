package com.smartwork.contact.repository;

import com.smartwork.contact.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public Page<Contact> findByNameStartingWithIgnoreCase(String name, Pageable pageable);
}

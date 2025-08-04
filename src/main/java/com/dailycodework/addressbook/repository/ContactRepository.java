package com.dailycodework.addressbook.repository;

import com.dailycodework.addressbook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}

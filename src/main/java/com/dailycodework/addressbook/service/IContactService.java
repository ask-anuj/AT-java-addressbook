package com.dailycodework.addressbook.service;
import com.dailycodework.addressbook.model.Contact;

import java.util.List;

public interface IContactService {
    Contact addContact(Contact request);
    Contact updateContact(Long id, Contact contact);
    //Contact getContactById(Long id);
    Contact getContact(Long id);
    void deleteContact(Long id);
    List<Contact> getAllContacts();
}

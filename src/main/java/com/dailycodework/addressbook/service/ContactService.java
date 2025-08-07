package com.dailycodework.addressbook.service;

import com.dailycodework.addressbook.model.Address;
import com.dailycodework.addressbook.repository.ContactRepository;
import com.dailycodework.addressbook.model.Contact;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    public Contact addContact(Contact request) {
        return contactRepository.save(request);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        return contactRepository.findById(id).map(existingContact -> {
            updateContactFields(existingContact, contact);
            updateAddress(existingContact, contact.getAddress());
            return contactRepository.save(existingContact);
            //}).orElseThrow(() -> new EntityNotFoundException("No contact found with id: " + id));
        }).orElse(null);
        }

        private void updateContactFields(Contact existingContact, Contact newContact) {
            existingContact.setFirstName(newContact.getFirstName());
            existingContact.setLastName(newContact.getLastName());
        }

         private void updateAddress(Contact existingContact, Address newAddress) {
             if (newAddress != null) {
                 Address existingAddress = existingContact.getAddress();
                 if (existingAddress != null) {
                     existingAddress.setCountry(newAddress.getCountry());
                     existingAddress.setState(newAddress.getState());
                     existingAddress.setCity(newAddress.getCity());
                     existingAddress.setAddress(newAddress.getAddress());
                     existingContact.setAddress(existingAddress);
                 } else {
                     existingContact.setAddress(newAddress);
                 }
             }

    }

    @Override
    public Contact getContact(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.findById(id)
                .ifPresentOrElse(contactRepository::delete, () -> {
                    throw new EntityNotFoundException("No contact found");
                });
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }
}

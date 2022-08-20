package com.mercuryCyclists.procurement.service;

import com.mercuryCyclists.procurement.entity.Contact;
import com.mercuryCyclists.procurement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
/**
 * Service for contact
 */
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) { this.contactRepository = contactRepository; }

    /**
     * Gets all contact
     */
    public List<Contact> getAllContact() { return contactRepository.findAll(); }

    /**
     * Gets contact based on id
     */
    public Contact getContact(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);

        if(!contact.isPresent()){
            throw new IllegalStateException(String.format("contact with Id %s does not exist",contact));
        }
        return contact.get();
    }

    /**
     * Registers new contact
     */
    public Contact registerContact(Contact contact){
        if(!contact.validate()){
            throw new IllegalStateException("Invalid contact");

        }
        contactRepository.save(contact);
        return contact;
    }

    /**
     * Updates existing contact based on contact given
     */
    public Contact updateContact(Contact contact) {
        if(!contact.validate()){
            throw new IllegalStateException("Invalid contact");

        }

        Optional<Contact> existingContact = contactRepository.findById(contact.getId());
        if(!existingContact.isPresent()){
            throw new IllegalStateException(String.format("Contact with Id %s does not exist",contact.getId()));
        }

        contactRepository.save(contact);
        return contact;
    }

    /**
     * deletes existing contact based on id
     */
    public void deleteContact(Long contactId){
        Optional<Contact> existingContact = contactRepository.findById(contactId);

        if(!existingContact.isPresent()){
            throw new IllegalStateException(String.format("Contact with Id %s does not exist",contactId));
        }
        contactRepository.delete(existingContact.get());
    }
}

package com.mercuryCyclists.procurement.controller;

import com.mercuryCyclists.procurement.entity.Contact;
import com.mercuryCyclists.procurement.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) { this.contactService = contactService; }

    /**
     * Gets all Contacts
     */
    @GetMapping
    public List<Contact> getContacts() { return contactService.getAllContact(); }

    /**
     * Gets contact based on Id
     */
    @GetMapping(path="{contactId}")
    public Contact getContact(@PathVariable("contactId") Long ContactId) { return contactService.getContact(ContactId); }

    /**
     * Registers a new contact
     */
    @PostMapping()
    public Contact registerContact(@RequestBody Contact contact) { return contactService.registerContact(contact); }

    /**
     * Updates existing Contact based on the Contact given
     */
    @PutMapping()
    public Contact updateContact(@RequestBody Contact contact) { return contactService.updateContact(contact); }

    /**
     * Deletes existing Contact based on Id
     */
    @DeleteMapping(path="{contactId}")
    public void deleteContact (@PathVariable("contactId") Long contactId) { contactService.deleteContact(contactId); }
}

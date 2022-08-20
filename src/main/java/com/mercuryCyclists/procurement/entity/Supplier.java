package com.mercuryCyclists.procurement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Supplier Entity / DAO
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String base;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Contact> contactSet = new HashSet<Contact>();

    /**
     * Checks to see whether supplier is valid
     */
    public boolean validate(){
        if (companyName == null || companyName.isEmpty() || base == null || base.isEmpty() ){
            return false;
        }
        return true;
    }

    public void addContact(Contact c) {
        contactSet.add(c);
    }

    public void removeContact(Contact c) {
        if(contactSet.contains(c)) {
            contactSet.remove(c);
        }
    }

    public void updateContact(Contact c) {
        contactSet.remove(c);
        contactSet.add(c);
    }
}

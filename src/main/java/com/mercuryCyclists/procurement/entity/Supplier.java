package com.mercuryCyclists.procurement.entity;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany
    private Set<Contact> contactSet;

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
        if(c.validate()) {
            contactSet.add(c);
        } else {
            throw new IllegalStateException("Contact invalid");
        }

    }
}

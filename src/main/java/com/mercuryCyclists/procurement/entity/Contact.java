package com.mercuryCyclists.procurement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Contact Entity
 */

@Entity
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String position;

    public Contact() {}

    public Contact(String name, String phone, String email, String position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public boolean validate(){
        if (name == null || phone == null || email == null || position == null ){
            return false;
        }
        return true;
    }

    public boolean equals(Object another) {
        //System.out.println("Does it equal?" + (this.id == ((Contact)another).id));
        return this.id == ((Contact)another).id;
    }

    public int hashCode() {
        // Fixes a minor bug with hibernate initializing the ID after the item is put in the set
        if(id == null) {
            id = new Long(1);
        }
        return id.hashCode();
    }
}

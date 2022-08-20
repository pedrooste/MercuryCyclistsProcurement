package com.mercuryCyclists.procurement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Contact Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String position;

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

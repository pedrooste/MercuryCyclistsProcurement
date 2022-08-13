package com.mercuryCyclists.procurement.entity;

import lombok.*;

import javax.persistence.*;

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

    /**
     * Checks to see whether supplier is valid
     */
    public boolean validate(){
        if (companyName == null || companyName.isEmpty() || base == null || base.isEmpty() ){
            return false;
        }
        return true;
    }
}

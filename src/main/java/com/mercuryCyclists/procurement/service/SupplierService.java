package com.mercuryCyclists.procurement.service;

import com.mercuryCyclists.procurement.entity.Contact;
import com.mercuryCyclists.procurement.entity.Supplier;
import com.mercuryCyclists.procurement.repository.ContactRepository;
import com.mercuryCyclists.procurement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for supplier
 */
@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) { this.supplierRepository = supplierRepository; }

    /**
     * Gets all suppliers
     */
    public List<Supplier> getAllSuppliers() { return supplierRepository.findAll(); }

    /**
     * Gets supplier based on id
     */
    public Supplier getSupplier(Long supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if(!supplier.isPresent()){
            throw new IllegalStateException(String.format("Supplier with Id %s does not exist",supplierId));
        }
        return supplier.get();
    }

    /**
     * Registers new supplier
     */
    public Supplier registerSupplier(Supplier supplier){
        if(!supplier.validate()){
            throw new IllegalStateException("Invalid supplier");

        }
        supplierRepository.save(supplier);
        return supplier;
    }

    /**
     * Updates existing supplier based on supplier given
     */
    public Supplier updateSupplier(Supplier supplier) {
        if(!supplier.validate()){
            throw new IllegalStateException("Invalid supplier");

        }

        Optional<Supplier> existingSupplier = supplierRepository.findById(supplier.getId());
        if(!existingSupplier.isPresent()){
            throw new IllegalStateException(String.format("Supplier with Id %s does not exist",supplier.getId()));
        }

        supplierRepository.save(supplier);
        return supplier;
    }

    /**
     * deletes existing supplier based on id
     */
    public void deleteSupplier(Long supplierId){
        Optional<Supplier> existingSupplier = supplierRepository.findById(supplierId);

        if(!existingSupplier.isPresent()){
            throw new IllegalStateException(String.format("Supplier with Id %s does not exist",supplierId));
        }
        supplierRepository.delete(existingSupplier.get());
    }

    public Supplier addContact(Supplier supplier, Contact contact) {
        if(!supplier.validate()){
            throw new IllegalStateException("Invalid supplier");

        }
        Optional<Supplier> existingSupplier = supplierRepository.findById(supplier.getId());
        if(!existingSupplier.isPresent()) {
            throw new IllegalStateException(String.format("Supplier with Id %s does not exist", supplier.getId()));
        }

        existingSupplier.get().addContact(contact);
        supplierRepository.save(supplier);
        return supplier;
    }
}

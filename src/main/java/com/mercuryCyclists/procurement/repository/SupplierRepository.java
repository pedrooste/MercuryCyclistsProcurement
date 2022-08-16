package com.mercuryCyclists.procurement.repository;

import com.mercuryCyclists.procurement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for supplier
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}

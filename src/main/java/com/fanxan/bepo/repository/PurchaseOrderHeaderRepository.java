package com.fanxan.bepo.repository;

import com.fanxan.bepo.models.entity.PurchaseOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderHeaderRepository extends JpaRepository<PurchaseOrderHeader, Integer> {
}
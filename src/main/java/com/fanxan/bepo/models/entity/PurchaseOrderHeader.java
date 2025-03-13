package com.fanxan.bepo.models.entity;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "po_h")
@JsonPropertyOrder({"id", "datetime", "description", "totalPrice", "totalCost", "details", "createdBy", "updatedBy", "createdDatetime", "updatedDatetime"})
public class PurchaseOrderHeader extends FoundationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime datetime;

    @Column(length = 500)
    private String description;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_cost")
    private Integer totalCost;

    @OneToMany(mappedBy = "purchaseOrderHeader", cascade = CascadeType.ALL)
    private List<PurchaseOrderDetail> details;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }


    public List<PurchaseOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PurchaseOrderDetail> details) {
        this.details = details;
        for (PurchaseOrderDetail detail : details) {
            detail.setPurchaseOrderHeader(this);
        }
    }
}
package com.fanxan.bepo.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "po_d")
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "poh_id", insertable = false, updatable = false)
    private Integer pohId;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_qty")
    private Integer itemQty;

    @Column(name = "item_cost")
    private Integer itemCost;

    @Column(name = "item_price")
    private Integer itemPrice;

    @ManyToOne
    @JoinColumn(name = "poh_id")
    @JsonIgnore
    private PurchaseOrderHeader purchaseOrderHeader;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPohId() {
        return pohId;
    }

    public void setPohId(Integer pohId) {
        this.pohId = pohId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public Integer getItemCost() {
        return itemCost;
    }

    public void setItemCost(Integer itemCost) {
        this.itemCost = itemCost;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public PurchaseOrderHeader getPurchaseOrderHeader() {
        return purchaseOrderHeader;
    }

    public void setPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
        this.purchaseOrderHeader = purchaseOrderHeader;
    }
}

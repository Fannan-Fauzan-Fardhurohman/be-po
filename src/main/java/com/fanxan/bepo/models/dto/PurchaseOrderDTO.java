package com.fanxan.bepo.models.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderDTO {
    private LocalDateTime datetime;
    private String description;
    private String createdBy;
    private List<PurchaseOrderDetailDTO> details;

    // Getters and Setters
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<PurchaseOrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<PurchaseOrderDetailDTO> details) {
        this.details = details;
    }

    public static class PurchaseOrderDetailDTO {
        private Integer itemId;
        private Integer itemQty;
        private Integer itemCost;
        private Integer itemPrice;

        // Getters and Setters
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
    }
}
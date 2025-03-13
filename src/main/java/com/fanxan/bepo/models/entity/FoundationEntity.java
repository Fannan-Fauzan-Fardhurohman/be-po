package com.fanxan.bepo.models.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class FoundationEntity {

    @Column(name = "created_by", length = 255, updatable = false)
    private String createdBy;

    @Column(name = "updated_by", length = 255)
    private String updatedBy;

    @Column(name = "created_datetime", updatable = false)
    private LocalDateTime createdDatetime;

    @Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;

    @PrePersist
    protected void onCreate() {
        this.createdBy = "SYSTEM";  // Set SYSTEM or any default user
        this.createdDatetime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedBy = "SYSTEM"; // Only set during updates
        this.updatedDatetime = LocalDateTime.now();
    }

    // Getters
    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public LocalDateTime getUpdatedDatetime() {
        return updatedDatetime;
    }
}

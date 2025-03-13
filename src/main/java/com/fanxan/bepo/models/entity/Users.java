package com.fanxan.bepo.models.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@JsonPropertyOrder({"id", "firstName", "lastName", "email", "phone", "createdBy", "updatedBy", "createdDatetime", "updatedDatetime"})
@Entity
@Table(name = "users")
public class Users extends FoundationEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 500)
    private String firstName;

    @Column(name = "last_name", length = 500)
    private String lastName;

    @Column(length = 500)
    private String email;

    @Column(length = 255)
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
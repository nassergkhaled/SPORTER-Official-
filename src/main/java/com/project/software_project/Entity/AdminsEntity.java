package com.project.software_project.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
public class AdminsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    public Integer phone;
    public String email;
    public String fullname;
    public String password;
}

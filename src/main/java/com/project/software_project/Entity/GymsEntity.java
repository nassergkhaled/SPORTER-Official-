package com.project.software_project.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "gyms")
public class GymsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    public String location;
    public String name;
    public Integer amountmonthly;
    public Integer amountquarterly;
    public Integer amountannual;

}

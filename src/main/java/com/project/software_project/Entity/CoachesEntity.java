package com.project.software_project.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "coaches")
@Data
public class CoachesEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    public String phone;
    public String email;
    public String fullname;
    public String password;
    public boolean gender;//0 -->Female  // 1 -->Male
    public float weight;
    public float height;
    public int age;
    public short paymentperiod;//0 -->Monthly // 1-->Quarterly // 2-->ANNUAL
    public int amount;
}

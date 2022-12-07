package com.project.software_project.Entity;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.core
//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.type.TrueFalseType;
//import org.codehaus.jackson.annotate.JsonBackReference;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "players")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PlayersEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String phone;
    public String email;
    public String fullname;
    public String password;
    public boolean gender;//0 -->Female  // 1 -->Male
    public short strategy;//1--> Gym// 2--> App // 3-->Personal
    public float weight;
    public float height;
    public int age;
    public int goal ;//0--> Learn The Basics// 1-->Get Fitter// 2--> Lose Weight// 3--> Gain Weight//4-->Gain More Flexible
    //public int gymid;
    public int coachid;
    public int gymid;
    public String city;

    /*
    public PlayersEntity(Integer id, String phone, String email, String fullname, String password, boolean gender, short strategy, float weight, float height, int age, int goal, int coachid, String oneTimePassword, Date otpRequestedTime, CoachesEntity coach) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.gender = gender;
        this.strategy = strategy;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.goal = goal;
        this.oneTimePassword = oneTimePassword;
        this.otpRequestedTime = otpRequestedTime;
        Coach = coach;


        if(this.coachid==0){ this.coachid = 100; return;}
        this.coachid = coachid;
    }*/



    //OTP by Email Code
    @Transient
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @Column(name = "one_time_password")
    public String oneTimePassword;

    @Column(name = "otp_requested_time")
    public Date otpRequestedTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coachid",insertable = false,updatable = false)
    //@JsonBackReference
    //@JsonIgnore(value = this.flag)
    private CoachesEntity Coach;

    @JsonBackReference
    @JoinColumn(name="gymid",insertable = false,updatable = false)
    @ManyToOne
    private GymsEntity gymentity;



}

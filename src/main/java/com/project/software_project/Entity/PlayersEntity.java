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

    //OTP by Email Code
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @Column(name = "one_time_password")
    public String oneTimePassword;

    @Column(name = "otp_requested_time")
    public Date otpRequestedTime;


//    @Transient
//    int i=0;
//
//
//    public boolean Flsag()
//    {
//        if(i==0)
//            this.flag true;
//        i++;
//        this.flag= false;
//    }
//    @Transient
//    private static final boolean flag=Flsag();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coachid",insertable = false,updatable = false)
    //@JsonBackReference
    //@JsonIgnore(value = this.flag)
    private CoachesEntity Coach;




}

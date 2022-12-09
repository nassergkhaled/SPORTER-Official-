package com.project.software_project.Entity;

//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.codehaus.jackson.annotate.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.software_project.Dto.CoachesDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Entity
@Table(name = "coaches")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
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
    public int goal ;//0--> Learn The Basics// 1-->Get Fitter// 2--> Lose Weight// 3--> Gain Weight//4-->Gain More Flexible



    @OneToMany(mappedBy = "Coach")//Note Very Important if you made many relations for the same entity you have to choose the same mapped by name
    private List<PlayersEntity> playersOfCoach;

    public static CoachesEntity toDto(CoachesDto Coach) {
        return CoachesEntity.builder()
                .id(Coach.getId())
                .phone(Coach.getPhone())
                .email(Coach.getEmail())
                .fullname(Coach.getFullname())
                .password(Coach.getPassword())
                .gender(Coach.isGender())
                .weight(Coach.getWeight())
                .height(Coach.getHeight())
                .age(Coach.getAge())
                .paymentperiod(Coach.getPaymentperiod())
                .amount(Coach.getAmount())
                .goal(Coach.getGoal())
                .playersOfCoach(Coach.getPlayersOfCoach())
                .build();
    }

}

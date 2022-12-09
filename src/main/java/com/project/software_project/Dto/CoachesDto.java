package com.project.software_project.Dto;

import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachesDto {
    private Integer id;
    private String phone;
    private String email;
    private String fullname;
    private String password;
    private boolean gender;//0 -->Female  // 1 -->Male
    private float weight;
    private float height;
    private int age;
    private short paymentperiod;//0 -->Monthly // 1-->Quarterly // 2-->ANNUAL
    private int amount;
    private int goal ;//0--> Learn The Basics// 1-->Get Fitter// 2--> Lose Weight// 3--> Gain Weight//4-->Gain More Flexible
    private List<PlayersEntity> playersOfCoach;

    public static CoachesDto toDto(CoachesEntity Coach) {
        return CoachesDto.builder()
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

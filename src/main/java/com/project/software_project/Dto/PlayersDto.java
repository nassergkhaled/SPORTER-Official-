
package com.project.software_project.Dto;

import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayersDto {
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
    public String guest;
    public int goal ;//0--> Learn The Basics// 1-->Get Fitter// 2--> Lose Weight// 3--> Gain Weight//4-->Gain More Flexible
    public int coachid;
    public int gymid;
    public String city;
    public CoachesEntity coach;
    public GymsEntity gymentity;
    public String path;
    public static PlayersDto toDto(PlayersEntity player)
    {
        return PlayersDto.builder()
                .id(player.getId())
                .phone(player.getPhone())
                .email(player.getEmail())
                .fullname(player.getFullname())
                .password(player.getPassword())
                .gender(player.isGender())
                .strategy(player.getStrategy())
                .weight(player.getWeight())
                .height(player.getHeight())
                .age(player.getAge())
                .guest(player.getGuest())
                .goal(player.getGoal())
                .coachid(player.getCoachid())
                .gymid(player.getGymid())
                .city(player.getCity())
                .path(player.getPath())
                .coach(player.getCoach())
                .gymentity(player.getGymentity())
                .build();
    }

    public static PlayersDto toDtoWithoutCoachAndGymEntity(PlayersEntity player)
    {
        return PlayersDto.builder()
                .id(player.getId())
                .phone(player.getPhone())
                .email(player.getEmail())
                .fullname(player.getFullname())
                .password(player.getPassword())
                .gender(player.isGender())
                .strategy(player.getStrategy())
                .weight(player.getWeight())
                .height(player.getHeight())
                .age(player.getAge())
                .guest(player.getGuest())
                .goal(player.getGoal())
                .coachid(player.getCoachid())
                .gymid(player.getGymid())
                .city(player.getCity())
                .path(player.getPath())
                .build();
    }
    public static List<PlayersDto> convertDtoToEntity(List<PlayersEntity> players) {
        List<PlayersDto> newPlayersEntityList = new ArrayList<>();

        for (PlayersEntity dto : players){
            newPlayersEntityList.add(PlayersDto.toDtoWithoutCoachAndGymEntity(dto));
        }
        return newPlayersEntityList;
    }


    public static List<PlayersDto> onlyNames(List<PlayersEntity> playersOfCoach) {
        List<PlayersDto> newPlayersEntityList = new ArrayList<>();

        for (PlayersEntity dto : playersOfCoach){
            newPlayersEntityList.add(PlayersDto.onlyNamesDto(dto));
        }
        return newPlayersEntityList;
    }

    private static PlayersDto onlyNamesDto(PlayersEntity dto) {
        return PlayersDto.builder()
                .fullname(dto.getFullname())
                .build();
    }
}

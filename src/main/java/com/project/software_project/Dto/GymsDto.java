
package com.project.software_project.Dto;

import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymsDto {
    private Integer id;
    private String location;
    private String name;
    private String description;
    private Integer amountmonthly;
    private Integer amountquarterly;
    private Integer amountannual;
    private float rating;
    public String path;
    private List<PlayersEntity> gymPlayers;



    public static GymsDto gymWithoutPlayersOfGym (GymsEntity Gym)
    {
        return GymsDto.builder()
                .id(Gym.getId())
                .location(Gym.getLocation())
                .amountannual(Gym.getAmountannual())
                .name(Gym.getName())
                .amountmonthly(Gym.getAmountmonthly())
                .amountquarterly(Gym.getAmountquarterly())
                .rating(Gym.getRating())
                .path(Gym.getPath())
                .description(Gym.getDescription())
                .build();
    }

    public static List<GymsDto> convertDtoToEntity(List<GymsEntity> Gym) {
        List<GymsDto> newGymsEntityList = new ArrayList<>();

        for (GymsEntity dto : Gym){
            GymsDto newGym=new GymsDto();
            newGymsEntityList.add(GymsDto.gymWithoutPlayersOfGym(dto));
        }
        return newGymsEntityList;
    }

    public static GymsDto toDto(GymsEntity Gym) {
        return GymsDto.builder()
                .id(Gym.getId())
                .location(Gym.getLocation())
                .amountannual(Gym.getAmountannual())
                .name(Gym.getName())
                .amountmonthly(Gym.getAmountmonthly())
                .amountquarterly(Gym.getAmountquarterly())
                .rating(Gym.getRating())
                .path(Gym.getPath())
                .description(Gym.getDescription())
                .gymPlayers(Gym.getGymPlayers())
                .build();
    }
}

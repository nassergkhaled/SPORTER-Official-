package com.project.software_project.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.software_project.Dto.GymsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Builder
@Entity
@Data
@Table(name = "gyms")
@NoArgsConstructor
@AllArgsConstructor
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
    public float rating;


    @JsonManagedReference
    @OneToMany(mappedBy = "gymentity")
    private List<PlayersEntity> gymPlayers;


    public static GymsEntity toEntity (GymsDto Gym)
    {
        return GymsEntity.builder()
                .id(Gym.getId())
                .location(Gym.getLocation())
                .amountannual(Gym.getAmountannual())
                .name(Gym.getName())
                .amountmonthly(Gym.getAmountmonthly())
                .amountquarterly(Gym.getAmountquarterly())
                .rating(Gym.getRating())
                .build();
    }
}

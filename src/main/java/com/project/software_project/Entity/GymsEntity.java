package com.project.software_project.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "gymentity")
    private List<PlayersEntity> gymPlayers;
}

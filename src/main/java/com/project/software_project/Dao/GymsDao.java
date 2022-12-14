package com.project.software_project.Dao;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.software_project.Dto.GymsDto;
import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.Reposorty.GymsRepo;
import com.project.software_project.Reposorty.PlayersRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GymsDao {

    @Autowired
    private GymsRepo GymRepo;
    @Autowired
    private PlayersRepo playersRepo;




    public List<GymsDto> viewRecommendedGymsForASpecificPlayer(Integer playerId) {
        try {
            Optional<PlayersEntity>player=this.playersRepo.findById(playerId);
            if(player.isPresent())
            {
                List<GymsEntity> response=this.GymRepo.findAllByLocation(player.get().getCity());
                response.sort(Comparator.comparing(GymsEntity::getRating,Comparator.reverseOrder()));
                response.sort(Comparator.comparing(GymsEntity::getAmountmonthly));
                //response=removeAttributeFromAllObjectsInArrayList(response,"gymPlayers");
                return GymsDto.convertDtoToEntity(response);
            }
            else {return new ArrayList<>();}
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }

    }

    public List<GymsEntity> viewAllGyms() {
        try {
            List<GymsEntity>response=new ArrayList<GymsEntity>();
            response=this.GymRepo.findAll();
            response.remove(this.GymRepo.findAllById(100));
            return response;
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    public GymsDto returnAllGymData(Integer gymId) {
        try {
            Optional<GymsEntity>Gym=Optional.ofNullable(this.GymRepo.findAllById(gymId));
            if(Gym.isPresent())
            {
                return GymsDto.toDto(Gym.get());
            }
            else {return new GymsDto();}

        }
        catch (Exception e)
        {
            return new GymsDto();
        }
    }

    public String addGym(GymsEntity gym) {
        try {
            this.GymRepo.save(gym);
            return "SUCCESS";
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }
}

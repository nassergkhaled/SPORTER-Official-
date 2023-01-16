package com.project.software_project.Dao;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.software_project.Dto.GymsDto;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.Reposorty.CoachesRepo;
import com.project.software_project.Reposorty.GymsRepo;
import com.project.software_project.Reposorty.PlayersRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

@Service
public class GymsDao {

    @Autowired
    private GymsRepo GymRepo;
    @Autowired
    private PlayersRepo playersRepo;
    @Autowired
    private CoachesRepo coachesRepo;

    private Instances data;
    private RandomForest model;

    public List<GymsDto> viewRecommendedGymsForASpecificPlayerUsingMachineLearning(Integer playerId) throws Exception {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("gyms.csv");
            data = source.getDataSet();
            data.setClassIndex(data.numAttributes() - 1);
            model = new RandomForest();
            model.buildClassifier(data);
            Optional<PlayersEntity> player = this.playersRepo.findById(playerId);
            if (player.isPresent()) {
                Instance playerInstance = new DenseInstance(data.numAttributes());
                playerInstance.setDataset(data);
                // fill the player instance with the player data
                // use the model to predict the recommended gyms
                double[] prediction = model.distributionForInstance(playerInstance);
                List<GymsEntity> recommendedGyms = new ArrayList<>();
                // iterate over the prediction array to find the recommended gyms
                for (int i = 0; i < prediction.length; i++) {
                    if (prediction[i] > 0.8)
                        recommendedGyms.add(GymRepo.findAllById(Integer.parseInt(data.attribute(data.classIndex()).value(i))));
                }

                // Sort the recommended gyms by rating and price
                recommendedGyms.sort(Comparator.comparing(GymsEntity::getRating, Comparator.reverseOrder()));
                recommendedGyms.sort(Comparator.comparing(GymsEntity::getAmountmonthly));

                return GymsDto.convertDtoToEntity(recommendedGyms);
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            //System.out.println("\n\n\n\n>>>>>>>>>>>\n"+e.getStackTrace()+"\n\n\n");
            return recommendedGyms(playerId);
        }
    }


    public List<GymsDto> recommendedGyms(Integer playerId) {
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

    public List<CoachesEntity> getGymCoaches(Integer gymId) {
        try {
            Optional <GymsEntity> gym = Optional.ofNullable(this.GymRepo.findAllById(gymId));
            if(gym.isPresent())
            {
                return this.coachesRepo.findAllByGymId(gymId);
            }
            else {return new ArrayList<>();}
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }
}


package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.GymsDao;
import com.project.software_project.Dto.CoachesDto;
import com.project.software_project.Dto.GymsDto;
import com.project.software_project.Dto.PlayersDto;
import com.project.software_project.Entity.GymsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@RestController
@RequestMapping(path = "coaches")
public class CoachesController {
    @Autowired
    private CoachesDao coachsDao;
//    @GetMapping(path = "/")
//    public List<GymsEntity> viewAllGyms()
//    {
//        return this.co.viewAllGyms();
//    }
//
//    @GetMapping(path = "/for/{playerid}")
//    public List<GymsDto> viewRecommendedGymsForASpecificPlayer(@PathVariable (name = "playerid") Integer playerId)
//    {
//        return this.gymsDao.viewRecommendedGymsForASpecificPlayer(playerId);
//    }
    @GetMapping(path = "/{email}")
    public CoachesDto returnAllCoachData(@PathVariable (name = "email") String email)
    {
        return this.coachsDao.returnAllCoachData(email);
    }

    @GetMapping(path = "/id/{id}")
    public CoachesDto returnAllCoachDataUsingId(@PathVariable (name = "id") Integer id)
    {
        return this.coachsDao.returnAllCoachData(id);
    }
    @GetMapping(path = "getplayers/{email}")
    public List<PlayersDto> getCoachPlayers (@PathVariable (name = "email") String email)
    {
        return this.coachsDao.returnCoachPlayers(email);
    }
}

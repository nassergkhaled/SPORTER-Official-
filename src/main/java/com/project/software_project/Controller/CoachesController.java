
package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.GymsDao;
import com.project.software_project.Dto.CoachesDto;
import com.project.software_project.Dto.GymsDto;
import com.project.software_project.Entity.GymsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "/{coachid}")
    public CoachesDto returnAllGymData(@PathVariable (name = "coachid") Integer coachId)
    {
        return this.coachsDao.returnAllCoachData(coachId);
    }
}

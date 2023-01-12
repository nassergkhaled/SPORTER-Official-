package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.Dto.PlayersDto;
import com.project.software_project.Entity.PlayersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {
    @Autowired
    private CoachesDao coachesDao;
    @Autowired
    private PlayersDao playersDao;

    @GetMapping(value = "/coach/{playerid}/{coachid}")
    public String signForACoach(@PathVariable (name = "playerid") Integer playerId,
                         @PathVariable (name = "coachid") Integer coachId)
    {
        return this.playersDao.signPlayerToACoach(playerId,coachId);

    }

    @GetMapping(value = "/gym/{playerid}/{gymid}")
    public String signForAGym(@PathVariable (name = "playerid") Integer playerId,
                                @PathVariable (name = "gymid") Integer gymid)
    {
        return this.playersDao.signPlayerToAGym(playerId,gymid);

    }
    @GetMapping(value = "/getdatafromemail/{email}")
    public PlayersDto getDataFromEmail(@PathVariable (name = "email")String email)
    {
        return this.playersDao.getDataFromEmail(email);
    }
}

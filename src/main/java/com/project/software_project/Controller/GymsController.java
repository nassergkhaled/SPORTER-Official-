
package com.project.software_project.Controller;
import com.project.software_project.Dao.GymsDao;
import com.project.software_project.Entity.GymsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "gyms")
public class GymsController {
    @Autowired
    private GymsDao gymsDao;
    @GetMapping(path = "/")
    public List<GymsEntity> viewAllGyms()
    {
        return this.gymsDao.viewAllGyms();
    }

    @GetMapping(path = "/{playerid}")
    public List<GymsEntity> viewRecommendedGymsForASpecificPlayer(@PathVariable (name = "playerid") Integer playerId)
    {
        return this.gymsDao.viewRecommendedGymsForASpecificPlayer(playerId);

    }



}

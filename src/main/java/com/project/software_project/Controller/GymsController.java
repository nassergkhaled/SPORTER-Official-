
package com.project.software_project.Controller;
import com.project.software_project.Dao.GymsDao;
import com.project.software_project.Dto.GymsDto;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.GymsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/for/{playerid}")
    public List<GymsDto> viewRecommendedGymsForASpecificPlayer(@PathVariable (name = "playerid") Integer playerId) throws Exception {
        return this.gymsDao.viewRecommendedGymsForASpecificPlayerUsingMachineLearning(playerId);
    }
    @GetMapping(path = "/{gymid}")
    public GymsDto returnAllGymData(@PathVariable (name = "gymid") Integer gymId)
    {
        return this.gymsDao.returnAllGymData(gymId);
    }
    @PostMapping(path = "/add")
    public String addGym(@RequestBody GymsEntity Gym)
    {
        return this.gymsDao.addGym(Gym);
    }
    @GetMapping(path = "/coaches/{id}")
    public List<CoachesEntity> gymCoaches(@PathVariable (name = "id") Integer id)
    {
        return this.gymsDao.getGymCoaches(id);
    }
}

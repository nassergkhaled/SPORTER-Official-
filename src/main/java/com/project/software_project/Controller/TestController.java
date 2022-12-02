package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.GymsDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Entity.PlayersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {
    @Autowired
    private GymsDao GymDao;
    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;


    @GetMapping(path = "gyms")
    public  List<GymsEntity>VIEWALLGYMS()
    {
        return this.GymDao.ShowAll();
    }
    @GetMapping(path = "players")
    public  List<PlayersEntity>VIEWALLPlayers()
    {
        return this.PlayerDao.ShowAll();
    }
    @GetMapping(path = "coaches")
    public  List<CoachesEntity>VIEWALLCoaches()
    {
        return this.CoachDao.ShowAll();
    }
}

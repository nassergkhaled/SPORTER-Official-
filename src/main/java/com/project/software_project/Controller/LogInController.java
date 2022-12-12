package com.project.software_project.Controller;

import com.project.software_project.Dao.AdminsDao;
import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.bodies.LogInRequestBody;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.bodies.LogInResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/login")
public class LogInController {

    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;
    @Autowired
    private AdminsDao AdminDao;


    @PostMapping("/")
    public LogInResponseBody LogIn(@RequestBody LogInRequestBody Body) {
        Integer PlayerID=PlayerDao.LoginPlayer(Body.getEmail(), Body.getPassword()).getId();
        String guest=PlayerDao.LoginPlayer(Body.getEmail(),Body.getPassword()).getGuest();
        Integer CoachID=CoachDao.LoginCoach(Body.getEmail(), Body.getPassword());
        Integer AdminID=AdminDao.LoginAdminDao(Body.getEmail(), Body.getPassword());
        LogInResponseBody ResponseBody=new LogInResponseBody();
        if (PlayerID!=0) {
            ResponseBody.setMsg("Success 'Player' ");
            ResponseBody.setID(PlayerID);
            ResponseBody.setGuest(Integer.parseInt(guest));
            return ResponseBody;
        } else if (CoachID!=0) {
            ResponseBody.setMsg("Success 'Coach'");
            ResponseBody.setID(CoachID);
            return ResponseBody;
        } else if (AdminID!=0) {
            ResponseBody.setMsg("Success 'Admin'");
            ResponseBody.setID(AdminID);
            return ResponseBody;
        } else {
            ResponseBody.setMsg("Failed");
            ResponseBody.setID(-1);
            return ResponseBody;
        }
    }
    @GetMapping(path = "/view-all")
    public List<CoachesEntity> viewAllCoaches()
    {
        return this.CoachDao.viewAll();
    }
    @GetMapping(path = "/view-all/p")
    public List<PlayersEntity> viewAllPlayers()
    {
        return this.PlayerDao.viewAll();
    }



}
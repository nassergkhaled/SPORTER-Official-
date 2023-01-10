package com.project.software_project.Controller;

import com.project.software_project.Dao.AdminsDao;
import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.bodies.LogInRequestBody;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.PlayersEntity;
import com.project.software_project.bodies.LogInResponseBody;
import com.project.software_project.bodies.PlayerLogInBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LogInResponseBody> LogIn(@RequestBody LogInRequestBody Body) {
        PlayerLogInBody playerResponse = PlayerDao.LoginPlayer(Body.getEmail(), Body.getPassword());
        Integer CoachID=CoachDao.LoginCoach(Body.getEmail(), Body.getPassword());
        Integer AdminID=AdminDao.LoginAdminDao(Body.getEmail(), Body.getPassword());
        LogInResponseBody response = new LogInResponseBody();
        if (playerResponse.getId() != 0) {
            response.setMsg("Success 'Player' ");
            response.setID(playerResponse.getId());
            response.setGuest(Integer.parseInt(playerResponse.getGuest()));
            response.setToken(playerResponse.getToken());
            return ResponseEntity.ok().header("Authorization", "Bearer " + playerResponse.getToken()).body(response);
        } else if (CoachID != 0) {
            response.setMsg("Success 'Coach'");
            response.setID(CoachID);
            return ResponseEntity.ok().body(response);
        } else if (AdminID != 0) {
            response.setMsg("Success 'Admin'");
            response.setID(AdminID);
            return ResponseEntity.ok().body(response);
        } else {
            response.setMsg("Failed");
            response.setID(-1);
            return ResponseEntity.ok().body(response);
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
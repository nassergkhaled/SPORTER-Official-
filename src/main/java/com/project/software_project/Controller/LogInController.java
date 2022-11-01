package com.project.software_project.Controller;

import com.project.software_project.Dao.AdminsDao;
import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.Dto.LogInBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

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
    public String LogIn(@RequestBody LogInBody Body) {
        if (PlayerDao.LoginPlayerDao(Body.getEmail(), Body.getPassword())) {
            return "Success 'Player'";
        } else if (CoachDao.LoginCoachDao(Body.getEmail(), Body.getPassword())) {
            return "Success 'Coach'";
        } else if (AdminDao.LoginAdminDao(Body.getEmail(), Body.getPassword())) {
            return "Success 'Admin'";
        } else {
            return "Failed";
        }
    }


//    @GetMapping(path = "/google")
//    public String OAuth(OAuth2AuthenticationToken oAuth2AuthenticationToken)
//    {
//        if (PlayerDao.LoginGooglePlayerDao(oAuth2AuthenticationToken.getPrincipal().getAttribute("email"))) {
//            return "Success 'Player'";
//        } else if (CoachDao.LoginGoogleCoachDao(oAuth2AuthenticationToken.getPrincipal().getAttribute("email"))) {
//            return "Success 'Coach'";
//        } else {
//            return "Failed";
//        }
//    }

}
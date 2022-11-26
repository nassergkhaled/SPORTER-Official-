package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.bodies.LogInRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reset-password")
public class ResetPasswordController {
    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;

    @PostMapping(path = "/player")
    public String ResetPasswordPlayer (@RequestBody LogInRequestBody NewPassBody)
    {
        return this.PlayerDao.ResetPassword(NewPassBody.getPassword(),NewPassBody.getEmail());
    }
    @PostMapping(path = "/coach")
    public String ResetPasswordCoach (@RequestBody LogInRequestBody NewPassBody)
    {
        return this.CoachDao.ResetPassword(NewPassBody.getPassword(),NewPassBody.getEmail());
    }

}

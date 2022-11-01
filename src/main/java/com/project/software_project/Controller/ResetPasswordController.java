package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.Dto.LogInBody;
import com.project.software_project.Dto.ResetPasswordBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {
    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;

    @PostMapping(path = "/reset-password")
    public String ResetPassword (@RequestBody ResetPasswordBody ResetPasswordBody)
    {

        if(ResetPasswordBody.getId()>1000)
        {
            return this.PlayerDao.ResetPassword(ResetPasswordBody);
        }
        else
        {
            return this.CoachDao.ResetPassword(ResetPasswordBody);
        }

    }
}

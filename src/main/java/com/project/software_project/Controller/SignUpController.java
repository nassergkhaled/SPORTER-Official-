package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.Dto.StringBody;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.PlayersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(path = "/signup")
public class SignUpController {
    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;


    @PostMapping(path = "/player")
    public String SignUp(@RequestBody PlayersEntity Player)
    {
        return this.PlayerDao.SignUpPlayerDao(Player);
    }

    @PostMapping(path = "/coach")
    public String SignUp(@RequestBody CoachesEntity Coach)
    {
        return this.CoachDao.SignUpCoachDao(Coach);
    }

    @PostMapping(path = "/otp/player")
    public Integer OtpPlayer(@RequestBody StringBody email)
    {
        try {
            return this.PlayerDao.OTP_OpertionPlayer(email.bodystring);
        }
        catch (Exception e)
        {
            return -1;
        }

    }
    @PostMapping(path = "/otp/coach")
    public Integer OtpCoach(@RequestBody StringBody email)
    {
        try {
            return this.CoachDao.OTP_OpertionCoach(email.bodystring);
        }
        catch (Exception e)
        {
            return -1;
        }

    }
}

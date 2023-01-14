package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.bodies.PhoneDigitsAPIBody;
import com.project.software_project.bodies.StringBody;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.PlayersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/signup")
public class SignUpController {
    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;



    @PostMapping(path = "/player")
    public String SignUp(@RequestBody PlayersEntity Player) {
        //Player.setGuest("1");
        return this.PlayerDao.SignUpPlayer(Player);
    }

    @PostMapping(path = "/coach")
    public String SignUp(@RequestBody CoachesEntity Coach)
    {
        Coach.setRegistrationyear(LocalDate.now().getYear());
        return this.CoachDao.SignUpCoach(Coach);
    }

    @PostMapping(path = "/otp/player")
    public Integer OtpPlayer(@RequestBody StringBody email)
    {
        try {
            return this.PlayerDao.OTP_OperationPlayer(email.bodystring);
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
            return this.CoachDao.OTP_OperationCoach(email.bodystring);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    @PostMapping (path = "/getphonedigitscoach")
    public PhoneDigitsAPIBody GetPhoneDigitsC(@RequestBody StringBody email)
    {
        return this.CoachDao.phonedigitscoach(email.bodystring);
    }
    @PostMapping (path = "/getphonedigitspalyer")
    public PhoneDigitsAPIBody GetPhoneDigitsP(@RequestBody StringBody email)
    {
        return this.PlayerDao.phoneDigitsPlayer(email.bodystring);
    }
}

package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.GeneralDao;
import com.project.software_project.Dao.PlayersDao;

import com.project.software_project.bodies.EditProfileBody;
import com.project.software_project.bodies.FeedBackBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(path = "profile")
public class EditProfileController {
    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;
    @Autowired
    private GeneralDao GeneralDao;
    @PatchMapping(path = "/edit/player")
    public String EditPlayerinfo(@RequestBody EditProfileBody Body)
    {
        return this.PlayerDao.EditInfo(Body);
    }
    @PatchMapping(path = "/edit/coach")
    public String editCoachInfo(@RequestBody EditProfileBody Body)
    {
        return this.CoachDao.editInfo(Body);
    }
    @GetMapping(path = "/help")
    public String SendFeedBack(@RequestParam String email,
                               @RequestParam String name,
                               @RequestParam String text) throws MessagingException, UnsupportedEncodingException {
        return this.GeneralDao.SendFeedBack(email,name,text);
    }

}

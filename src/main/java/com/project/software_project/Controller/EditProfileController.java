package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;

import com.project.software_project.bodies.EditProfileBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "edit")
public class EditProfileController {

    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;
    @GetMapping(path = "/player")
    public String EditPlayerinfo(@RequestParam String email,
                                 @RequestParam String name,
                                 @RequestParam String phone,
                                 @RequestParam Integer goal)
    {
        EditProfileBody Body= new EditProfileBody(name,email,phone,goal);
        return this.PlayerDao.Editinfo(Body);
    }
}

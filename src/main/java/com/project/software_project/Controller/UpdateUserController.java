package com.project.software_project.Controller;


import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.Dao.PlayersDao;
import com.project.software_project.Dto.UpdatePasswordBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController

@RequestMapping(path = "/update")
public class UpdateUserController
{

    @Autowired
    private PlayersDao PlayerDao;
    @Autowired
    private CoachesDao CoachDao;

    @PostMapping(path = "password")
    public String UpdatePassword(@RequestBody UpdatePasswordBody Body)
    {
//        if(Body.getId()>1000)return this.PlayerDao.UpdatePassword(Body);
//        else return this.CoachDao.UpdatePassword(Body);
        return "Hi";
    }
}


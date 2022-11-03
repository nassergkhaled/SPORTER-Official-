package com.project.software_project.Controller;

import com.project.software_project.Dao.CoachesDao;
import com.project.software_project.bodies.StringBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/front")
public class FrontAssociationController {
    @Autowired
    private CoachesDao CoachDao;

    @GetMapping(path = "/id-from-coach-name")
    public Integer getIdFromCoachName(@RequestBody StringBody CoachName)
    {
        return this.CoachDao.getIdFromCoachName(CoachName);
    }
}

package com.project.software_project.Controller;

import com.project.software_project.Dao.GymsDao;
import com.project.software_project.Entity.GymsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private GymsDao GymDao;
    @GetMapping(path = "gyms")
    public List<GymsEntity>VIEWALLGYMS()
    {
        return this.GymDao.ShowAll();
    }
}

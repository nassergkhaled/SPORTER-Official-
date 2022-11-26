package com.project.software_project.Dao;

import com.project.software_project.Entity.GymsEntity;
import com.project.software_project.Reposorty.GymsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymsDao {

    @Autowired
    private GymsRepo GymRepo;


    public List<GymsEntity>ShowAll()
    {
        return this.GymRepo.findAll();
    }


}

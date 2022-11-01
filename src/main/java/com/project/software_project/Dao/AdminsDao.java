package com.project.software_project.Dao;

import com.project.software_project.Entity.AdminsEntity;
import com.project.software_project.Reposorty.AdminsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminsDao {
    @Autowired
    public AdminsRepo AdminReposotry;

    public boolean LoginAdminDao(String email, String password)
    {
        try {
            Optional<AdminsEntity> AdminEntity;
            AdminEntity = Optional.ofNullable(AdminReposotry.findAllByEmailAndPassword(email, password));
            if (AdminEntity.isPresent()) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
        catch (Exception e)
        {
            return Boolean.FALSE;
        }
    }
}

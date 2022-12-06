package com.project.software_project.Dao;

import com.project.software_project.Entity.AdminsEntity;
import com.project.software_project.Reposorty.AdminsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminsDao {
    @Autowired
    public AdminsRepo AdminRepository;
    public Integer LoginAdminDao(String email, String password)
    {
        try {
            Optional<AdminsEntity> AdminEntity;
            AdminEntity = Optional.ofNullable(AdminRepository.findAllByEmailAndPassword(email, password));
            if (AdminEntity.isPresent()) {
                return AdminEntity.get().getId();
            } else {
                return 0;
            }
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}

package com.project.software_project.Reposorty;

import com.project.software_project.Entity.AdminsEntity;
import com.project.software_project.Entity.CoachesEntity;
import com.project.software_project.Entity.PlayersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachesRepo extends JpaRepository<CoachesEntity,Integer>
{
    public CoachesEntity findAllByEmail(String email);
    public CoachesEntity findAllByEmailAndPassword(String email, String password);
     public boolean existsByEmail(String Email);
    public boolean existsByFullname(String Name);
    public boolean existsByPhone(String Phone);

}

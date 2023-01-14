package com.project.software_project.Reposorty;

import com.project.software_project.Entity.CoachesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachesRepo extends JpaRepository<CoachesEntity,Integer>
{
    public CoachesEntity findAllByEmail(String email);
    public CoachesEntity findAllByEmailAndPassword(String email, String password);
     public boolean existsByEmail(String Email);
    public boolean existsByPhone(String Phone);
    public List<CoachesEntity>findAll();
    public CoachesEntity findAllByFullname(String coachName);

    public CoachesEntity findAllById(Integer coachId);

    public CoachesEntity findAllByPhone(String phone);
}

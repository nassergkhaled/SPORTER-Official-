package com.project.software_project.Reposorty;

import com.project.software_project.Entity.GymsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymsRepo extends JpaRepository<GymsEntity,Integer> {
    public List<GymsEntity>findAll();
    public Optional<GymsEntity> findById(Integer id);
}

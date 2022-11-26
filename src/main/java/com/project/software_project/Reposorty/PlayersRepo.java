package com.project.software_project.Reposorty;

import com.project.software_project.Entity.AdminsEntity;
import com.project.software_project.Entity.PlayersEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayersRepo extends JpaRepository<PlayersEntity,Integer> {
    public List<PlayersEntity> findAllById(Integer id);
    public PlayersEntity findAllByEmail(String email);
    public List<PlayersEntity> findAllByFullname(String FName);

    public PlayersEntity findAllByPhone(String phone);
    public PlayersEntity findAllByIdAndPassword(Integer id,String password);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);
    //insert is done in the dao directly using save () from JPA

    public PlayersEntity findAllByEmailAndPassword(String email, String password);


}

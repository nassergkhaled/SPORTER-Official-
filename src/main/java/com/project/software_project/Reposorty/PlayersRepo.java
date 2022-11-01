package com.project.software_project.Reposorty;

import com.project.software_project.Entity.AdminsEntity;
import com.project.software_project.Entity.PlayersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepo extends JpaRepository<PlayersEntity,Integer> {
    public PlayersEntity findAllById(Integer id);
    public PlayersEntity findAllByEmail(String email);

    public PlayersEntity findAllByIdAndPassword(Integer id,String password);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);
    //insert is done in the dao directly using save () from jpa

    public PlayersEntity findAllByEmailAndPassword(String email, String password);


}

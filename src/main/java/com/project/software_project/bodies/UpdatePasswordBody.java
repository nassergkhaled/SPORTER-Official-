package com.project.software_project.bodies;

import lombok.Data;

@Data
public class UpdatePasswordBody {
    String email;
    String old;
    String password;


}

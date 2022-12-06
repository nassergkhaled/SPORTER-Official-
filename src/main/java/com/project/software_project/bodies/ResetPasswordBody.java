package com.project.software_project.bodies;

import lombok.Data;

@Data
public class ResetPasswordBody {
    public Integer id;
    public String newPassword;
}

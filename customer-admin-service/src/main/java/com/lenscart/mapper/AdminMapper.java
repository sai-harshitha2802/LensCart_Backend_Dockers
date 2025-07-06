package com.lenscart.mapper;

import com.lenscart.dto.AdminDTO;
import com.lenscart.entity.Admin;
import com.lenscart.entity.Role;

public class AdminMapper {

    public static AdminDTO toDTO(Admin admin) {
        return new AdminDTO(
               
                admin.getName(),
                admin.getEmail(),
                admin.getPassword()
        );
    }

    public static Admin toEntity(AdminDTO adminDTO) {
        return new Admin(
                
                adminDTO.getName(),
                adminDTO.getEmail(),
                adminDTO.getPassword(),
                Role.ADMIN // Ensure Role is explicitly set
        );
    }
}

package com.example.demo.Services;

import com.example.demo.Entities.Role;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.RequestEntities.RequestCreateRole;
import com.example.demo.RequestEntities.RequestUpdateRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Role not found")
        );
    }
    public Role createRole(RequestCreateRole createRole) {
        try {
            Role role = new Role();
            role.setRole_name(createRole.getName_role());
            return roleRepository.save(role);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public Role updateRole(String id, RequestUpdateRole requestUpdateRole){
        Role role = getRoleById(id);
        role.setRole_name(requestUpdateRole.getName_role());
        return roleRepository.save(role);
    }
    public Role deleteRoleById(String id) {
        try{
             roleRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}

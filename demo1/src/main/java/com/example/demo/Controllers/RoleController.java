package com.example.demo.Controllers;

import com.example.demo.Entities.Role;
import com.example.demo.RequestEntities.RequestCreateRole;
import com.example.demo.RequestEntities.RequestUpdateRole;
import com.example.demo.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public String ShowAllRole(Model model){
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "Role/index";
    }
    @GetMapping("/edit/{id}")
    public String ShowRoleForm(@PathVariable String id, Model model){
        Role role = roleService.getRoleById(id);
        model.addAttribute("role",role);
        return "Role/edit";
    }
    @GetMapping("/view/{id}")
    public String ShowRoleById(@PathVariable String id, Model model){
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "Role/view";
    }
    @PostMapping("/saveEdit")
    public String SaveEditRole(RequestUpdateRole requestUpdateRole){
        roleService.updateRole(requestUpdateRole.getId_role(),requestUpdateRole);
        return "redirect:/roles";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        Role role = new Role();
        model.addAttribute("role", role);
        return "Role/create";
    }
    @PostMapping("/create")
    public String SaveCreateRole(RequestCreateRole requestCreateRole){
        roleService.createRole(requestCreateRole);
        return "redirect:/roles";
    }
    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable String id){
        roleService.deleteRoleById(id);
        return "redirect:/roles";
    }
}

package com.example.demo.Controllers;


import com.example.demo.Entities.Role;
import com.example.demo.Entities.User;
import com.example.demo.RequestEntities.RequestCreateUser;
import com.example.demo.RequestEntities.RequestUpdateUser;
import com.example.demo.Services.RoleService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("")
    public String ShowAllUsers(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "User/index";
    }
    @GetMapping("/edit/{id}")
    public String ShowStudentForm(@PathVariable String id, Model model){
        User user = userService.getUserById(id);
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("listRole",listRole);
        model.addAttribute("user", user);
        return "User/edit";
    }
    @GetMapping("/view/{id}")
    public String ShowStudentById(@PathVariable String id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "User/view";
    }
    @PostMapping("/saveEdit")
    public String SaveEditUser(RequestUpdateUser requestUpdateUser){
        userService.updateUser(requestUpdateUser.getId(),requestUpdateUser);
        return "redirect:/users";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        User user = new User();
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("listRole",listRole);
        model.addAttribute("user", user);
        return "User/create";
    }
    @PostMapping("/create")
    public String SaveCreateUser(RequestCreateUser requestCreateUser){
        userService.CreateUser(requestCreateUser);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

}
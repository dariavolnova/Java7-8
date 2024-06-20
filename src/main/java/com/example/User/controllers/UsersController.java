package com.example.User.controllers;

import com.example.User.model.User;
import com.example.User.model.dao.UserDAO;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDao = new UserDAO();
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("users",userDao.index());
        return "index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user",userDao.show(id));
        return "show";
    }
    @GetMapping("/new")
    public String adduser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @GetMapping("/{id}/edit")
    public String  edituser(@PathVariable("id") int id,Model model) {
        model.addAttribute("user", userDao.show(id));
        return "edit";
    }
    @PostMapping("")
    public String create(@ModelAttribute("user")  @Valid User user, BindingResult br){
        if(br.hasErrors()){
            return "new";
        }
        userDao.save(user);
        return "redirect:/users";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user")  @Valid User user, @PathVariable("id") int id, BindingResult br){
        if(br.hasErrors()){
            return "edit";
        }
        System.out.println("update");
        userDao.update(id,user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        System.out.println("delete");
        userDao.delete(id);
        return "redirect:/users";
    }


}

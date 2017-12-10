package com.app.exchange.controller;

import com.app.exchange.dao.IRoleDAO;
import com.app.exchange.domain.User;
import com.app.exchange.domain.security.UserRole;
import com.app.exchange.service.MailService;
import com.app.exchange.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private MailService mailService;


    @RequestMapping(value = "/")
    public String home(){
        return "app/home";
    }

    @RequestMapping(value = "/home")
    public String getHome(){
        return "app/home";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(@ModelAttribute("user") User user, Model model){
        if (userService.checkUserExists(user.getUsername(), user.getEmail())){
            if (userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists", true);
            }
            if (userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("usernameExists", true);
            }
            return "signup";
        }else{
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDAO.findByName("ROLE_USER")));
            userService.createUser(user, userRoles);
            mailService.notifyUserEnabled(user);
            return "redirect:/home";
        }
    }




}

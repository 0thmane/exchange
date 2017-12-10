package com.app.exchange.controller;

import com.app.exchange.domain.Categorie;
import com.app.exchange.domain.User;
import com.app.exchange.service.MailService;
import com.app.exchange.service.interfaces.ICategorieService;
import com.app.exchange.service.interfaces.IUserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICategorieService categorieService;

    @Autowired
    private IUserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping("/")
    public String categorie(Model model){

        List<Categorie> categorieList = categorieService.getAllCategorie();
        Categorie categorie = new Categorie();
        model.addAttribute("categorieList", categorieList);
        model.addAttribute("categorie", categorie);
        return "admin/categorie";
    }

    @RequestMapping(value = "/categorie/save", method= RequestMethod.POST)
    public String categorieSave(@ModelAttribute("categorie") Categorie categorie){
        categorieService.saveCategorie(categorie);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/categorie/getCategorie", method= RequestMethod.GET)
    @ResponseBody
    public String getCategorie(@RequestParam("id") String id){
        System.out.println("######## passer");
        Categorie cat = categorieService.findById(Long.parseLong(id));
        System.out.println(cat.toString());
        JSONObject json = new JSONObject();
        json.put("id", cat.getId());
        json.put("name", cat.getName());
        json.put("description", cat.getDescription());
        return json.toJSONString();
    }


    @RequestMapping(value = "/categorie/delete", method= RequestMethod.GET)
    public String categorieDelete(@ModelAttribute("id") String id){
        categorieService.deleteCategorie(Long.parseLong(id));
        return "redirect:/admin/";
    }

    @RequestMapping("/accounts")
    public String accounts(Model model){
        List<User> userList = userService.getAllUserNotEnabled();
        model.addAttribute("userList", userList);
        return "admin/accounts";
    }

    @RequestMapping("/accounts/activer")
    public String accounts(@ModelAttribute("id") String id){
        User user = userService.findById(Long.parseLong(id));
        user.setEnabled(true);
        userService.saveUser(user);
        mailService.notifyUserEnabled(user);
        return "redirect:/admin/accounts";
    }

}

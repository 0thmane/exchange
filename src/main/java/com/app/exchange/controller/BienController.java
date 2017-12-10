package com.app.exchange.controller;

import com.app.exchange.domain.Bien;
import com.app.exchange.domain.User;
import com.app.exchange.service.interfaces.IBienService;
import com.app.exchange.service.interfaces.ICategorieService;
import com.app.exchange.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bien")
public class BienController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBienService bienService;

    @Autowired
    private ICategorieService categorieService;

    @RequestMapping("/")
    public String bien(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Bien> bienList = user.getlBien();
        model.addAttribute("bienList", bienList);
        model.addAttribute("bien", new Bien());
        model.addAttribute("categorieList", categorieService.getAllCategorie());
        model.addAttribute("categorieId");
        return "app/biens";
    }

    @RequestMapping("/save")
    public String bien(@ModelAttribute("bien") Bien bien,@ModelAttribute("categorieId") String categorieId, Principal principal){
        User user = userService.findByUsername(principal.getName());
        bien.setUser(user);
        bien.setState(false);
        bien.setCategorie(categorieService.findById(Long.parseLong(categorieId)));
        bienService.saveBien(bien);
        return "redirect:/bien/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") String id){
        bienService.deleteBien(Long.parseLong(id));
        return "redirect:/bien/";
    }

    @RequestMapping("/details")
    public String details(@RequestParam("id") String id, Model model){
        Bien bien = bienService.findById(Long.parseLong(id));
        System.out.println("###### bien: " + bien);
        model.addAttribute("bien", bien);
        return "app/bienDetails";
    }


}

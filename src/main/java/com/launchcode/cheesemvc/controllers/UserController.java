package com.launchcode.cheesemvc.controllers;


import com.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="user")
public class UserController {

    @RequestMapping(value="add")
    public String add(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {

        if(errors.hasErrors()) {
            model.addAttribute(user);
            return "user/add";
        }

        if(user.getPassword().equals(verify)) {
            model.addAttribute("user", user);
            return "user/index";
        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("error", "Passwords do not match");
            return "user/add";
        }

    }
}

package com.controller;

import com.entities.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class AuthorizationController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loadStartPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authentication");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView authenticate(HttpSession httpSession, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();

        int id = userService.find(user);

        if(id == -1){
            modelAndView.setViewName("redirect:/");
        }else{
            user.setId(id);
            httpSession.setAttribute("user",user);
            modelAndView.setViewName("redirect:/dashboard");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        //todo усложнить добавление при регистрации
        userService.add(user);
        return modelAndView;
    }

}

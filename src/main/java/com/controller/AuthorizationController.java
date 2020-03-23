package com.controller;

import com.entities.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loading the start page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loadStartPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authentication");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView authenticate(HttpSession httpSession, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(user.toString() + user.getPassword());
        int id = userService.findUser(user);
        if(id == -1){
            System.out.println("NO SUCH USER SORRY");
            modelAndView.setViewName("redirect:/authentication");
        }else{
            user.setId(id);
            httpSession.setAttribute("user",user);
            System.out.println("USER DETECTED");
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

    //todo добавить проверку логина в регистрации
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(user.toString()+" "+user.getPassword());
        if(user.getPassword()!=null) {
            user.setId(userService.add(user));
            modelAndView.setViewName("redirect:/");
        }else{
            modelAndView.setViewName("redirect:/registration");
        }
        return modelAndView;
    }

}

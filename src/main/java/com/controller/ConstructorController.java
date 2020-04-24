package com.controller;

import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import com.forms.CreatingForm;
import com.forms.QuestionnaireForm;
import com.entities.User;
import com.service.QuestionnaireService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

@RestController
public class ConstructorController {
    private QuestionnaireService questionnaireService;
    private UserService userService;

    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) httpSession.getAttribute("user");
        List<Questionnaire> questionnaires = userService.getQuestionnaireList(user.getId());

        System.out.println(questionnaires.size());

        if (questionnaires.isEmpty()) {
            System.out.println("No questionnaires for " + user.getLogin() + "\n");
        } else {
            int[] numberOfAnswers = questionnaireService.numberOfAnswers(user.getId());
            int i = 0;
            for (Questionnaire questionnaire : questionnaires) {
                questionnaire.setNumberOfAnswers(numberOfAnswers[i]);
                i++;
            }
        }
        modelAndView.setViewName("home");
        modelAndView.addObject("username", user.getLogin());
        modelAndView.addObject("questionnaireList", questionnaires);
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.GET)
    public ModelAndView createForm(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create_form");
        modelAndView.addObject("questions", new ArrayList());
        modelAndView.addObject("username", user.getLogin());
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.POST)
    public ModelAndView readingTheQuestionnaire(@RequestParam("questionInformation") String[] questions, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");

        CreatingForm form = new CreatingForm(questions, user.getId());
        Questionnaire questionnaire = form.getQuestionnaire();

        questionnaireService.add(questionnaire);

        modelAndView.setViewName("redirect:/dashboard");
        modelAndView.addObject("username", user.getLogin());
        return modelAndView;
    }


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/dashboard");
        questionnaireService.delete(questionnaireId);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView loadPageEdit(@ModelAttribute("q") int questionnaireId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");
        modelAndView.setViewName("editing");

        Questionnaire questionnaire = questionnaireService.get(questionnaireId);

        System.out.println("||||"+questionnaire.toString());

        modelAndView.addObject("questionnaire", questionnaire);
        modelAndView.addObject("id", questionnaireId);
        modelAndView.addObject("username", user.getLogin());
        modelAndView.addObject("result", new ArrayList<>());
        httpSession.setAttribute("questionnaire", questionnaireId);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editQuestionnaire(@RequestParam("questionInformation") String[] questions, HttpSession httpSession) {

        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");
        System.out.println();
        for (String s : questions) {
            System.out.println(s);
        }
        System.out.println();

        QuestionnaireForm form = new QuestionnaireForm(questions, user.getId());
        Questionnaire questionnaire = form.getQuestionnaire();
        int id = (int) httpSession.getAttribute("questionnaire");
        questionnaire.setId(id);


        questionnaireService.update(questionnaire);

        modelAndView.setViewName("redirect:/dashboard");
        httpSession.removeAttribute("questionnaire");
        return modelAndView;
    }

}

package com.controller;

import com.model.Answer;
import com.model.Question;
import com.model.Questionnaire;
import com.forms.QuestionnaireForm;
import com.model.User;
import com.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;
/*
@Controller
public class OldController {
    private QuestionnaireService questionnaireService;
    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }


    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public ModelAndView allQuestionnaires(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");
        List<Questionnaire> questionnaires = questionnaireService.getUserQuestionnaires(user.getId());
        if (questionnaires.isEmpty()) {
            System.out.println("No questionnaires for " + user.getLogin() + "\n");
        }
        modelAndView.setViewName("dashboard");
        modelAndView.addObject("questionnairesList", questionnaires);
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.GET)
    public ModelAndView createForm(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create_form");
        modelAndView.addObject("questions",new ArrayList());
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.POST)
    public ModelAndView readingTheQuestionnaire(@RequestParam("questionInformation")String[] questions, @ModelAttribute("title")String title, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");

        QuestionnaireForm form = new QuestionnaireForm(questions,user.getId());
        Map<Question,List<Answer>>map = form.getMap();

        if(map.isEmpty() || title.isEmpty()){
            modelAndView.setViewName("create_form");
            modelAndView.addObject("message","QuestionsList or title is empty!");
        }else{
            Questionnaire questionnaire = new Questionnaire(title,user.getId(),map.keySet().size());
            questionnaireService.add(questionnaire,map);
            modelAndView.setViewName("redirect:/dashboard");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }


    @RequestMapping(value="/delete", method = RequestMethod.GET)
    public ModelAndView deleteQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/dashboard");
        questionnaireService.delete(questionnaireId);
        return modelAndView;
    }

}
**/
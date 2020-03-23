package com.controller;

import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
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

@Controller
public class ConstructorController {
    private QuestionnaireService questionnaireService;
    private UserService userService;
    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView allQuestionnaires(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) httpSession.getAttribute("user");
        List<Questionnaire> questionnaires = userService.getQuesionnaireList(user.getId());
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
        modelAndView.addObject("username",user.getLogin());
        modelAndView.addObject("questionnaireList", questionnaires);
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.GET)
    public ModelAndView createForm(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create_form");
        modelAndView.addObject("questions", new ArrayList());
        modelAndView.addObject("username",user.getLogin());
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.POST)
    public ModelAndView readingTheQuestionnaire(@RequestParam("questionInformation") String[] questions, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");
        System.out.println(questions[0]+"!!!!!!!");

        QuestionnaireForm form = new QuestionnaireForm(questions, user.getId());
        Map<Question, List<Answer>> map = form.getMap();

        if (map.isEmpty()) {
            modelAndView.setViewName("create_form");
            modelAndView.addObject("message", "QuestionsList or title is empty!");
        } else {
            Questionnaire questionnaire = new Questionnaire(form.getTitle(), form.getDescription(), user.getId(), map.keySet().size());
            questionnaireService.add(questionnaire, map);
            modelAndView.setViewName("redirect:/dashboard");
        }
        modelAndView.setViewName("redirect:/dashboard");
        modelAndView.addObject("username",user.getLogin());
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
    public ModelAndView loadPageEdit(@ModelAttribute("q") int questionnaireId,HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(questionnaireId);
        modelAndView.setViewName("edit");
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        Map<Question, List<Answer>> map = questionnaireService.getMap(questionnaireId);
        modelAndView.addObject("map", map);
        modelAndView.addObject("questionnaire",questionnaire);
        modelAndView.addObject("id",questionnaireId);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editQuestionnaire(HttpSession httpSession, @RequestParam("questionInformation") String[] questions,
    @RequestParam("description")String description, @RequestParam("title")String title,@RequestParam("qId")String id ) {

        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");

        QuestionnaireForm form = new QuestionnaireForm(questions, user.getId());
        Map<Question, List<Answer>> map = form.getMap();
        System.out.println(id);

        Questionnaire questionnaire = questionnaireService.getById(Integer.parseInt(id));
        questionnaire.setDescription(description);
        questionnaire.setTitle(title);
        questionnaire.setNumberOfAnswers(map.keySet().size());
        questionnaireService.edit(questionnaire,map);
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

}

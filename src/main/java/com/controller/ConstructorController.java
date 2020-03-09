package com.controller;

import com.forms.UserAnswersForm;
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
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

@Controller
public class ConstructorController {
    private QuestionnaireService questionnaireService;

    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView allQuestionnaires(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) httpSession.getAttribute("user");
        List<Questionnaire> questionnaires = questionnaireService.getUserQuestionnaires(user.getId());
        if (questionnaires.isEmpty()) {
            System.out.println("No questionnaires for " + user.getLogin() + "\n");
        } else {
          /*  int[] numberOfAnswers = questionnaireService.numberOfAnswers(user.getId());
            int i = 0;
            for (Questionnaire questionnaire : questionnaires) {
                questionnaire.setNumberOfAnswers(numberOfAnswers[i]);
                i++;
            }*/
        }
        modelAndView.setViewName("home");

        modelAndView.addObject("questionnaireList", questionnaires);
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.GET)
    public ModelAndView createForm(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create_form");
        modelAndView.addObject("questions", new ArrayList());
        return modelAndView;
    }


    @RequestMapping(value = "/create_form", method = RequestMethod.POST)
    public ModelAndView readingTheQuestionnaire(@RequestParam("questionInformation") String[] questions, @ModelAttribute("title") String title, @ModelAttribute("description") String description, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");

        QuestionnaireForm form = new QuestionnaireForm(questions, user.getId());
        Map<Question, List<Answer>> map = form.getMap();

        if (map.isEmpty() || title.isEmpty()) {
            modelAndView.setViewName("create_form");
            modelAndView.addObject("message", "QuestionsList or title is empty!");
        } else {
            Questionnaire questionnaire = new Questionnaire(title, description, user.getId(), map.keySet().size());
            questionnaireService.add(questionnaire, map);
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


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/dashboard");
        questionnaireService.delete(questionnaireId);
        return modelAndView;
    }

    /*
        @RequestMapping(value = "/questionnaire", method = RequestMethod.GET)
        public ModelAndView openQuestionnaire(@ModelAttribute("q") int questionnaireId, HttpSession httpSession) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("questionnaire");

            Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
            Map<Question, List<Answer>>map = questionnaireService.getQuestionnaireInfo(questionnaireId);

            questionnaire.setTitle(questionnaire.getTitle().substring(0, 1).toUpperCase() + questionnaire.getTitle().substring(1));
            modelAndView.addObject("title",questionnaire.getTitle());
            modelAndView.addObject("map",map);
            return modelAndView;
        }

        @RequestMapping(value = "/questionnaire", method = RequestMethod.POST)
        public ModelAndView openQuestionnaire() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/dashboard");
            return modelAndView;
        }

    */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView loadPageEdit(@ModelAttribute("q") int questionnaireId,HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        Map<Question, List<Answer>> map = questionnaireService.getQuestionnaireInfo(questionnaireId);
        modelAndView.addObject("map", map);
        modelAndView.addObject("questionnaire",questionnaire);

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editQuestionnaire(HttpSession httpSession, @RequestParam("questionInformation") String[] questions,
  @ModelAttribute("questionnaire")Questionnaire questionnaire ) {

        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("user");

        QuestionnaireForm form = new QuestionnaireForm(questions, user.getId());
        Map<Question, List<Answer>> map = form.getMap();
        System.out.println(questionnaire.toString());
        questionnaire.setNumberOfAnswers(map.keySet().size());
        questionnaireService.delete(questionnaire.getId());
        questionnaireService.add(questionnaire,map);
       // questionnaireService.edit(questionnaire);
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

}

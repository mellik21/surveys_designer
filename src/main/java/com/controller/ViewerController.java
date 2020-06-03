package com.controller;

import com.forms.UserAnswersForm;
import com.entities.Answer;
import com.entities.Question;
import com.entities.Questionnaire;
import com.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class ViewerController {
    private QuestionnaireService questionnaireService;

    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view");

        Questionnaire questionnaire = questionnaireService.get(questionnaireId);

        modelAndView.addObject("questionnaire", questionnaire);
        return modelAndView;
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView getAnswers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/view_answers", method = RequestMethod.GET)
    public ModelAndView deleteQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view_answers");

        List<UserAnswersForm> answerList = questionnaireService.getUserAnswers(questionnaireId);

        modelAndView.addObject("answerList", answerList);

        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam ("search") String searchQuery) {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("list");
        List<Questionnaire> questionnaireList = questionnaireService.search(searchQuery);

        modelAndView.addObject("questionnaireList", questionnaireList);
        return modelAndView;
    }



}

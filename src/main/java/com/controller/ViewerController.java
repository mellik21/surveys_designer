package com.controller;

import com.forms.UserAnswersForm;
import com.model.Answer;
import com.model.Question;
import com.model.Questionnaire;
import com.model.UserAnswer;
import com.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ViewerController {
    private QuestionnaireService questionnaireService;
    @Autowired
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @RequestMapping(value="/view", method = RequestMethod.GET)
    public ModelAndView viewQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view");
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        Map<Question, List<Answer>>map = questionnaireService.getQuestionnaireInfo(questionnaireId);
        modelAndView.addObject("map",map);
        modelAndView.addObject("title",questionnaire.getTitle());
        return modelAndView;
    }

    @RequestMapping(value="/view", method = RequestMethod.POST)
    public ModelAndView getAnswers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

    @RequestMapping(value="/view_answers", method = RequestMethod.GET)
    public ModelAndView deleteQuestionnaire(@ModelAttribute("questionnaireId") int questionnaireId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view_answers");

        List<UserAnswersForm> answerList = questionnaireService.getUserAnswers(questionnaireId);

        modelAndView.addObject("answerList", answerList);

        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
        public ModelAndView editQuestionnaire(@ModelAttribute("q") int questionnaireId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        Questionnaire questionnaire = questionnaireService.getById(questionnaireId);
        Map<Question, List<Answer>>map = questionnaireService.getQuestionnaireInfo(questionnaireId);
        modelAndView.addObject("map",map);
        modelAndView.addObject("title",questionnaire.getTitle());
        return modelAndView;
    }


}

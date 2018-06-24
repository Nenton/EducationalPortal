package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.innopolis.stc9.earth_stc9.pojo.Subject;
import ru.innopolis.stc9.earth_stc9.services.ILessonService;
import ru.innopolis.stc9.earth_stc9.services.IPerformanceService;

import java.util.List;

/***
 * @author Aleksandr Tikhonov - created 03.06.2018, modified 02.06.2018 by author
 */

@Controller
@SessionAttributes(value = "id")
public class PerformanceController {
    private static final Logger logger = Logger.getLogger(PerformanceController.class);
    private List<Subject> subjects;

    @Autowired
    private ILessonService lessonService;

    @Autowired
    private IPerformanceService performanceService;

    @RequestMapping(value = "/performance", method = RequestMethod.GET)
    public String getSubjects(Model model, @RequestParam(required = false) String subjectId) {

        logger.info("Method PerformanceController.getSubjects() has been done " + this.getClass().getName());

        if (subjects == null) {
            subjects = lessonService.getSubjects();
        }
        model.addAttribute("subjects", subjects);
        if (subjectId != null) {
            int subId = Integer.parseInt(subjectId);
            model.addAttribute("performance", performanceService.getPerformance(SecurityContextHolder.getContext().getAuthentication().getName(), subId));
            logger.info("If condition of method PerformanceController.getSubjects() has been done, subjectId = " + subjectId + this.getClass().getName());
        }
        return "performance";
    }
}

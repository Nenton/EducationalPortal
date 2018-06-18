package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.services.IInfoService;

/**
 * Controller for show information non registered users
 */
@Controller
public class InfoController {
    private final IInfoService infoService;
    private static final Logger logger = Logger.getLogger(InfoController.class);

    @Autowired
    public InfoController(IInfoService infoService) {
        this.infoService = infoService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    private String doGet(Model model) {
        logger.info("doGet" + this.getClass().getName());
        model.addAttribute("studentsCount", infoService.getStudentsCount());
        model.addAttribute("teachersCount", infoService.getTeachersCount());
        model.addAttribute("subjectsCount", infoService.getSubjectsCount());
        model.addAttribute("lessonsCount", infoService.getLessonsCount());
        return "info";
    }
}

package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for show information non registered users
 */
@Controller
public class InfoController {
    private static final Logger logger = Logger.getLogger(InfoController.class);

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    private String doGet(Model model) {
        logger.info("doGet" + this.getClass().getName());
        return "info";
    }
}

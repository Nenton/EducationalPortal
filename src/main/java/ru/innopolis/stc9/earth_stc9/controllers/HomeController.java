package ru.innopolis.stc9.earth_stc9.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for home page
 */
@Controller

public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage() {
        return "index";
    }
}

package ru.innopolis.stc9.earth_stc9.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Controller for home page
 */
@Controller

public class HomeController extends AbstractController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPage() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String checkAuthentication(Model model, HttpSession httpSession) {
        if (!httpSession.getAttribute("login").toString().isEmpty()) {
            model.addAttribute("login", httpSession.getAttribute("login").toString());
        }
        return "index";
    }
}

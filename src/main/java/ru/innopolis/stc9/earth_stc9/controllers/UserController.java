package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IUsersService;

/**
 * Class NOT REALIZED. For current user
 */
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private IUsersService service;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    protected String doGet(@PathVariable String id, Model model) {
        logger.info("doGet" + this.getClass().getName());
        try {
            int idUser = Integer.parseInt(id);
            User user = service.getUserById(idUser);
            model.addAttribute("user", user);
        } catch (NumberFormatException e) {
            logger.warn(e);
        }
        return "user";
    }
}

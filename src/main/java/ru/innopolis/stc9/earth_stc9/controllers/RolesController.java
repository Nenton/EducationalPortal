package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.earth_stc9.pojo.Role;
import ru.innopolis.stc9.earth_stc9.services.IRoleService;

import java.util.List;

/**
 * Controller for show roles
 */
@Controller
public class RolesController {
    private static final Logger logger = Logger.getLogger(RolesController.class);
    @Autowired
    private IRoleService service;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String doGet(@RequestAttribute String message, Model model) {
        logger.info("doGet" + this.getClass().getName());
        List<Role> roles = service.getRoles();
        model.addAttribute("roles", roles);
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "roles";
    }

    @RequestMapping(value = "/roleAdd", method = RequestMethod.POST)
    public String doPostAddRole(@RequestAttribute String nameRole, Model model) {
        logger.info("doPost" + this.getClass().getName());
        if (nameRole.isEmpty()) {
            return doGet("Передано пустое имя", model);
        } else {
            Role role = new Role(nameRole);
            service.createRole(role);
            return doGet("", model);
        }
    }

    @RequestMapping(value = "/roleDelete", method = RequestMethod.POST)
    public String doPostDeleteole(@RequestAttribute String roleId, Model model) {
        logger.info("doPost" + this.getClass().getName());
        try {
            service.deleteRole(Integer.parseInt(roleId));
        } catch (ClassCastException e) {
            logger.warn("В запрос вместо id передано что-то другое", e);
        }
        return doGet("", model);
    }
}

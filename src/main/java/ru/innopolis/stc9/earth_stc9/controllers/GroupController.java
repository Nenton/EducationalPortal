package ru.innopolis.stc9.earth_stc9.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.Group;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018, updated 02.06.2018
 */

@Controller
@RequestMapping(value = "/groups")
public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class);

    @Autowired
    private IGroupService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getGroups(Model model) {
        logger.info("Method \"getGroups()\" from GroupController done " + this.getClass().getName());
        model.addAttribute("groups", service.getGroups());
        return "groups";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createGroup(Model model, @RequestParam String nameGroup, @RequestParam String descriptionGroup) {
        logger.info("Method \"createGroup()\" from GroupController done " + this.getClass().getName());
        if (!nameGroup.equals("") && !descriptionGroup.equals("")) {
            logger.info("If condition of method \"createGroup\" from GroupController done " + this.getClass().getName());
            service.createGroup(new Group(0, nameGroup, descriptionGroup));
        }
        model.addAttribute("groups", service.getGroups());
        return "groups";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String deleteGroup(Model model, @RequestParam String groupId) {
        logger.info("Method \"deleteGroup()\" from GroupController done " + this.getClass().getName());

        if (groupId != null) {
            logger.info("If condition of method \"deleteGroup\" from GroupController done " + this.getClass().getName());
            logger.info(groupId);
            service.deleteGroup(Integer.parseInt(groupId));
        }
        model.addAttribute("groups", service.getGroups());
        return "groups";
    }
}


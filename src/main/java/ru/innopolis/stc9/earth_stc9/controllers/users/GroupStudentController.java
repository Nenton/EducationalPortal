package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;
import ru.innopolis.stc9.earth_stc9.services.IGroupStudentService;

import java.util.ArrayList;

@Controller
public class GroupStudentController {
    private static final Logger logger = Logger.getLogger(GroupStudentController.class);
    @Autowired
    public IGroupStudentService servicegroup;
    @Autowired
    private IGroupService service;



    @RequestMapping(value = "/studentgroup", method = RequestMethod.GET)
    public String getGroups(Model model) {
        logger.info("Method \"getGroups()\" from GroupController done " + this.getClass().getName());
        model.addAttribute("groups", service.getGroups());
        return "studentgroup";
    }

    @RequestMapping(value = "/studentgroup/getstudentgroup", method = RequestMethod.GET)
    public String getStudentList(@RequestParam String id, Model model) {
        ArrayList<User> studentgrouplist = null;
        int idgroup = Integer.parseInt(id);
            studentgrouplist = servicegroup.getStudentfromGroup(Integer.parseInt(id));
        logger.info("Successful getstudentgroup from the DB" + id);
            model.addAttribute("studentgrop", studentgrouplist);
            return "viewstudentgroup";


    }

    @RequestMapping(value = "/studentgroup/delete", method = RequestMethod.POST)
    public String deleteStudentGroup(@RequestParam String id, @RequestParam String idgroup, Model model) {
        servicegroup.deleteStudentInGroup(Integer.parseInt(id));
        logger.info("Successful deleted from the DB" + id);
        return getStudentList(idgroup, model);

    }


    @RequestMapping(value = "/studentgroup/add", method = RequestMethod.POST)
    public String addStudentGroup(@RequestParam String studentId, @RequestParam String groupId, Model model) {
        if (studentId.isEmpty() && groupId.isEmpty()) {
            model.addAttribute("message", "Необходимо заполнить оба значения");
            return "viewstudentgroup";
        }
        try {
            int studentID = Integer.parseInt(studentId);
            int groupID = Integer.parseInt(groupId);
            servicegroup.addStudentInGroup(new GroupStudents(studentID, groupID));
            logger.info("Successful add in DB" + studentId + groupID);
            return getStudentList(groupId, model);
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Пожалуйста, введите корретное значение");
            return "viewstudentgroup";
        }


    }


}



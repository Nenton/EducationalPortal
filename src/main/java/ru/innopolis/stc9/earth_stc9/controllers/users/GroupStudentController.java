package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IGroupStudentService;

import java.util.ArrayList;

@Controller
public class GroupStudentController {
    @Autowired
    public IGroupStudentService servicegroup;


    @RequestMapping(value = "/studentgroup", method = RequestMethod.GET)
    public String studentgroup(Model model) {
        return "studentgroup";
    }

    @RequestMapping(value = "/studentgroup/getstudentgroup", method = RequestMethod.POST)
    public String getStudentList(@RequestParam String id, Model model) {
        ArrayList<User> studentgrouplist = null;
        try {
            int idgroup = Integer.parseInt(id);
            studentgrouplist = servicegroup.getStudentfromGroup(Integer.parseInt(id));
            if (studentgrouplist.size() == 0) {
                model.addAttribute("message", "К сожалению мы не смогли найти такую группу");
                return "studentgroup";
            }
            model.addAttribute("studentgrop", studentgrouplist);
            return "viewstudentgroup";
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Пожалуйста, введите корретное значение");
            return "studentgroup";
        }

    }

    @RequestMapping(value = "/studentgroup/delete", method = RequestMethod.POST)
    public String deleteStudentGroup(@RequestParam String id, @RequestParam String idgroup, Model model) {
        servicegroup.deleteStudentInGroup(Integer.parseInt(id));
        return getStudentList(idgroup, model);

    }


    @RequestMapping(value = "/studentgroup/add", method = RequestMethod.POST)
    public String addStudentGroup(@RequestParam String id, @RequestParam String studentId, @RequestParam String groupId, Model model) {
        try {
            int identry = Integer.parseInt(id);
            int studentID = Integer.parseInt(studentId);
            int groupID = Integer.parseInt(groupId);
            servicegroup.addStudentInGroup(new GroupStudents(identry, studentID, groupID));
            return getStudentList(groupId, model);
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Пожалуйста, введите корретное значение");
            return "viewstudentgroup";
        }

    }


}



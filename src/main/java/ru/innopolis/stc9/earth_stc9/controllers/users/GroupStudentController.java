package ru.innopolis.stc9.earth_stc9.controllers.users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.earth_stc9.pojo.GroupStudents;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;
import ru.innopolis.stc9.earth_stc9.services.IGroupStudentService;

import java.util.List;

@Controller
public class GroupStudentController {
    private static final Logger logger = Logger.getLogger(GroupStudentController.class);
    private final IGroupStudentService servicegroup;
    private final IGroupService service;

    @Autowired
    public GroupStudentController(IGroupStudentService servicegroup, IGroupService service) {
        this.servicegroup = servicegroup;
        this.service = service;
    }



    /**
     * Страница со списком всех групп
     */

    @RequestMapping(value = "/studentgroup", method = RequestMethod.GET)
    public String getGroups(Model model) {
        logger.info("Method \"getGroups()\" from GroupController done " + this.getClass().getName());
        model.addAttribute("groups", service.getGroups());
        return "studentgroup";
    }

    /**
     * Страница со списком студентов конкретной группы
     */

    @RequestMapping(value = "/studentgroup/{groupname}", method = RequestMethod.GET)
    public String getStudentList(@PathVariable("groupname") Integer groupname, Model model) {
        List<User> studentgrouplist = null;
        studentgrouplist = servicegroup.getStudentfromGroup(groupname);
        logger.info("Successful getstudentgroup from the DB" + groupname);
        model.addAttribute("studentgroup", studentgrouplist);
        model.addAttribute("studentnotgroup", getStudentnotGroup());
        model.addAttribute("group", service.getGroupById(groupname));
        return "viewstudentgroup";

    }

    /**
     * Получение списка студентов не включенных в группы
     */
    private List<User> getStudentnotGroup() {
        return servicegroup.getStudentnotGroup();
    }

    /**
     * Удаление студента из группы
     */

    @RequestMapping(value = "/studentgroupdelete/{id}", method = RequestMethod.POST)
    public String deleteStudentGroup(@PathVariable("id") Integer id, @RequestParam String idgroup, Model model) {
        servicegroup.deleteStudentInGroup(id);
        logger.info("Successful deleted from the DB" + id);
        return getStudentList(Integer.parseInt(idgroup), model);

    }

    /**
     *
     *  Добавление студента в группу
     */

    @RequestMapping(value = "/addstudentgroup", method = RequestMethod.POST)
    public String addStudentGroup(@RequestParam String studentnotgroup, @RequestParam String namegroup, Model model) {
        int studentID = Integer.parseInt(studentnotgroup);
        int groupID = Integer.parseInt(namegroup);
        GroupStudents groupStudents = new GroupStudents(studentID, groupID);
        servicegroup.addStudentInGroup(groupStudents);
        logger.info("Successful add in DB" + studentnotgroup + groupID);
        return getStudentList(groupID, model);


    }

}


package ru.innopolis.stc9.earth_stc9.controllers.groups;

import ru.innopolis.stc9.earth_stc9.controllers.AbstractController;
import ru.innopolis.stc9.earth_stc9.pojo.Group;
import ru.innopolis.stc9.earth_stc9.services.GroupService;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

@WebServlet("/groups")
public class GroupController extends AbstractController {
    private IGroupService service = new GroupService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        logger.info("Method \"doGet\" from GroupController done " + this.getClass().getName());

        req.setAttribute("groups", service.getGroups());
        req.getRequestDispatcher("pages/groups.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Method \"doPost\" from GroupController done " + this.getClass().getName());

        if (!req.getParameter("nameGroup").equals("") && !req.getParameter("descriptionGroup").equals("")) {
            logger.info("1st If condition of Method \"doPost\" from GroupController done " + this.getClass().getName());
            service.createGroup(new Group(0, req.getParameter("nameGroup"), req.getParameter("descriptionGroup")));
        }
        doGet(req, resp);
    }
}

/**
 * @author Aleksandr Tikhonov - created 29.05.2018
 */

package ru.innopolis.stc9.earth_stc9.controllers.groups;

import ru.innopolis.stc9.earth_stc9.controllers.AbstractController;
import ru.innopolis.stc9.earth_stc9.services.GroupService;
import ru.innopolis.stc9.earth_stc9.services.IGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/groups/delete")
public class GroupDeleteController extends AbstractController {
    private IGroupService service = new GroupService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Method \"doPost\" from GroupController done " + this.getClass().getName());

        if (req.getParameter("groupId") != null) {
            logger.info("If condition of Method \"doPost\" from GroupDeleteController done " + this.getClass().getName());
            logger.info(req.getParameter("groupId"));
            service.deleteGroup(Integer.parseInt(req.getParameter("groupId")));
        }
        resp.sendRedirect("/groups");
    }
}

package ru.innopolis.stc9.earth_stc9.controllers;


import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;
import ru.innopolis.stc9.earth_stc9.pojo.Lesson;
import ru.innopolis.stc9.earth_stc9.pojo.User;
import ru.innopolis.stc9.earth_stc9.services.ILessonService;
import ru.innopolis.stc9.earth_stc9.services.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for all need compressed information for user
 */
@WebServlet(urlPatterns = {"/dashboard"})
public class DashboardController extends AbstractController {
    private ILessonService service = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet" + this.getClass().getName());
        String login = ((String) req.getSession().getAttribute("login"));
        List<Lesson> lessons = null;
        if (login != null && !login.isEmpty()) {
            User user = service.getUserByLogin(login);
            switch (user.getRole().getId()) {
                case Roles.STUDENT_ROLE_ID:
                    lessons = service.getLessonsByStudentId(user.getId(), 10);
                    break;
                case Roles.TEACHER_ROLE_ID:
                    lessons = service.getLessonsByTeacherId(user.getId(), 10);
                    break;
                case Roles.ADMIN_ROLE_ID:
                    lessons = service.getLessonsLast(10);
                    break;
                default:
                    resp.sendRedirect("/login?errorMsg=noAccess");
                    break;
            }
        }
        if (lessons != null) {
            req.setAttribute("lessons", lessons);
        }
        req.getRequestDispatcher("/pages/dashboard.jsp").forward(req, resp);
    }
}

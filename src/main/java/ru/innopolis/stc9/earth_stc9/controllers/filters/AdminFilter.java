package ru.innopolis.stc9.earth_stc9.controllers.filters;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.innopolis.stc9.earth_stc9.controllers.users.Roles;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for admin role. Access is granted only admin
 */

@WebFilter(urlPatterns = {"/users", "/roles", "/groups",})
@EnableWebMvc
public class AdminFilter extends AuthFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Method \"doFilter\" from AdminFilter has done");
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        Integer role = (Integer) httpSession.getAttribute("role");
        Object login = httpSession.getAttribute("login");
        if (login != null && !((String) login).isEmpty() && role != null && role == Roles.ADMIN_ROLE_ID) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?errorMsg=noAccess");
        }
    }
}

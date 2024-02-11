package com.gleb.ratingmovies.controller.filter;


import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.dao.entity.UserRole;
import com.gleb.ratingmovies.util.Attribute;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final String WARN_MESSAGE = "Permission denied. Role: ";
    private static final String PERMISSION_DENIED = "Permission denied";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String commandName = servletRequest.getParameter(Parameter.COMMAND);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        UserRole userRole;
        if (session.getAttribute("role") == null) {
            userRole = UserRole.GUEST;
        } else {
            userRole = (UserRole) session.getAttribute("role");
        }
        session.setAttribute(Attribute.ROLE, userRole);
        String roleLine = userRole.toString();
        boolean isAccessAllowed = isAccessAllowed(commandName, roleLine);
        if (isAccessAllowed) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
            httpServletRequest.setAttribute(Attribute.ERROR_MESSAGE, PERMISSION_DENIED);
        }
    }

    private boolean isAccessAllowed(String commandName, String roleLine) {
        if (commandName == null) {
            return true;
        }
        try {
            return UserRole.valueOf(roleLine).isExistCommandName(commandName);
        } catch (IllegalArgumentException e) {
            logger.warn(WARN_MESSAGE + roleLine);
            return false;

        }
    }

}
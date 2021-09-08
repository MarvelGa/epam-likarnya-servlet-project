package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    private static final Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        logger.debug("Command starts");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        logger.debug(String.format("redirect --> %s", Path.PAGE__LOGIN));
        logger.debug("Command finished");
        return Path.PAGE__LOGIN;
    }
}

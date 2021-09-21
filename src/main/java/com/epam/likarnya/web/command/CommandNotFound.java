package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandNotFound implements Command {
    private static final Logger logger = Logger.getLogger(CommandNotFound.class);

    @Override
    public final String execute(final HttpServletRequest request,
                                final HttpServletResponse response) {
        logger.debug("Command starts");

        String errorMessage = "Where are not such command!";
        request.setAttribute("errorMessage", errorMessage);
        logger.error("Set the request attribute: errorMessage --> "
                + errorMessage);
        logger.debug("Command finished");
        return Path.PAGE_ERROR_PAGE;
    }
}

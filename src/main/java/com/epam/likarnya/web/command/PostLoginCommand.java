package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PostLoginCommand implements Command {
    private static final Logger logger = Logger.getLogger(PostLoginCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    public PostLoginCommand() {
    }

    public PostLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        logger.debug("Command starts");
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        logger.trace("Request parameter: loging --> " + email);
        String password = request.getParameter("password");

        String errorMessage;
        String forward = Path.ERROR_PAGE;

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            logger.debug(String.format("forward --> %s", Path.PAGE__LOGIN));
            return Path.PAGE__LOGIN;
        }

        User user = userService.getUserByEmail(email);
        logger.trace("Found in DB: user --> " + user);

        if (user == null || !user.getPassword().equals(encryptPassword(password))) {
            errorMessage = "Wrong login or password";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            logger.debug(String.format("forward --> %s", Path.PAGE__LOGIN));
            return Path.PAGE__LOGIN;
        } else {
            User.Role userRole = user.getRole();
            logger.trace("userRole --> " + userRole);

            if (userRole == User.Role.ADMIN) {
                session.setAttribute("user", user);
                logger.trace("Set the session attribute: user --> " + user);
                logger.debug(String.format("redirect --> %s", Path.COMMAND__ADMIN_CABINET));
                forward = Path.COMMAND__ADMIN_CABINET;
            }

            if (userRole == User.Role.DOCTOR) {
                session.setAttribute("doctor", user);
                logger.trace("Set the session attribute: userRole --> " + userRole);
                logger.debug(String.format("redirect --> %s", Path.COMMAND__DOCTOR_CABINET));
                forward = Path.COMMAND__DOCTOR_CABINET;
            }

            if (userRole == User.Role.NURSE) {
                session.setAttribute("nurse", user);
                logger.trace("Set the session attribute: userRole --> " + userRole);
                logger.debug(String.format("redirect --> %s", Path.COMMAND__NURSE_CABINET));
                forward = Path.COMMAND__NURSE_CABINET;
            }

            logger.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        }
        logger.debug(String.format("forward --> %s", forward));
        logger.debug("Command finished");
        return forward;
    }

    private String encryptPassword(final String password) throws AppException {
        if (Objects.isNull(password) || password.isEmpty()) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            throw new AppException(e.getMessage(), e);
        }
    }
}
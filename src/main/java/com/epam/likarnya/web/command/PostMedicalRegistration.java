package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import com.epam.likarnya.web.validator.DataValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PostMedicalRegistration implements Command {
    private static final Logger logger = Logger.getLogger(PostMedicalRegistration.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    public PostMedicalRegistration() {
    }

    public PostMedicalRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        logger.debug("Command starts");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String category = request.getParameter("category");

        List<String> errorList = new ArrayList<>();

        if (firstName == null || lastName == null || email == null || password==null || role==null || category==null) {
            String errorMessage = "Each field must be filled";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE__MEDIC_REGISTRATION;
        }

        if (userService.getUserByEmail(email) != null) {
            logger.trace("User with this email already exist");
            String errorMessage = "User with this email already exist";
            errorList.add(errorMessage);
        }

        if (!DataValidator.isNameValid(firstName)) {
            logger.trace("Invalid name");
            String errorMessage = "You entered invalid name";
            errorList.add(errorMessage);
        }
        if (!DataValidator.isSurnameValid(lastName)) {
            logger.trace("Invalid surname");
            String errorMessage = "You entered invalid lastName";
            errorList.add(errorMessage);
        }

        if (!DataValidator.isEmailValid(email)) {
            logger.trace("Invalid email");
            String errorMessage = "You entered invalid email";
            errorList.add(errorMessage);
        }

        if (!DataValidator.isPasswordValid(password)) {
            logger.trace("Invalid password");
            String errorMessage = "You entered invalid password";
            errorList.add(errorMessage);
        }

        if (Long.valueOf(category) != 0 && role.equals("NURSE")) {
            logger.trace("NURSE has the category");
            String errorMessage = "NURSE should not have the category!";
            errorList.add(errorMessage);
        }

        if (Long.valueOf(category) == 0 && role.equals("DOCTOR")) {
            logger.trace("Doctor without a category");
            String errorMessage = "Doctor should have the category!";
            errorList.add(errorMessage);
        }

        if (!errorList.isEmpty()) {
            request.setAttribute("errorMessages", errorList);
            logger.debug(String.format("forward --> %s", Path.PAGE__PATIENT_REGISTRATION));
            return Path.PAGE__MEDIC_REGISTRATION;
        } else {
            User newUser;
            Long categoryId = Long.valueOf(category);
            if (categoryId!=0){
                newUser =new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPassword(encryptPassword(password));
                newUser.setRole(User.Role.valueOf(role));
                newUser.setCategoryId(categoryId);
            }else{
                newUser =new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPassword(encryptPassword(password));
                newUser.setRole(User.Role.valueOf(role));
            }

            logger.trace("Saving new user: " + newUser);
            userService.addMedicalWorker(newUser);
            if (newUser.getRole()== User.Role.NURSE){
                logger.debug(String.format("redirect --> %s", Path.COMMAND__NURSES_LIST));
                return Path.COMMAND__NURSES_LIST;
            }
            logger.debug(String.format("redirect --> %s", Path.COMMAND__DOCTORS_LIST));
            return Path.COMMAND__DOCTORS_LIST;
        }

    }

    private String encryptPassword(final String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            return password.hashCode() + password.hashCode() + "";
        }
    }

}

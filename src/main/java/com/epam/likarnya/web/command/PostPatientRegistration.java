package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import com.epam.likarnya.web.validator.DataValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostPatientRegistration implements Command {
    private static final Logger logger = Logger.getLogger(PostPatientRegistration.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    public PostPatientRegistration() {
    }

    public PostPatientRegistration(UserService userService) {
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
        Long category = Long.valueOf(request.getParameter("category"));

        List<String> errorList = new ArrayList<>();

//        if (name == null || lastName == null || bDay == null || gender==null || name.isEmpty() || lastName.isEmpty()) {
//            String errorMessage = "Each field must be filled";
//            request.setAttribute("errorMessage", errorMessage);
//            logger.error("errorMessage --> " + errorMessage);
//            return Path.PAGE__MEDIC_REGISTRATION;
//        }


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

        if (!errorList.isEmpty()) {
            request.setAttribute("errorMessages", errorList);
            logger.debug(String.format("forward --> %s", Path.PAGE__PATIENT_REGISTRATION));
            return Path.PAGE__MEDIC_REGISTRATION;
        } else {
            User newUser;
            if (category!=0){
                newUser =new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRole(User.Role.valueOf(role));
                newUser.setCategoryId(category);
            }else{
                newUser =new User();
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRole(User.Role.valueOf(role));
            }


            logger.trace("Saving new user: " + newUser);
            userService.addMedicalWorker(newUser);
            logger.debug(String.format("redirect --> %s", Path.COMMAND__ADMIN_CABINET));
            return Path.COMMAND__ADMIN_CABINET;
        }

    }

}

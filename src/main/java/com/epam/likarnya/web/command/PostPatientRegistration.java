package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.service.PatientService;
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
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();

    public PostPatientRegistration() {
    }

    public PostPatientRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        logger.debug("Command starts");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String bDay = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");

        List<String> errorList = new ArrayList<>();

        if (name == null || lastName == null || bDay == null || gender == null || name.isEmpty() || lastName.isEmpty()) {
            String errorMessage = "Each field must be filled";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE__PATIENT_REGISTRATION;
        }

        if (!DataValidator.isNameValid(name)) {
            logger.trace("Invalid name");
            String errorMessage = "You entered invalid name";
            errorList.add(errorMessage);
        }
        if (!DataValidator.isSurnameValid(lastName)) {
            logger.trace("Invalid surname");
            String errorMessage = "You entered invalid lastName";
            errorList.add(errorMessage);
        }

        if (!errorList.isEmpty()) {
            request.setAttribute("errorMessages", errorList);
            logger.debug(String.format("forward --> %s", Path.PAGE__PATIENT_REGISTRATION));
            return Path.PAGE__PATIENT_REGISTRATION;
        } else {
            Patient newPatient = new Patient();
            newPatient.setFirstName(name);
            newPatient.setLastName(lastName);
            newPatient.setDateOfBirth(LocalDate.parse(bDay));
            newPatient.setGender(Patient.Gender.valueOf(gender));
            logger.trace("Saving new patient: " + newPatient);
            patientService.addPatient(newPatient);
            logger.debug(String.format("redirect --> %s", Path.COMMAND__ADMIN_CABINET));
            return Path.COMMAND__ADMIN_CABINET;
        }

    }
}

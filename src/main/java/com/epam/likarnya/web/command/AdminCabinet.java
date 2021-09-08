package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminCabinet implements Command {
    private static final Logger logger = Logger.getLogger(AdminCabinet.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<Patient> patients = patientService.getPatientWithOutMedicalCard();
        request.setAttribute("patients", patients);
        return Path.ADMIN_CABINET;
    }
}

package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.service.CategoryService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostCreateMedicalCard implements Command {
    private static final Logger logger = Logger.getLogger(PostCreateMedicalCard.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();
    private CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String doctorId = request.getParameter("doctor");
        String patientId =request.getParameter("patientId");



        return Path.ADMIN_CABINET;
    }
}

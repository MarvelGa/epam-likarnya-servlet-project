package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Category;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.service.CategoryService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddMedicalCard implements Command {
    private static final Logger logger = Logger.getLogger(AddMedicalCard.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();
    private CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        Long patientId = Long.valueOf(request.getParameter("patientId"));
        String category = request.getParameter("category");
        Patient patient = patientService.getPatientById(patientId);
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("patient", patient);
        request.setAttribute("categories", categories);
        if (category != null) {
            if (category.isEmpty()) {
                return Path.PAGE__ADDING_MED_CAD;
            }
            Long categoryId = Long.valueOf(category);
            List<DoctorDTO> doctors = userService.getDoctorsByCategoryId(categoryId);
            request.setAttribute("catValue", categoryId);
            request.setAttribute("doctors", doctors);
        }
        return Path.PAGE__ADDING_MED_CAD;
    }
}

package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Category;
import com.epam.likarnya.service.CategoryService;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DoctorsList implements Command {
    private static final Logger logger = Logger.getLogger(DoctorsList.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        //List<DoctorDTO> doctorsList = userService.getDoctors();
        String category = request.getParameter("category");
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);

//        if (category != null) {
//            if (category.isEmpty()) {
//                return Path.PAGE__DOCTORS;
//            }
//            Long categoryId = Long.valueOf(category);
//            List<DoctorDTO> doctors = userService.getDoctorsWithCountOfPatientsByCategoryId(categoryId);
//            request.setAttribute("catValue", categoryId);
//            request.setAttribute("doctorsList", doctors);
//        }
        if (category==null){
            List<DoctorDTO> doctorsList = userService.getDoctorsWithCountOfPatients();
            request.setAttribute("doctorsList", doctorsList);
        } else {
            if (category.isEmpty()) {
                List<DoctorDTO> doctorsList = userService.getDoctorsWithCountOfPatients();
                request.setAttribute("doctorsList", doctorsList);
                return Path.PAGE__DOCTORS;
            }
            Long categoryId = Long.valueOf(category);
            List<DoctorDTO> doctors = userService.getDoctorsWithCountOfPatientsByCategoryId(categoryId);
            request.setAttribute("catValue", categoryId);
            request.setAttribute("doctorsList", doctors);
        }




        return Path.PAGE__DOCTORS;
    }
}

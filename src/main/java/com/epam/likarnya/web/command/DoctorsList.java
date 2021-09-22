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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorsList implements Command {
    private static final Logger logger = Logger.getLogger(DoctorsList.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();

    public DoctorsList() {
    }

    public DoctorsList(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        String category = request.getParameter("category");
        String sort = request.getParameter("sorting");
        List<DoctorDTO> doctorsList = new ArrayList<>();

        if (category == null) {
            doctorsList = userService.getDoctorsWithCountOfPatients();
        } else {
            if (category.isEmpty()) {
                doctorsList = userService.getDoctorsWithCountOfPatients();
                return getString(request, sort, doctorsList);
            }
            Long categoryId = Long.valueOf(category);
            doctorsList = userService.getDoctorsWithCountOfPatientsByCategoryId(categoryId);
            request.setAttribute("catValue", categoryId);
        }
        return getString(request, sort, doctorsList);
    }

    private String getString(HttpServletRequest request, String sort, List<DoctorDTO> doctorsList) {
        if (sort != null && doctorsList.size() != 0) {
            if (!sort.isEmpty()) {
                if (sort.equals("ASC-NAME")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getFirstName))
                            .collect(Collectors.toList());
                }
                if (sort.equals("DESC-NAME")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getFirstName).reversed())
                            .collect(Collectors.toList());
                }
                if (sort.equals("ASC")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getLastName))
                            .collect(Collectors.toList());
                }
                if (sort.equals("DESC")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getLastName).reversed())
                            .collect(Collectors.toList());
                }
                if (sort.equals("DECREASE")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getCountOfPatient).reversed())
                            .collect(Collectors.toList());
                }
                if (sort.equals("INCREASE")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getCountOfPatient))
                            .collect(Collectors.toList());
                }
                if (sort.equals("CAT-ASC")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getCategory))
                            .collect(Collectors.toList());
                }
                if (sort.equals("CAT-DESC")) {
                    doctorsList = doctorsList.stream()
                            .filter(c -> c != null)
                            .sorted(Comparator.comparing(DoctorDTO::getCategory).reversed())
                            .collect(Collectors.toList());
                }
            }
        }

        request.setAttribute("sort", sort);
        request.setAttribute("doctorsList", doctorsList);

        return Path.PAGE__DOCTORS;
    }
}

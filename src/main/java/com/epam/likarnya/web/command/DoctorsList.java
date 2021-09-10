package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<DoctorDTO> doctorsList = userService.getDoctors();
        request.setAttribute("doctorsList", doctorsList);
        return Path.PAGE__DOCTORS;
    }
}

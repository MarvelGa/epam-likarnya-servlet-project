package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.NurseDTO;
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

public class NursesList implements Command {
    private static final Logger logger = Logger.getLogger(NursesList.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    public NursesList() {
    }

    public NursesList(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<NurseDTO> nursesList = userService.getNurses();
        request.setAttribute("nursesList", nursesList);
        return Path.PAGE__NURSES;
    }
}

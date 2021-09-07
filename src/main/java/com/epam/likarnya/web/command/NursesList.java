package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NursesList implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        return Path.PAGE__NURSES;
    }
}

package com.epam.likarnya.web;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.web.command.Command;
import com.epam.likarnya.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            process(req, resp);
        } catch (AppException e) {
            e.printStackTrace();
        }

    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, AppException {
        String address = Path.PAGE_ERROR_PAGE;
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        try {
            address = command.execute(req, resp);
        } catch (AppException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }
        if (address.contains("controller?command")) {
            resp.sendRedirect(address);
        } else {
            req.getRequestDispatcher(address).forward(req, resp);
        }
    }
}

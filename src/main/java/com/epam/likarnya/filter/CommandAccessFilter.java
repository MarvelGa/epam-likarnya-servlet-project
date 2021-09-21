package com.epam.likarnya.filter;

import com.epam.likarnya.Path;
import com.epam.likarnya.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CommandAccessFilter.class);

    private final Map<User.Role, List<String>> accessMap = new HashMap<User.Role, List<String>>();

    private List<String> notControlling = new ArrayList<String>();

    @Override
    public final void destroy() {
        logger.debug("Filter destruction starts");

        logger.debug("Filter destruction finished");
    }

    @Override
    public final void doFilter(final ServletRequest request,
                               final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        logger.debug("Filter starts");

        if (accessAllowed(request)) {
            logger.debug("Filter finished");
            chain.doFilter(request, response);
        } else {

            String errorMessasge = "You do not have permission "
                    + "to access the requested resource";

            request.setAttribute("errorMessage", errorMessasge);
            logger.trace("Set the request attribute: errorMessage --> "
                    + errorMessasge);

            request.getRequestDispatcher(
                    Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }


    private boolean accessAllowed(final ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getRequestURI().equalsIgnoreCase("/likarnya/changeLocale")) {
            logger.trace("Accessed allowed for changing language");
            return true;
        }
        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (notControlling.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }
        User user = new User() ;

        if ((User) session.getAttribute("user")!=null){
            user = (User) session.getAttribute("user");
        }
        if ((User) session.getAttribute("doctor")!=null){
            user = (User) session.getAttribute("doctor");
        }

        if ((User) session.getAttribute("nurse")!=null){
            user = (User) session.getAttribute("nurse");
        }

        if (Objects.isNull(user)) {
            return false;
        }
        User.Role roleName =  user.getRole();

        return accessMap.get(roleName).contains(commandName);
    }

    @Override
    public final void init(final FilterConfig fConfig)
            throws ServletException {
        logger.debug("Filter initialization starts");

        accessMap.put(User.Role.ADMIN, asList(fConfig.getInitParameter("restrictedAdmin")));
        accessMap.put(User.Role.DOCTOR, asList(fConfig.getInitParameter("restrictedDoctor")));
        accessMap.put(User.Role.NURSE, asList(fConfig.getInitParameter("restrictedNurse")));
        logger.trace("Access map  --> " + accessMap);

        notControlling = asList(fConfig.getInitParameter("not-controlling"));
        logger.trace("Out of control commands --> " + notControlling);

        logger.debug("Filter initialization finished");
    }

    private List<String> asList(final String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}

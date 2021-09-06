package com.epam.likarnya.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ResourceBundle;

public class LanguageListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(LanguageListener.class);
    public static final String PROPERTIES_FILE = "resources";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.trace("Initialize language starts");
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);
        servletContextEvent.getServletContext().setAttribute("resources", rb);
        LOG.trace("Initialize language finished");;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}

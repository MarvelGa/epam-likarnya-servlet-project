package com.epam.likarnya.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class ContextListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ContextListener.class);

    @Override
    public final void contextDestroyed(final ServletContextEvent event) {
        log("Servlet context destruction starts");
        log("Servlet context destruction finished");
    }

    @Override
    public final void contextInitialized(final ServletContextEvent event) {
        log("Servlet context has starts");
        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();
        log("Servlet context has finished the init");
    }


    private void initLog4J(final ServletContext servletContext) {
        log("Log4J is start for init");
        try {
            String homeDir = servletContext.getRealPath("/");
            File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
            PropertyConfigurator.configure(propertiesFile.toString());
            logger.debug("Log4j was init");
        } catch (Exception ex) {
            log("There is the problem with configure Log4j" + ex);
        }
        log("Log4J init was finished");
    }

    private void initCommandContainer() {
        try {
            Class.forName(
                    "com.epam.likarnya.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException(
                    "There is problem with init to Command Container");
        }
    }


    private void log(final String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}

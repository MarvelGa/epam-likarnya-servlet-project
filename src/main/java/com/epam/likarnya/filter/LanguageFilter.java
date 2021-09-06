package com.epam.likarnya.filter;

import com.epam.likarnya.Locales;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class LanguageFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LanguageFilter.class);
    public static final String PROPERTIES_FILE = "resources";

    @Override
    public final void doFilter(final ServletRequest request,
                               final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        HttpServletRequest req = (HttpServletRequest) request;
        LOG.trace("Request uri --> " + req.getRequestURI());
        String locale = request.getParameter("locale");
        if (locale == null) {
            locale = "en";
        }
        LOG.trace("Request locale --> " + locale);
        LOG.trace("Change language to: " + locale);
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE, Locales.getLocale(locale));
        LOG.trace("Set attribute: resources ");
        req.getSession().setAttribute("resources", rb);
        LOG.trace("Set attribute: locale ");
        req.getSession().setAttribute("locale", locale);
        LOG.debug("Filter finished");

        ((HttpServletResponse) response).sendRedirect(req.getHeader("referer"));
    }

    @Override
    public final void init(final FilterConfig filterConfig)
            throws ServletException {
        LOG.debug("Filter initialization starts");
        LOG.debug("Filter initialization finished");
    }

    @Override
    public final void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }
}

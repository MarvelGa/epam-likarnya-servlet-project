package com.epam.likarnya.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EncodingFilter.class);

    private String encoding;

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

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            logger.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);

        }

        logger.debug("Filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public final void init(final FilterConfig fConfig)
            throws ServletException {
        logger.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        logger.trace("Encoding from web.xml --> " + encoding);
        logger.debug("Filter initialization finished");
    }
}

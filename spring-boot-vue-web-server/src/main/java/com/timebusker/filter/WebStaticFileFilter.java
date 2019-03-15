package com.timebusker.filter;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

public class WebStaticFileFilter implements Filter {

    private static final String WEB_STATIC_VERSION = "_v0=" + new Date().getTime();
    private static final String QUESTION_MARK = "?";
    private static final String AND_SYSBOLS = "&";
    // private static final String REGEX_PARTTERN = "/js/.*\\.js((?!WEB_STATIC_VERSION).).*$";
    private static final String REGEX_PARTTERN = "/js/";

    private static final Pattern PATTERN = Pattern.compile(REGEX_PARTTERN);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURL().toString();
        String queryStr = httpRequest.getQueryString();
        if (url.indexOf(REGEX_PARTTERN) > 0 && StringUtils.isNotBlank(queryStr)) {
            if (queryStr.indexOf(WEB_STATIC_VERSION) > 0) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpResponse.sendRedirect(url + QUESTION_MARK + queryStr + AND_SYSBOLS + WEB_STATIC_VERSION);
            }
        } else if (url.indexOf(REGEX_PARTTERN) > 0 && StringUtils.isBlank(queryStr)) {
            httpResponse.sendRedirect(url + QUESTION_MARK + WEB_STATIC_VERSION);
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

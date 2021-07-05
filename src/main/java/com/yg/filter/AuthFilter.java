package com.yg.filter;

import com.yg.util.SessionName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute(SessionName.session_name) == null) {
            req.setAttribute("error", "请登录！");
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        } else {
            chain.doFilter(request, response);
        }

    }

    public void destroy() {

    }
}

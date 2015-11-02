package ru.wv.security;

import ru.wv.beans.users.LoginSession;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginSession loginSession = (LoginSession) ((HttpServletRequest) request).getSession().getAttribute("loginSession");

        if (((HttpServletRequest) request).getServletPath().startsWith("/admin/index.xhtml")) {
            if (loginSession != null && loginSession.isLoggedIn())
                ((HttpServletResponse) response).sendRedirect("/admin/welcome.xhtml");
        } else {
            if (loginSession == null || !loginSession.isLoggedIn()) {
                ((HttpServletResponse) response).sendRedirect("/admin/index.xhtml");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

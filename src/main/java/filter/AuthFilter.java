package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginURI = req.getContextPath() + "/login.jsp";
        String loginServletURI = req.getContextPath() + "/login";
        String registerURI = req.getContextPath() + "/register.jsp";
        String registerServletURI = req.getContextPath() + "/register";

        boolean loggedIn = session != null && session.getAttribute("userEmail") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(loginServletURI);
        boolean registerRequest = req.getRequestURI().equals(registerURI) || req.getRequestURI().equals(registerServletURI);

        System.out.println("AuthFilter: URI=" + req.getRequestURI() + ", LoggedIn=" + loggedIn + ", LoginRequest=" + loginRequest + ", RegisterRequest=" + registerRequest);

        if (loggedIn || loginRequest || registerRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }

    public void destroy() {}
}
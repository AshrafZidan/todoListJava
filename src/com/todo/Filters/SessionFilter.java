package com.todo.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

@WebFilter(filterName = "SessionFilter",value = "/*")
public class SessionFilter implements Filter {
    private ArrayList<String> urlList;


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getServletPath();
        boolean allowedRequest = false;


        if (urlList.contains(url)) {
            allowedRequest = true;

        }

        if (!allowedRequest) {
            HttpSession session = request.getSession(false);
            if (null == session.getAttribute("loggedInUser")) {
                RequestDispatcher rd=req.getRequestDispatcher("/login/login.jsp");
                rd.include(req, resp);

            }else {
                chain.doFilter(req, resp);

            }

        }
        else {
//            response.sendRedirect("login");
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
//            String urls = config.getInitParameter("avoid-urls");
        String urls = "/login";

        StringTokenizer token = new StringTokenizer(urls, ",");

            urlList = new ArrayList<String>();

            while (token.hasMoreTokens()) {
                urlList.add(token.nextToken());

            }
        }

}

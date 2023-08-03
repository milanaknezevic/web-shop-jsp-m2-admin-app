package com.example.adminapp.controllers;

import com.example.adminapp.beans.AdminBean;
import com.example.adminapp.beans.CategoryBean;
import com.example.adminapp.beans.LogBean;
import com.example.adminapp.beans.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "adminController", value = "/admin-controller")
public class AdminController extends HttpServlet {
    private static final String SIGN_IN = "/WEB-INF/pages/sign-in.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String ERROR = "/WEB-INF/pages/error.jsp";
    public AdminController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String address = "/WEB-INF/pages/sign-in.jsp";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        session.setAttribute("notification", "");
        if (action == null || action.equals("")) {
            address = SIGN_IN;
        } else if (action.equals("sign-out")) {
            session.invalidate();
        } else if (action.equals("sign-in")) {
            String username = request.getParameter("korisnicko_ime");
            String password = request.getParameter("lozinka");
            AdminBean admin = new AdminBean();
            if (admin.login(username, password)) {
                session.setAttribute("admin", admin);
                LogBean logBean = new LogBean();
                UserBean userBean = new UserBean();
                CategoryBean categoryBean = new CategoryBean();
                session.setAttribute("logBean", logBean);
                session.setAttribute("userBean", userBean);
                session.setAttribute("categoryBean", categoryBean);
                address = USERS;
            }
            else {
                session.setAttribute("notification","Invalid credentials!");
            }
        }
        else {
          /*  AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
            if (adminBean == null || !adminBean.isLoggedIn()) {
                address = SIGN_IN;*/
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

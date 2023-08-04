package com.example.adminapp.controllers;

import com.example.adminapp.beans.AdminBean;
import com.example.adminapp.beans.CategoryBean;
import com.example.adminapp.beans.LogBean;
import com.example.adminapp.beans.UserBean;
import com.example.adminapp.models.User;
import com.example.adminapp.models.enums.Role;
import com.example.adminapp.models.enums.Status;
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
    private static final String ADD_USER = "/WEB-INF/pages/add-new-user.jsp";
    private static final String UPDATE_USER = "/WEB-INF/pages/update-user.jsp";
    private static final String LOGS = "/WEB-INF/pages/logs.jsp";
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
            AdminBean adminBean = new AdminBean();
            if (adminBean.login(username, password)) {
                session.setAttribute("adminBean", adminBean);
                LogBean logBean = new LogBean();
                UserBean userBean = new UserBean();
                CategoryBean categoryBean = new CategoryBean();
                session.setAttribute("logBean", logBean);
                session.setAttribute("userBean", userBean);
                session.setAttribute("categoryBean", categoryBean);
                address = USERS;
            } else {
                session.setAttribute("notification", "Invalid credentials!");
            }
        } else {
            AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
            if (adminBean == null || !adminBean.isLoggedIn()) {
                address = SIGN_IN;
            } else {
                UserBean userBean = (UserBean) session.getAttribute("userBean");
                CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
                switch (action) {
                    case "users":
                        address = USERS;
                        break;
                    case "add-new-user":
                        address = ADD_USER;
                        if (request.getParameter("submit") != null) {
                            String roleValue = request.getParameter("rola");
                            Role role = Role.fromValue(Integer.parseInt(roleValue));
                            User user = new User(0, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), request.getParameter("password"),
                                    request.getParameter("city"), request.getParameter("avatar"),
                                    request.getParameter("email"),
                                    role, Status.ACTIVE);
                            System.out.println("user" + user);

                            if (userBean.addUser(user)) {
                                address = USERS;
                            }
                        }
                        break;
                    case "update-user":
                        address =UPDATE_USER;
                        int updateId = Integer.parseInt(request.getParameter("id"));
                        User updateUser = userBean.getById(updateId);
                        userBean.setUser(updateUser);
                        if (request.getParameter("submit") != null) {
                            String roleValue = request.getParameter("rola");
                            Role role = Role.fromValue(Integer.parseInt(roleValue));

                            String statusValue = request.getParameter("status");
                            Status status = Status.fromValue(Integer.parseInt(statusValue));

                            User user = new User(updateId, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), updateUser.getLozinka(),request.getParameter("city"),
                                    request.getParameter("avatar"),request.getParameter("email"), role, status);
                            System.out.println("status"+status);
                            if (userBean.update(user)) {
                                address = USERS;
                            }
                        }
                        break;
                    case "delete-user":
                        int id = Integer.parseInt(request.getParameter("id"));
                        User user = userBean.getById(id);
                        user.setStatus(Status.BLOCKED);
                        userBean.changeStatus(user);
                        address = USERS;
                        break;
                    case "logs":
                        address = LOGS;
                        break;
                    default:
                        address = ERROR;
                        break;
                }
            }
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

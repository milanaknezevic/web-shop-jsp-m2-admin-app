package com.example.adminapp.controllers;

import com.example.adminapp.beans.*;
import com.example.adminapp.dao.CategoryDAO;
import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.Category;
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
import java.util.Arrays;

@WebServlet(name = "adminController", value = "/admin-controller")
public class AdminController extends HttpServlet {
    private static final String SIGN_IN = "/WEB-INF/pages/sign-in.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String ADD_USER = "/WEB-INF/pages/add-new-user.jsp";
    private static final String UPDATE_USER = "/WEB-INF/pages/update-user.jsp";
    private static final String LOGS = "/WEB-INF/pages/logs.jsp";
    private static final String CATEGORIES = "/WEB-INF/pages/categories.jsp";
    private static final String VIEW_ATTRIBUTES = "/WEB-INF/pages/view-attributes.jsp";
    private static final String ADD_CATEGORY = "/WEB-INF/pages/add-new-category.jsp";
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
            response.sendRedirect(request.getContextPath()+"/admin-controller");
            return;
        } else if (action.equals("sign-in")) {
            String username = request.getParameter("korisnicko_ime");
            String password = request.getParameter("lozinka");
            AdminBean adminBean = new AdminBean();
            if (adminBean.login(username, password)) {
                session.setAttribute("adminBean", adminBean);
                LogBean logBean = new LogBean();
                UserBean userBean = new UserBean();
                CategoryBean categoryBean = new CategoryBean();
                AttributeBean attributeBean = new AttributeBean();
                session.setAttribute("logBean", logBean);
                session.setAttribute("userBean", userBean);
                session.setAttribute("categoryBean", categoryBean);
                session.setAttribute("attributeBean", attributeBean);
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
                AttributeBean attributeBean = (AttributeBean) session.getAttribute("attributeBean");
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
                        address = UPDATE_USER;
                        int updateId = Integer.parseInt(request.getParameter("id"));
                        User updateUser = userBean.getById(updateId);
                        userBean.setUser(updateUser);
                        if (request.getParameter("submit") != null) {
                            String roleValue = request.getParameter("rola");
                            Role role = Role.fromValue(Integer.parseInt(roleValue));

                            String statusValue = request.getParameter("status");
                            Status status = Status.fromValue(Integer.parseInt(statusValue));

                            User user = new User(updateId, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), updateUser.getLozinka(), request.getParameter("city"),
                                    request.getParameter("avatar"), request.getParameter("email"), role, status);
                            System.out.println("status" + status);
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
                    case "categories":
                        address = CATEGORIES;
                        break;
                    case "view-attributes":
                        address = VIEW_ATTRIBUTES;
                        break;
                    case "delete-categories":
                        int id1 = Integer.parseInt(request.getParameter("id"));
                        Category category = CategoryBean.getById(id1);
                        categoryBean.deleteCategory(category);
                        address = CATEGORIES;
                        break;
                    case "add-new-category":
                        address = ADD_CATEGORY;
                        String categoryName = request.getParameter("name");
                        String[] attributeNames = request.getParameterValues("attribute_name[]");
                        String[] attributeTypes = request.getParameterValues("attribute_type[]");
                        if (request.getParameter("submit") != null) {
                            Category category1 = new Category(0, categoryName);
                            int categoryId = categoryBean.addCategory(category1);

                            if (categoryId != -1) {
                                if (attributeNames != null && attributeTypes != null) {
                                    for (int i = 0; i < attributeNames.length; i++) {
                                        String attributeName = attributeNames[i];
                                        String attributeType = attributeTypes[i];
                                        Attribute attribute = new Attribute(0, attributeName, attributeType);
                                        attributeBean.insertAttribute(attribute, categoryId);

                                    }
                                }
                                address = CATEGORIES;
                            }
                        }


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

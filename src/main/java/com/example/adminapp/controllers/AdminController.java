package com.example.adminapp.controllers;

import com.example.adminapp.beans.*;
import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.Category;
import com.example.adminapp.models.User;
import com.example.adminapp.models.enums.Status;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;

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
    private static final String UPDATE_CATEGORY = "/WEB-INF/pages/update-category.jsp";
    private static final String ADD_ATTRIBUTE = "/WEB-INF/pages/add-new-attribute.jsp";
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
            response.sendRedirect(request.getContextPath() + "/admin-controller");
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
                            User user = new User(0, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), request.getParameter("password"),
                                    request.getParameter("city"),
                                    request.getParameter("email"),
                                    Status.ACTIVE);
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
                           String statusValue = request.getParameter("status");
                            Status status = Status.fromValue(Integer.parseInt(statusValue));

                            User user = new User(updateId, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), updateUser.getLozinka(), request.getParameter("city"),
                                    request.getParameter("email"), status);
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
                        Integer id3 = Integer.parseInt(request.getParameter("id"));
                        if (id3 != null) {
                            Category category = categoryBean.getById(id3);
                            categoryBean.setCategory(category);
                        }
                        break;
                    case "delete-categories":
                        int id1 = Integer.parseInt(request.getParameter("id"));
                        Category category = categoryBean.getById(id1);
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
                                System.out.println("attributeTypes " + attributeTypes);
                                System.out.println("attributeNames " + attributeNames);
                                if (attributeNames != null) {
                                    for (int i = 0; i < attributeNames.length; i++) {
                                        String attributeName = attributeNames[i];
                                        String attributeType = "";
                                        if (attributeTypes != null) {
                                            attributeType = attributeTypes[i];
                                        } else attributeType = "0";
                                        String tip = "";
                                        if (attributeType.equals("0")) {
                                            tip = "STRING";
                                        } else if (attributeType.equals("1")) {
                                            tip = "INT";
                                        } else {
                                            tip = "DOUBLE";
                                        }
                                        Attribute attribute = new Attribute(0, attributeName, tip);
                                        System.out.println("attribute " + attribute);
                                        attributeBean.insertAttribute(attribute, categoryId);

                                    }
                                }
                                address = CATEGORIES;
                            }
                        }
                        break;
                    case "update-category":
                        address = UPDATE_CATEGORY;
                        int updateCategoryId = Integer.parseInt(request.getParameter("id"));
                        Category updateCategory = categoryBean.getById(updateCategoryId);
                        categoryBean.setCategory(updateCategory);
                        if (request.getParameter("submit") != null) {
                            String categoryName1 = request.getParameter("name");
                            updateCategory.setNaziv(categoryName1);
                            //update kategoriju
                            categoryBean.update(updateCategory);

                            // Ovdje ćemo koristiti ključ "name" polja kako bismo dohvatili poslane atribute
                            Enumeration<String> parameterNames = request.getParameterNames();
                            while (parameterNames.hasMoreElements()) {
                                String paramName = parameterNames.nextElement();
                                if (paramName.startsWith("attributeName_")) {
                                    int attributeId = Integer.parseInt(paramName.substring("attributeName_".length()));
                                    String attributeName = request.getParameter("attributeName_" + attributeId);
                                    String attributeType = request.getParameter("type_" + attributeId);
                                    System.out.println("attributeType " + attributeType);
                                    String tip = "";
                                    if (attributeType.equals("0")) {
                                        tip = "STRING";
                                    } else if (attributeType.equals("1")) {
                                        tip = "INT";
                                    } else {
                                        tip = "DOUBLE";
                                    }

                                    Attribute attribute = new Attribute(attributeId, attributeName, tip);
                                    System.out.println("atribut " + attribute);
                                    attributeBean.update(attribute);

                                }
                            }

                            address = CATEGORIES;
                        }
                        break;
                    case "delete-attribute":
                        int id2 = Integer.parseInt(request.getParameter("idAttr"));
                        attributeBean.deleteAttribute(id2);
                        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                        Category category1 = categoryBean.getById(categoryId);
                        categoryBean.setCategory(category1);
                        address = VIEW_ATTRIBUTES;
                        break;
                    case "add-new-attribute":
                        address = ADD_ATTRIBUTE;
                        if (request.getParameter("submit") != null) {
                            int categoryId1 = Integer.parseInt(request.getParameter("id"));
                            String type=request.getParameter("type");
                            String tip = "";
                            if (type.equals("0")) {
                                tip = "STRING";
                            } else if (type.equals("1")) {
                                tip = "INT";
                            } else {
                                tip = "DOUBLE";
                            }
                            Attribute attribute = new Attribute(0, request.getParameter("name"), tip);
                            if(attributeBean.insertAttribute(attribute, categoryId1))
                            {
                                Category category2 = categoryBean.getById(categoryId1);
                                categoryBean.setCategory(category2);
                                address=VIEW_ATTRIBUTES;
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

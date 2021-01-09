package controllers;


import dao.AppUserDao;
import dao.impl.MySQLUserDao;
import error.ValidationError;
import models.AppUser;
import services.UsersAppService;
import services.impl.UsersAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login", ""})
public class LoginServlet extends HttpServlet {

    private UsersAppService service;

    @Override
    public void init() throws ServletException {
        service = new UsersAppServiceImpl(new MySQLUserDao());

//        AppUserDao dao = new MySQLUserDao();
//        AppUser user = AppUser.UserBuilder.getBuilder()
//                .fistName("admin")
//                .lastName("admin")
//                .email("admin")
//                .password("admin")
//                .admin(true)
//                .build();
//        dao.saveUser(user);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = null;
        String password = null;


        if (email != null && password != null) {
            req.setAttribute(ServletUtils.USER_EMAIL, email);
            req.setAttribute(ServletUtils.USER_PASSWORD, password);
            doPost(req, resp);
            return;
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String email = req.getParameter(ServletUtils.USER_EMAIL);
        String password = req.getParameter(ServletUtils.USER_PASSWORD);


        if (email == null && password == null) {
            email = (String) req.getAttribute(ServletUtils.USER_EMAIL);
        }

        boolean credsInvalid = !service.isEmailAndPasswordValid(email, password);
        if (credsInvalid) {
            ArrayList<ValidationError> errors = new ArrayList<>();
            boolean isEmailExsist = !service.isEmailExsist(email);
            if (isEmailExsist) {
                ValidationError validationEmailError = new ValidationError(ServletUtils.EMAIL_ERROR_HEADER, ServletUtils.EMAIL_ERROR_MESSAGE);
                errors.add(validationEmailError);
            }
            if (!isEmailExsist) {
                ValidationError validationPasswordError = new ValidationError(ServletUtils.PASSWORD_ERROR_HEADER, ServletUtils.PASSWORD_ERROR_MESSAGE);
                errors.add(validationPasswordError);
            }

            req.setAttribute(ServletUtils.ERRORS_ATTRIBUTE_NAME, errors);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        AppUser appUser = service.getAppUserByEmail(email);
        req.getSession().setAttribute(ServletUtils.APP_USER, appUser);


        String loggedUser = service.getUserNameFromEmail(email);
        req.getSession().setAttribute(ServletUtils.USER_FULL_NAME, loggedUser);

        boolean userIsAdmin = service.isUserIsAdmin(email);
        req.getSession().setAttribute(ServletUtils.IS_USER_IS_ADMIN, userIsAdmin);
        req.getRequestDispatcher("patientList").forward(req, resp);
    }


}

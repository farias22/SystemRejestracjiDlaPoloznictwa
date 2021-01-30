package controllers;


import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
import error.ValidationError;
import models.AppUser;
import services.PatientListAppService;
import services.UsersAppService;
import services.impl.PatientListAppServiceImpl;
import services.impl.UsersAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static services.impl.PatientListAppServiceImpl.generatePatientList;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login", ""})
public class LoginServlet extends HttpServlet {

    private UsersAppService usersService;
    private PatientListAppService patientService;

    @Override
    public void init() throws ServletException {
        usersService = new UsersAppServiceImpl(new MySQLUserDao());

        patientService = new PatientListAppServiceImpl(new MySQLPatientDao());

//        AppUserDao dao = new MySQLUserDao();
//        AppUser user = AppUser.UserBuilder.getBuilder()
//                .fistName("Paweł")
//                .lastName("Jabłonowski")
//                .login("admin")
//                .password("admin")
//                .admin(true)
//                .build();
//        dao.saveUser(user);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = null;
        String password = null;


        if (login != null && password != null) {
            req.setAttribute(ServletUtils.USER_LOGIN, login);
            req.setAttribute(ServletUtils.USER_PASSWORD, password);
            doPost(req, resp);
            return;
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String login = req.getParameter(ServletUtils.USER_LOGIN);
        String password = req.getParameter(ServletUtils.USER_PASSWORD);


        if (login == null && password == null) {
            login = (String) req.getAttribute(ServletUtils.USER_LOGIN);
        }

        boolean credsInvalid = !usersService.isLoginAndPasswordValid(login, password);
        if (credsInvalid) {
            ArrayList<ValidationError> errors = new ArrayList<>();
            boolean isEmailExsist = !usersService.isEmailExsist(login);
            if (isEmailExsist) {
                ValidationError validationEmailError = new ValidationError(ServletUtils.LOGIN_ERROR_HEADER, ServletUtils.LOGIN_ERROR_MESSAGE);
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

        AppUser appUser = usersService.getAppUserByLogin(login);
        req.getSession().setAttribute(ServletUtils.APP_USER, appUser);


        String loggedUser = usersService.getUserNameFromEmail(login);
        req.getSession().setAttribute(ServletUtils.USER_FULL_NAME, loggedUser);

        boolean userIsAdmin = usersService.isUserIsAdmin(login);
        req.getSession().setAttribute(ServletUtils.IS_USER_IS_ADMIN, userIsAdmin);

        generatePatientList(patientService, req);


        req.getRequestDispatcher("/patientList.jsp").forward(req, resp);
    }


}

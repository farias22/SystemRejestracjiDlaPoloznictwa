package controllers;

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


@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {



    private UsersAppService usersService;


    @Override
    public void init() throws ServletException {
        usersService = new UsersAppServiceImpl(new MySQLUserDao());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String imie = req.getParameter(ServletUtils.USER_FIRST_NAME);
        String nazwisko = req.getParameter(ServletUtils.USER_LAST_NAME);
        String login = req.getParameter(ServletUtils.USER_LOGIN);
        AppUser appUserOld = (AppUser) req.getSession().getAttribute(ServletUtils.APP_USER);
        boolean resetPassword = false;
        if (req.getParameter(ServletUtils.RESET_PASSWORD) != null
                && req.getParameter(ServletUtils.RESET_PASSWORD).equals("on")) {
            resetPassword = true;
        }
        String password1 = req.getParameter(ServletUtils.PASSWORD1) != null ? req.getParameter(ServletUtils.PASSWORD1) : "";
        String password2 = req.getParameter(ServletUtils.PASSWORD2) != null ? req.getParameter(ServletUtils.PASSWORD2) : "";


        AppUser appUserNew = AppUser.UserBuilder.getBuilder()
                .fistName(imie)
                .lastName(nazwisko)
                .login(login)
                .password(resetPassword ? password1 : appUserOld.getPassword())
                .admin(appUserOld.isAdmin())
                .build();
        ArrayList<ValidationError> editingError = new ArrayList<>();


        boolean domainUnavailable = !usersService.domainAvailable(login);
        boolean condition1 = domainUnavailable && !appUserOld.getLogin().equals(appUserNew.getLogin());
        boolean condition2 = resetPassword && !password1.equals(password2);

        if (condition1 || condition2) {
            if (condition1) {
                ValidationError creatingNewUserErrorEnd = new ValidationError(ServletUtils.LOGIN_IN_USE, ServletUtils.LOGIN_IN_USE_MESSAGE);
                editingError.add(creatingNewUserErrorEnd);
            }
            if (condition2) {
                ValidationError passwordDoNotMatch = new ValidationError(ServletUtils.RESET_PASSWORD_ERROR, ServletUtils.RESET_PASSWORD_ERROR_MESSAGE);
                editingError.add(passwordDoNotMatch);
            }
            req.setAttribute(ServletUtils.EDITING_USER_ERROR, editingError);
            req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
            return;

        }

        usersService.editUser(appUserOld, appUserNew);
        req.getSession().setAttribute(ServletUtils.APP_USER, appUserNew);
        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

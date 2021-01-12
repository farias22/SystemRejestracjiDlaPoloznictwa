package controllers;

import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
import error.ValidationError;
import models.AppUser;
import models.Patient;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


@WebServlet(name = "EditUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {



    private UsersAppService service;


    @Override
    public void init() throws ServletException {
        service = new UsersAppServiceImpl(new MySQLUserDao());

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
        String email = req.getParameter(ServletUtils.USER_EMAIL);
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
                .email(email)
                .password(resetPassword ? password1 : appUserOld.getPassword())
                .admin(appUserOld.isAdmin())
                .build();
        ArrayList<ValidationError> editingError = new ArrayList<>();


        boolean domainUnavailable = !service.domainAvailable(email);
        boolean condition1 = domainUnavailable && !appUserOld.getEmail().equals(appUserNew.getEmail());
        boolean condition2 = resetPassword && !password1.equals(password2);

        if (condition1 || condition2) {
            if (condition1) {
                ValidationError creatingNewUserErrorEnd = new ValidationError(ServletUtils.EMAIL_IN_USE, ServletUtils.EMAIL_IN_USE_MESSAGE);
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

        service.editUser(appUserOld, appUserNew);
        req.getSession().setAttribute(ServletUtils.APP_USER, appUserNew);
        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

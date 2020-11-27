package controllers;


import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
import error.ValidationError;
import models.AppUser;
import models.Patient;
import services.PatientListAppService;
import services.RegistrationAppService;
import services.impl.PatientListAppServiceImpl;
import services.impl.RegistrationAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "AddNewUserServlet", value = "/addUser")
public class AddNewUserServlet extends HttpServlet {


    private RegistrationAppService service;


    @Override
    public void init() throws ServletException {
        service = new RegistrationAppServiceImpl(new MySQLUserDao());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String imie = req.getParameter(ServletUtils.USER_FIRST_NAME);
        String nazwisko = req.getParameter(ServletUtils.USER_LAST_NAME);
        String email = req.getParameter(ServletUtils.USER_MAIL_REGISTRATION);
        String password = req.getParameter(ServletUtils.USER_MAIL_REGISTRATION);


        boolean admin = false;
        if (req.getParameter(ServletUtils.USER_ADMIN) != null
                && req.getParameter(ServletUtils.USER_ADMIN).equals("on")) {
            admin = true;
        }

            AppUser appUser = AppUser.UserBuilder.getBuilder()
                    .fistName(imie)
                    .lastName(nazwisko)
                    .email(email)
                    .password(password)
                    .admin(admin)
                    .build();
        ArrayList<ValidationError> creatingNewUserError = new ArrayList<>();

        boolean domainUnavailable = !service.domainAvailable(email);

        if (domainUnavailable){
                ValidationError creatingNewUserErrorEnd= new ValidationError(ServletUtils.EMAIL_IN_USE, ServletUtils.EMAIL_IN_USE_MESSAGE);
            creatingNewUserError.add(creatingNewUserErrorEnd);

            req.setAttribute(ServletUtils.CREATING_NEW_USER_ERROR, creatingNewUserError);
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
            return;

        }

        service.addUser(appUser);

            req.getRequestDispatcher("patientList").forward(req, resp);
        }
    }

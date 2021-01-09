package controllers;

import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
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





        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

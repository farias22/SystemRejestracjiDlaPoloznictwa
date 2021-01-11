package controllers;

import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
import models.AppUser;
import services.PatientListAppService;
import services.UsersAppService;
import services.impl.PatientListAppServiceImpl;
import services.impl.UsersAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static services.impl.PatientListAppServiceImpl.getCurrentSearchedAppUsersList;


@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private UsersAppService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new UsersAppServiceImpl(new MySQLUserDao());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getParameter(ServletUtils.USER_ID);
        AppUser appUser = service.getAppUserById(Long.valueOf(parameter));
        service.deleteUser(appUser);
        getCurrentSearchedAppUsersList(req,service);


        req.getRequestDispatcher("searchUser").forward(req, resp);
    }

}

package controllers;

import dao.impl.MySQLUserDao;
import models.AppUser;
import services.UsersAppService;
import services.impl.UsersAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static services.impl.PatientListAppServiceImpl.getCurrentSearchedAppUsersList;


@WebServlet(name = "ResetUserPasswordServlet", value = "/resetPassword")
public class ResetUserPasswordServlet extends HttpServlet {

    private UsersAppService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = new UsersAppServiceImpl(new MySQLUserDao());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getParameter(ServletUtils.USER_ID);
        AppUser appUser = usersService.getAppUserById(Long.valueOf(parameter));
        usersService.resetUserPassword(appUser);
        getCurrentSearchedAppUsersList(req, usersService);

        req.getRequestDispatcher("searchUser").forward(req, resp);
    }

}

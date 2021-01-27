package controllers;


import dao.impl.MySQLUserDao;
import models.AppUser;
import models.PatientExtended;
import services.impl.UsersAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static services.impl.PatientListAppServiceImpl.getCurrentSearchedAppUsersList;

@WebServlet(name = "AddToBasketServlet", value = "/addToBasket")
public class AddToBasketServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long parameter = Long.valueOf(req.getParameter(ServletUtils.USER_ID));
        List<PatientExtended> list = (List<PatientExtended>)req.getSession().getAttribute(ServletUtils.PATIENT_LIST);
        for (PatientExtended patientExtended : list) {
            if (patientExtended.getId().equals(parameter)){
                patientExtended.addToBasket();
                break;
            }
        }
        req.getSession().setAttribute(ServletUtils.PATIENT_LIST, list);
        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

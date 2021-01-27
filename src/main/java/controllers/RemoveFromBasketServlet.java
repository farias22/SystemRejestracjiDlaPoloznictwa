package controllers;


import models.PatientExtended;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveFromBasketServlet", value = "/removeFromBasket")
public class RemoveFromBasketServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long parameter = Long.valueOf(req.getParameter(ServletUtils.USER_ID));
        List<PatientExtended> list = (List<PatientExtended>)req.getSession().getAttribute(ServletUtils.PATIENT_LIST);
        for (PatientExtended patientExtended : list) {
            if (patientExtended.getId().equals(parameter)){
                patientExtended.removeFromBasket();
                break;
            }
        }
        req.getSession().setAttribute(ServletUtils.PATIENT_LIST, list);
        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

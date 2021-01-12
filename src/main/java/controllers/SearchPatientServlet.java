package controllers;


import dao.impl.MySQLPatientDao;
import models.Patient;
import services.PatientListAppService;
import services.impl.PatientListAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SearchPatientServlet", value = "/searchPatient")
public class SearchPatientServlet extends HttpServlet {



    private PatientListAppService patientService;


    @Override
    public void init() throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/searchPatient.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter(ServletUtils.SEARCH);

        List<Patient> resultList = patientService.getSearchingResults(value);

        if (resultList.size()==0){
            req.setAttribute(ServletUtils.NO_RESULTS_PATEMETER, "no results");
        }


        req.setAttribute(ServletUtils.SEARCH_LIST,resultList);


        req.getRequestDispatcher("/searchPatient.jsp").forward(req, resp);

    }

}
package controllers;

import dao.impl.MySQLPatientDao;
import models.Patient;
import services.PatientListAppService;
import services.impl.PatientListAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExportToXLSServlet", value = "/exportList")
public class ExportToXLSServlet extends HttpServlet {


    private PatientListAppService patientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Patient> patientList = patientService.getPatientList();

        patientService.exportListToXLS(patientList);

        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

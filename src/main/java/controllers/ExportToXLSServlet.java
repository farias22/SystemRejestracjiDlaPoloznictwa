package controllers;

import dao.impl.MySQLPatientDao;
import jxl.write.WriteException;
import models.Patient;
import reports.PatientsReportsGenerator;
import reports.ReportsList;
import services.PatientListAppService;
import services.impl.PatientListAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExportToXLSServlet", value = "/exportList")
public class ExportToXLSServlet extends HttpServlet {


    private PatientListAppService patientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao(), new PatientsReportsGenerator());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Patient> patientList = patientService.getPatientList();

        try {
            patientService.exportListToXLS(patientList);

//            Runtime.getRuntime().exec("/home/pawel/Dokumenty/test/test.xls");
        } catch (WriteException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

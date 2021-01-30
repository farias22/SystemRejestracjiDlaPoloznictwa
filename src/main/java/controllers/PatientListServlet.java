package controllers;


import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
import jxl.write.WriteException;
import models.Patient;
import models.comparators.PatientComparator;
import org.apache.poi.ss.usermodel.Workbook;
import reportsModule.PatientsReportsGenerator;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static services.impl.PatientListAppServiceImpl.generatePatientList;


@WebServlet(name = "PatientListServlet", value = "/patientList")
public class PatientListServlet extends HttpServlet {


    private PatientListAppService patientService;


    @Override
    public void init() {

        patientService = new PatientListAppServiceImpl(new MySQLPatientDao(), new PatientsReportsGenerator());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/patientList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Long> checkboxList = req.getParameterMap().entrySet().stream()
                .filter(x -> x.getKey().startsWith("check_")).map(x -> x.getValue()[0]).map(y -> Long.valueOf(y)).collect(Collectors.toList());


        if (req.getParameter("generateXls") != null) {

            List<Patient> patientListByID = patientService.getpatientListByID(checkboxList);
            Workbook workbook;
            try {
                workbook = patientService.exportListToXLS(patientListByID);
                resp.setHeader("Content-Disposition", "inline; filename*=UTF-8''ListaPacjentek.xlsx");
                resp.setHeader("Content-Type", "application/octet-stream");
                workbook.write(resp.getOutputStream());
                workbook.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }

        }

        req.getRequestDispatcher("/patientList.jsp").forward(req, resp);

    }
}

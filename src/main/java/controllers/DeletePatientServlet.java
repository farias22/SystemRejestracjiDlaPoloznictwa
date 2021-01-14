package controllers;

import dao.impl.MySQLPatientDao;
import models.Patient;
import models.PatientExtended;
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

import static models.PatientExtended.updatePatientListValue;


@WebServlet(name = "DeletePatientServlet", value = "/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    private PatientListAppService patientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long parameter = Long.valueOf(req.getParameter(ServletUtils.PATIENT_ID));

        patientService.deletePatient(parameter);


        List<PatientExtended> list = updatePatientListValue(req, patientService);

        req.getSession().setAttribute(ServletUtils.PATIENT_LIST, list);
        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

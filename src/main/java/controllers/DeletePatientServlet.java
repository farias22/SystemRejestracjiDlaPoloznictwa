package controllers;

import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
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


@WebServlet(name = "DeletePatientServlet", value = "/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    private PatientListAppService patientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getParameter(ServletUtils.PATIENT_ID);

        patientService.deletePatient(Long.valueOf(parameter));

        req.getRequestDispatcher("patientList").forward(req, resp);
    }

}

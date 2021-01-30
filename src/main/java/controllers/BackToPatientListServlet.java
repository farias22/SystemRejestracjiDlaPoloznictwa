package controllers;

import dao.impl.MySQLPatientDao;
import services.PatientListAppService;
import services.impl.PatientListAppServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static services.impl.PatientListAppServiceImpl.generatePatientList;

@WebServlet(name = "BackServlet", value = "/back")
public class BackToPatientListServlet extends HttpServlet {

    private PatientListAppService service;

    @Override
    public void init() throws ServletException {
        service = new PatientListAppServiceImpl(new MySQLPatientDao());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        generatePatientList(service, req);

        req.getRequestDispatcher("/patientList").forward(req, resp);

    }
}
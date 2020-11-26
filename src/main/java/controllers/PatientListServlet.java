package controllers;



import dao.AppPatientDao;
import dao.AppUserDao;
import dao.impl.MySQLPatientDao;
import dao.impl.MySQLUserDao;
import models.AppUser;
import models.Patient;
import services.PatientListAppService;
import services.RegistrationAppService;
import services.impl.PatientListAppServiceImpl;
import services.impl.RegistrationAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@WebServlet(name = "PatientListServlet", value = "/patientList")
public class PatientListServlet extends HttpServlet {


    private PatientListAppService service;

    @Override
    public void init() throws ServletException {
        service = new PatientListAppServiceImpl(new MySQLPatientDao());
        AppPatientDao dao = new MySQLPatientDao();
//        Patient patient = Patient.PatientBuilder.getBuilder()
//                .hospitalizationDate()
//                .comment("komentarz")
//                .prescribingDoctor("Agnieszka Mrozińska")
//                .refferingDoctor("Mędraś")
//                .diagnosis("Pacjentka jest w ciąży")
//                .phoneNumber("7321031020")
//                .foreigner(true)
//                .pesel("9892929292")
//                .firstName("Zbigniew")
//                .lastName("Stonoga")
//                .lastPeriodDate()
//                .scheludedRegistration(true)
//                .isActive()
//                .pragnancyAge(36)
//                .build();
//        Patient patient1 = Patient.PatientBuilder.getBuilder()
//                .hospitalizationDate()
//                .comment("no comment")
//                .prescribingDoctor("Agnieszka Mrozińska")
//                .refferingDoctor("Mędraś")
//                .diagnosis("Pacjentka jest w ciąży")
//                .phoneNumber("7321031020")
//                .pesel("9892929292")
//                .foreigner(true)
//                .firstName("Lila")
//                .lastName("Kotowska")
//                .lastPeriodDate()
//                .scheludedRegistration(true)
//                .isActive()
//                .pragnancyAge(36)
//                .build();
//        dao.savePatient(patient);
//        dao.savePatient(patient1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Patient> patientList = service.getPatientList();
        req.setAttribute(ServletUtils.PATIENT_LIST, patientList);

        req.getRequestDispatcher("/patientList.jsp").forward(req, resp);

    }
}

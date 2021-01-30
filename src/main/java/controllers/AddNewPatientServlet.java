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
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import static services.impl.PatientListAppServiceImpl.generatePatientList;


@WebServlet(name = "AddNewPatientServlet", value = "/addPatient")
public class AddNewPatientServlet extends HttpServlet {


    private PatientListAppService patientService;


    @Override
    public void init() throws ServletException {
        patientService = new PatientListAppServiceImpl(new MySQLPatientDao());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> availableDateList = patientService.getAvailableDateList(0L);
        req.setAttribute(ServletUtils.AVAILABLE_DATE_LIST, availableDateList);


        req.getRequestDispatcher("/addPatient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String imie = req.getParameter(ServletUtils.PATIENT_FIRST_NAME);
        String nazwisko = req.getParameter(ServletUtils.PATIENT_LAST_NAME);
        boolean foreigner = false;
        if (req.getParameter(ServletUtils.PATIENT_IS_FOREIGNER) != null
                && req.getParameter(ServletUtils.PATIENT_IS_FOREIGNER).equals("on")) {
            foreigner = true;
        }
        String pesel = req.getParameter(ServletUtils.PATIENT_PESEL);
        String phoneNumber = req.getParameter(ServletUtils.PATIENT_PHONE_NUMER);
        boolean scheludedRegistration = false;
        if (req.getParameter(ServletUtils.PATIENT_SHELUDED_REGISTRATION) != null
                && req.getParameter(ServletUtils.PATIENT_SHELUDED_REGISTRATION).equals("on")) {
            scheludedRegistration = true;
        }

        String diagnosis = req.getParameter(ServletUtils.PATIENT_DIAGNOSIS);
        String lastPeriodStr = req.getParameter(ServletUtils.PATIENT_LAST_PERIOD_DATE);
        Date lastPeriodDate = null;
        try {
            lastPeriodDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastPeriodStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int pragnancyAge = Integer.valueOf(req.getParameter(ServletUtils.PATIENT_PREGNENCY_AGE));
        String refferingDoctor = req.getParameter(ServletUtils.PATIENT_REFFERING_DOCTOR);
        String prescribingDoctor = (String) req.getSession().getAttribute(ServletUtils.USER_FULL_NAME);
        String comment = req.getParameter(ServletUtils.PATIENT_COMMENT);

        Date hospitalizationDate = null;
        if (scheludedRegistration) {
            hospitalizationDate = patientService.hospitalizationDateCounterForScheduledRegistration(lastPeriodDate, pragnancyAge, 0L);
        } else {

            hospitalizationDate = patientService.hospitalizationDateSetterForNotScheduledRegistration(req);
        }


        Patient patient = Patient.PatientBuilder.getBuilder()
                .firstName(imie)
                .lastName(nazwisko)
                .foreigner(foreigner)
                .pesel(pesel)
                .phoneNumber(phoneNumber)
                .scheludedRegistration(scheludedRegistration)
                .diagnosis(diagnosis)
                .lastPeriodDate(lastPeriodDate)
                .pragnancyAge(pragnancyAge)
                .hospitalizationDate(hospitalizationDate)
                .refferingDoctor(refferingDoctor)
                .prescribingDoctor(prescribingDoctor)
                .comment(comment)
                .isActive(true)
                .build();

        patientService.save(patient);

        String x = new SimpleDateFormat("yyyy-MM-dd").format(hospitalizationDate);
        JOptionPane
                .showMessageDialog(null, "Pacjentka "+imie+" "+nazwisko+" została\nzapisana na datę "+x);

        generatePatientList(patientService, req);

        req.getRequestDispatcher("/patientList.jsp").forward(req, resp);
    }

}

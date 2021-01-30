package controllers;


import dao.impl.MySQLPatientDao;
import models.Patient;
import models.comparators.HospitalizationDateComparator;
import models.comparators.PatientComparator;
import services.PatientListAppService;
import services.impl.PatientListAppServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static services.impl.PatientListAppServiceImpl.generatePatientList;


@WebServlet(name = "EditPatientServlet", value = "/editPatient")
public class EditPatientServlet extends HttpServlet {


    private PatientListAppService service;
    private Patient editedPatient = null;

    @Override
    public void init() throws ServletException {
        service = new PatientListAppServiceImpl(new MySQLPatientDao());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        editedPatient = service.getPatientById(Long.valueOf(req.getParameter(ServletUtils.PATIENT_ID)));


        req.setAttribute(ServletUtils.EDITED_PATIENT, editedPatient);

        generatePregnancyAgeValues(req);
        generateAvailableHospitalizationDate(req);

        req.getRequestDispatcher("/editPatient.jsp").forward(req, resp);
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
        boolean active = true;
        if (req.getParameter(ServletUtils.ACTIVE) != null
                && req.getParameter(ServletUtils.ACTIVE).equals("on")) {
            active = false;
        }
        String pesel = req.getParameter(ServletUtils.PATIENT_PESEL);
        String phoneNumber = req.getParameter(ServletUtils.PATIENT_PHONE_NUMER);
        boolean scheduledRegistration = false;
        if (req.getParameter(ServletUtils.PATIENT_SHELUDED_REGISTRATION) != null
                && req.getParameter(ServletUtils.PATIENT_SHELUDED_REGISTRATION).equals("on")) {
            scheduledRegistration = true;
        }

        String diagnosis = req.getParameter(ServletUtils.PATIENT_DIAGNOSIS);
        String lastPeriodStr = req.getParameter(ServletUtils.PATIENT_LAST_PERIOD_DATE);
        Date lastPeriodDate = null;
        try {
            lastPeriodDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastPeriodStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int pregnancyAge = Integer.valueOf(req.getParameter(ServletUtils.PATIENT_PREGNENCY_AGE));
        String referringDoctor = req.getParameter(ServletUtils.PATIENT_REFFERING_DOCTOR);
        String prescribingDoctor = (String) req.getSession().getAttribute(ServletUtils.USER_FULL_NAME);
        String comment = req.getParameter(ServletUtils.PATIENT_COMMENT);

        Date hospitalizationDate;
        if (scheduledRegistration) {

            hospitalizationDate = service.hospitalizationDateCounterForScheduledRegistration(lastPeriodDate, pregnancyAge, editedPatient.getId());

        } else {

            hospitalizationDate = service.hospitalizationDateSetterForNotScheduledRegistration(req);
        }


        Patient patient = Patient.PatientBuilder.getBuilder()
                .firstName(imie)
                .lastName(nazwisko)
                .foreigner(foreigner)
                .pesel(pesel)
                .phoneNumber(phoneNumber)
                .scheludedRegistration(scheduledRegistration)
                .diagnosis(diagnosis)
                .lastPeriodDate(lastPeriodDate)
                .pragnancyAge(pregnancyAge)
                .hospitalizationDate(hospitalizationDate)
                .refferingDoctor(referringDoctor)
                .prescribingDoctor(prescribingDoctor)
                .comment(comment)
                .isActive(active)
                .build();

        service.updatePatient(editedPatient, patient);

        generatePatientList(service, req);

        req.getRequestDispatcher("patientList").forward(req, resp);
    }

    private void generatePregnancyAgeValues(HttpServletRequest req) {
        String pattern1 = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
        String lastPeriodDate = simpleDateFormat1.format(editedPatient.getLastPeriodDate());

        Integer pregAge = editedPatient.getPregnancyAge();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 28; i < pregAge; i++) {
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<>();
        for (int i = pregAge + 1; i < 43; i++) {
            list2.add(i);
        }
        req.getSession().setAttribute(ServletUtils.LAST_PERIOD_DATE_PRESENT_VALUE, lastPeriodDate);
        req.getSession().setAttribute(ServletUtils.PREGNANCY_AGE_PRESENT_VALUE, pregAge);
        req.getSession().setAttribute(ServletUtils.PREGNANCY_AGE_LIST1, list1);
        req.getSession().setAttribute(ServletUtils.PREGNANCY_AGE_LIST2, list2);
    }

    private void generateAvailableHospitalizationDate(HttpServletRequest req) {

        List<String> availableDateList = service.getAvailableDateList(editedPatient.getId());
        String pattern2 = "dd.MM.yyyy";
        Date hospitalizationDate = editedPatient.getHospitalizationDate();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
        String hospitalizationDate2 = simpleDateFormat2.format(hospitalizationDate);
        List<String> before = new ArrayList<>();
        List<String> after = new ArrayList<>();
        for (String s : availableDateList) {
            if (HospitalizationDateComparator.before(hospitalizationDate2,s)){
                before.add(s);
            }
            if (HospitalizationDateComparator.after(hospitalizationDate2,s)){
                after.add(s);
            }
        }


        req.setAttribute(ServletUtils.HOSPITALIZATION_DATE, hospitalizationDate2);


        req.setAttribute(ServletUtils.AVAILABLE_DATE_LIST1, before);
        req.setAttribute(ServletUtils.AVAILABLE_DATE_LIST2, after);
    }

}

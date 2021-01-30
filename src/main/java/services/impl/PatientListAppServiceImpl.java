package services.impl;

import dao.AppPatientDao;
import jxl.write.WriteException;
import models.AppUser;
import models.Patient;
import models.comparators.PatientComparator;
import org.apache.poi.ss.usermodel.Workbook;
import reportsModule.PatientsReportsGenerator;
import reportsModule.ReportsList;
import services.PatientListAppService;
import services.UsersAppService;
import utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static data.converter.DataParser.parseStringToDateFormatddMMyyyy;

public class PatientListAppServiceImpl implements PatientListAppService {

    private AppPatientDao appPatientDao;
    private PatientsReportsGenerator reports;

    public PatientListAppServiceImpl(AppPatientDao appPatientDao) {
        this.appPatientDao = appPatientDao;
    }

    public PatientListAppServiceImpl(AppPatientDao appPatientDao, PatientsReportsGenerator reportsGenerator) {
        this.appPatientDao = appPatientDao;
        this.reports = reportsGenerator;
    }

    public PatientListAppServiceImpl(PatientsReportsGenerator reportsGenerator) {
        this.reports = reportsGenerator;
    }

    @Override
    public List<Patient> getPatientList() {
        return appPatientDao.getPatientList();
    }


    @Override
    public List<Patient> getSearchingResults(String search) {
        return appPatientDao.getSearchingResults(search);
    }

    @Override
    public Patient getPatientById(Long id) {
        return appPatientDao.getPatientById(id);
    }

    @Override
    public void save(Patient patient) {
        appPatientDao.savePatient(patient);
    }

    @Override
    public void deletePatient(Long id) {
        appPatientDao.deletePatient(id);
    }

    @Override
    public void updatePatient(Patient oldData, Patient newData) {
        appPatientDao.updatePatient(oldData, newData);
    }

    @Override
    public boolean isHospitalizationDateAvailable(Date data, Long idPatient) {
        return appPatientDao.isHospitalizationDateAvailable(data, idPatient);
    }

    public Date hospitalizationDateCounterForScheduledRegistration(Date dataStart, int age, Long idPatient) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataStart);
        calendar.add(Calendar.WEEK_OF_MONTH, age);
        Date result = calendar.getTime();
        boolean isHospitalizationDateAvailable = appPatientDao.isHospitalizationDateAvailable(result, idPatient);
        while (!isHospitalizationDateAvailable) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(result);
            calendar2.add(Calendar.DAY_OF_MONTH, 1);
            result = calendar2.getTime();

            isHospitalizationDateAvailable = appPatientDao.isHospitalizationDateAvailable(result, idPatient);

        }
        return result;
    }

    public Date hospitalizationDateSetterForNotScheduledRegistration(HttpServletRequest req) {

        return parseStringToDateFormatddMMyyyy(req.getParameter(ServletUtils.CHOOSEN_HOSPITALIZATION_DATE));

    }


    public static void getCurrentSearchedAppUsersList(HttpServletRequest req, UsersAppService service) {
        String value = (String) req.getSession().getAttribute(ServletUtils.SEARCH_USER);
        List<AppUser> resultList = service.getSearchingResults(value);
        if (resultList.size() == 0) {
            req.setAttribute(ServletUtils.NO_RESULTS_PATEMETER, "no results");
        }


        req.getSession().setAttribute(ServletUtils.SEARCHED_USERS_LIST, resultList);
    }


    @Override
    public Workbook exportListToXLS(List<Patient> patientList) throws IOException, WriteException {
        reports = new PatientsReportsGenerator();
        return reports.generate(ReportsList.GENERATE_SELECTED_PATIENT_LIST, patientList);
    }

    @Override
    public List<String> getAvailableDateList(Long idPatient) {
        return appPatientDao.getAvailableDateList(idPatient);
    }

    @Override
    public List<Patient> getpatientListByID(List<Long> idList) {
        return appPatientDao.getpatientListByID(idList);
    }

    public static void generatePatientList(PatientListAppService patientService, HttpServletRequest req){
        List<Patient> list =patientService.getPatientList();

        Collections.sort(list, new PatientComparator());


        req.getSession().setAttribute(ServletUtils.PATIENT_LIST, list);
    }
}

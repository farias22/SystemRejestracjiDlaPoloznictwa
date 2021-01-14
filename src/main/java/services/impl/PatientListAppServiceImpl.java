package services.impl;

import dao.AppPatientDao;
import models.AppUser;
import models.Patient;
import reports.PatientsReportsGenerator;
import reports.ReportsList;
import services.PatientListAppService;
import services.UsersAppService;
import utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientListAppServiceImpl implements PatientListAppService {

    private AppPatientDao appPatientDao;
    private PatientsReportsGenerator reports;

    public PatientListAppServiceImpl(AppPatientDao appPatientDao) {
        this.appPatientDao = appPatientDao;
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
    public boolean isHospitalizationDateAvailable(Date data) {
        return appPatientDao.isHospitalizationDateAvailable(data);
    }

    public Date hospitalizationDateCounterForScheduledRegistration(Date dataStart, int age) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataStart);
        calendar.add(Calendar.WEEK_OF_MONTH, age);
        Date result = calendar.getTime();
        boolean isHospitalizationDateAvailable = appPatientDao.isHospitalizationDateAvailable(result);
        while (!isHospitalizationDateAvailable) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(result);
            calendar2.add(Calendar.DAY_OF_MONTH, 1);
            result = calendar2.getTime();

            isHospitalizationDateAvailable = appPatientDao.isHospitalizationDateAvailable(result);

        }
        return result;
    }

    public Date hospitalizationDateCounterForNotScheduledRegistration() {

        Calendar c = Calendar.getInstance();
        c.set(1900, Calendar.JANUARY, 1);

        return c.getTime();
    }


    public static void getCurrentSearchedAppUsersList(HttpServletRequest req, UsersAppService service) {
        String value = (String) req.getSession().getAttribute(ServletUtils.SEARCH_USER);
        List<AppUser> resultList = service.getSearchingResults(value);
        if (resultList.size()==0){
            req.setAttribute(ServletUtils.NO_RESULTS_PATEMETER, "no results");
        }


        req.getSession().setAttribute(ServletUtils.SEARCHED_USERS_LIST,resultList);
    }


    @Override
    public void exportListToXLS(List<Patient> patientList) {
    reports.generate(ReportsList.GENERATE_SELECTED_PATIENT_LIST, patientList);
    }
}

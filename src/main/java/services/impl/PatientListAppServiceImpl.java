package services.impl;

import dao.AppPatientDao;
import dao.AppUserDao;
import models.Patient;
import services.PatientListAppService;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientListAppServiceImpl implements PatientListAppService {

    AppPatientDao appPatientDao;


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
    public void updatePatitnt(Patient oldData, Patient newData) {
        appPatientDao.updatePatient(oldData, newData);
    }

    @Override
    public boolean isHospitalizationDateAvailable(Date data) {
        return appPatientDao.isHospitalizationDateAvailable(data);
    }

    public Date hospitalizationDateCounterForScheludedRegistration(Date dataStart, int age) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataStart);
        calendar.add(Calendar.WEEK_OF_MONTH, age);
        Date result = calendar.getTime();
        boolean isHospitalizationDateAvailable = appPatientDao.isHospitalizationDateAvailable(result);
        while (!isHospitalizationDateAvailable) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(result);
            calendar2.add(Calendar.DAY_OF_MONTH, 1);
            Date date = calendar2.getTime();
            result = date;
            isHospitalizationDateAvailable = appPatientDao.isHospitalizationDateAvailable(result);

        }
        return result;
    }

    public Date hospitalizationDateCounterForNotScheludedRegistration() {

        Calendar c = Calendar.getInstance();
        c.set(1900,0,1);

        Date date = c.getTime();

        return date;
    }

}

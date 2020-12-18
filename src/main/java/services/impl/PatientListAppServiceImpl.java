package services.impl;

import dao.AppPatientDao;
import dao.AppUserDao;
import models.Patient;
import services.PatientListAppService;

import javax.xml.crypto.Data;
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
    public void save(Patient patient) {
        appPatientDao.savePatient(patient);
    }

    @Override
    public boolean isHospitalizationDateAvailable(Date data) {
        return appPatientDao.isHospitalizationDateAvailable(data);
    }
}

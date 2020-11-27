package services.impl;

import dao.AppPatientDao;
import dao.AppUserDao;
import models.Patient;
import services.PatientListAppService;

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


}

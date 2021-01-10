package dao;

import models.AppUser;
import models.Patient;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface AppPatientDao {


    void savePatient(Patient patient);

    void deletePatient(Long id);

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

    Patient getPatientById(Long id);

    boolean isHospitalizationDateAvailable(Date date);

}

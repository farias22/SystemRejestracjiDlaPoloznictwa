package dao;

import models.AppUser;
import models.Patient;

import java.util.List;

public interface AppPatientDao {


    void savePatient(Patient patient);

    void deletePatient(Patient patient);

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

}

package services;

import models.Patient;

import java.util.List;

public interface PatientListAppService {

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

    void save(Patient patient);

}

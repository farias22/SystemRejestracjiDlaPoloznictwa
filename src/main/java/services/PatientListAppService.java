package services;

import models.Patient;

import java.util.List;

public interface PatientListAppService {

    List<Patient> getPatientList();

    void save(Patient patient);

}

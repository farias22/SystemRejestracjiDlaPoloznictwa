package services;

import models.Patient;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PatientListAppService {

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

    void save(Patient patient);

    void deletePatient(Long id);

    boolean isHospitalizationDateAvailable(Date data);

}

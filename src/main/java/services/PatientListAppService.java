package services;

import models.Patient;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PatientListAppService {

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

    Patient getPatientById(Long id);

    void save(Patient patient);

    void deletePatient(Long id);

    void updatePatitnt(Patient oldData, Patient newData);

    boolean isHospitalizationDateAvailable(Date data);

    Date hospitalizationDateCounterForScheludedRegistration(Date dataStart, int age);

    Date hospitalizationDateCounterForNotScheludedRegistration();

}

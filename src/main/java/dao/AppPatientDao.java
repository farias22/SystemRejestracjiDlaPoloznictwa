package dao;


import models.Patient;
import java.util.Date;
import java.util.List;

public interface AppPatientDao {


    void savePatient(Patient patient);

    void deletePatient(Long id);

    void updatePatient(Patient oldData, Patient newData);

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

    Patient getPatientById(Long id);

    boolean isHospitalizationDateAvailable(Date date);

}

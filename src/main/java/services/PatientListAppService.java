package services;

import jxl.write.WriteException;
import models.Patient;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface PatientListAppService {

    List<Patient> getPatientList();

    List<Patient> getSearchingResults(String search);

    Patient getPatientById(Long id);

    void save(Patient patient);

    void deletePatient(Long id);

    void updatePatient(Patient oldData, Patient newData);

    boolean isHospitalizationDateAvailable(Date data);

    Date hospitalizationDateCounterForScheduledRegistration(Date dataStart, int age);

    Date hospitalizationDateCounterForNotScheduledRegistration();

    void exportListToXLS(List<Patient> patientList) throws IOException, WriteException;

}

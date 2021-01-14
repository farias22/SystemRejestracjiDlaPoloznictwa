package reports;

import models.Patient;
import reports.reportsList.GenerateSelectedPatientList;

import java.util.List;

public class PatientsReportsGenerator {


    public void generate(ReportsList report, List<Patient> patientList){

        switch (report){
            case GENERATE_SELECTED_PATIENT_LIST:
                new GenerateSelectedPatientList().generate(patientList);
        }

    }

}

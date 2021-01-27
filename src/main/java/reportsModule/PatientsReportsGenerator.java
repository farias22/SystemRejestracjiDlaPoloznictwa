package reportsModule;

import jxl.write.WriteException;
import models.PatientExtended;
import reportsModule.reportsList.GenerateSelectedPatientList;

import java.io.IOException;
import java.util.List;

public class PatientsReportsGenerator {


    public void generate(ReportsList report, List<PatientExtended> patientList) throws IOException, WriteException {

        switch (report){
            case GENERATE_SELECTED_PATIENT_LIST:
                new GenerateSelectedPatientList().generate(patientList);
        }

    }

}

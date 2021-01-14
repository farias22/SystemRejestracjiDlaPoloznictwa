package models.comparators;

import models.Patient;
import models.PatientExtended;

import java.util.Comparator;

public class PatientComparator implements Comparator<PatientExtended> {


    @Override
    public int compare(PatientExtended patient1, PatientExtended patient2) {
        int value =0;

        if (patient1.getHospitalizationDate().compareTo(patient2.getHospitalizationDate())!=0) {
            value = patient1.getHospitalizationDate().compareTo(patient2.getHospitalizationDate());
        }
        else {
            value = patient1.getLastName().compareTo(patient2.getLastName());
        }
    return value;
    }


}

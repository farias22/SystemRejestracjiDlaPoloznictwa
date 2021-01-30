package models.comparators;

import models.Patient;

import java.util.Comparator;

public class PatientComparator implements Comparator<Patient> {


    @Override
    public int compare(Patient patient1, Patient patient2) {
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

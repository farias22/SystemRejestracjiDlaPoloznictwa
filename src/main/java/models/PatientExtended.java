package models;

import java.util.ArrayList;
import java.util.List;

public class PatientExtended extends Patient {

    boolean basket;


    public boolean isBasket() {
        return basket;
    }

    public void setBasket() {
        this.basket = false;
    }



    public void addToBasket(){
        this.basket=true;
    }

    public void removeFromBasket(){
        this.basket=false;
    }

    public static List<PatientExtended> transferList(List<Patient> list){
        List<PatientExtended> resultList = new ArrayList<>();

        for (Patient patient : list) {

            resultList.add(transferPatient(patient));

//            PatientExtended pe = new PatientExtended();
//            pe.setId(patient.getId());
//            pe.setRegistrationDate(patient.getRegistrationDate());
//            pe.setHospitalizationDate(patient.getHospitalizationDate());
//            pe.setPregnancyAge(patient.getPregnancyAge());
//            pe.setFirstName(patient.getFirstName());
//            pe.setLastName(patient.getLastName());
//            pe.setForeigner(patient.isForeigner());
//            pe.setPesel(patient.getPesel());
//            pe.setPhoneNumber(patient.getPhoneNumber());
//            pe.setScheludedRegistration(patient.isScheludedRegistration());
//            pe.setDiagnosis(patient.getDiagnosis());
//            pe.setLastPeriodDate(patient.getLastPeriodDate());
//            pe.setRefferingDoctor(patient.getRefferingDoctor());
//            pe.setPrescribingDoctor(patient.getPrescribingDoctor());
//            pe.setComment(patient.getComment());
//            pe.setActive(patient.isActive());
//            pe.setBasket();
//            resultList.add(pe);
        }
        return resultList;
    }

    public static PatientExtended transferPatient(Patient patient){
        PatientExtended pe = new PatientExtended();


            pe.setId(patient.getId());
            pe.setRegistrationDate(patient.getRegistrationDate());
            pe.setHospitalizationDate(patient.getHospitalizationDate());
            pe.setPregnancyAge(patient.getPregnancyAge());
            pe.setFirstName(patient.getFirstName());
            pe.setLastName(patient.getLastName());
            pe.setForeigner(patient.isForeigner());
            pe.setPesel(patient.getPesel());
            pe.setPhoneNumber(patient.getPhoneNumber());
            pe.setScheludedRegistration(patient.isScheludedRegistration());
            pe.setDiagnosis(patient.getDiagnosis());
            pe.setLastPeriodDate(patient.getLastPeriodDate());
            pe.setRefferingDoctor(patient.getRefferingDoctor());
            pe.setPrescribingDoctor(patient.getPrescribingDoctor());
            pe.setComment(patient.getComment());
            pe.setActive(patient.isActive());
            pe.setBasket();

        return pe;
    }

}

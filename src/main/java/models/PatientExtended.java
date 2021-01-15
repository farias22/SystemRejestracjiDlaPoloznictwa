package models;

import services.PatientListAppService;
import utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PatientExtended extends Patient {

    boolean basket;


    public boolean isBasket() {
        return basket;
    }

    public void setBasket(boolean basket) {
        this.basket = basket;
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

        }
        return resultList;
    }

    private static PatientExtended transferPatient(Patient patient){
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
            pe.setBasket(false);

        return pe;
    }

    private static List<PatientExtended> mergeList(List<PatientExtended> list1, List<Patient> list2){
        List<PatientExtended> resultList = new ArrayList<>();



        for (Patient patient : list2) {
            PatientExtended p = transferPatient(patient);
            for (PatientExtended patientExtended : list1) {
                if (patientExtended.getId().equals(p.getId())){
                    p.setBasket(patientExtended.isBasket());
                    break;
                }
            }
            resultList.add(p);
        }
        return resultList;
    }

    public static List<PatientExtended>  updatePatientListValue(HttpServletRequest req, PatientListAppService patientService){
        List<PatientExtended> list1 = (List<PatientExtended>)req.getSession().getAttribute(ServletUtils.PATIENT_LIST);
        List<Patient> list2 = patientService.getPatientList();
        List<PatientExtended> list = mergeList(list1, list2);

        return list;

    }

}

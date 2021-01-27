package dao.impl;

import dao.AbstractSqlDao;
import dao.AppPatientDao;
import models.Patient;

import java.util.Date;
import java.util.List;

public class MySQLPatientDao extends AbstractSqlDao implements AppPatientDao {

    private final int MAXIUMUM_NUMBER_OF_PATIENT_PER_DAY = 2;


    @Override
    public void savePatient(Patient patient) {
        hibernateUtil.save(patient);
    }

    @Override
    public void deletePatient(Long idPatient) {
        hibernateUtil.deletePatient(Patient.class, idPatient);

    }

    @Override
    public void updatePatient(Patient oldData, Patient newData) {
        hibernateUtil.updatePatient(Patient.class, oldData, newData);
    }

    @Override
    public List<Patient> getPatientList() {
        List<Patient> patientList = entityManager.createQuery("select p from Patient p where p.active = :active", Patient.class)
                .setParameter("active", true)
                .getResultList();

        return patientList;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = entityManager.createQuery("select p from Patient p where p.id = :id", Patient.class)
                .setParameter("id", id).getSingleResult();

        return patient;
    }

    @Override
    public List<Patient> getSearchingResults(String search) {
        List<Patient> patientList = entityManager.createQuery("select p from Patient p where p.lastName like :search or p.pesel like :search", Patient.class)
                .setParameter("search", "%" + search + "%")
                .getResultList();

        return patientList;
    }

    @Override
    public boolean isHospitalizationDateAvailable(Date date) {
        boolean result = true;
        List<Patient> patientList = entityManager.createQuery("select p from Patient p where p.scheludedRegistration= :scheluded and p.hospitalizationDate = :data", Patient.class)
                .setParameter("scheluded", true)
                .setParameter("data", date)
                .getResultList();
        if (patientList.size() >= MAXIUMUM_NUMBER_OF_PATIENT_PER_DAY) {
            result = false;
        }

        return result;
    }
}

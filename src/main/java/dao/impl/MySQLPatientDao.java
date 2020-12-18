package dao.impl;

import dao.AbstractSqlDao;
import dao.AppPatientDao;
import models.AppUser;
import models.Patient;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MySQLPatientDao extends AbstractSqlDao implements AppPatientDao {

    @Override
    public void savePatient(Patient patient) {
        hibernateUtil.save(patient);
    }

    @Override
    public void deletePatient(Patient patient) {
        hibernateUtil.delete(Patient.class, patient.getId());

    }

    @Override
    public List<Patient> getPatientList() {
        List<Patient> patientList = entityManager.createQuery("select p from Patient p where p.active = :active", Patient.class)
                .setParameter("active", true)
                .getResultList();

        return patientList;
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
        if (patientList.size() >= 2) {
            result = false;
        }

        return result;
    }
}

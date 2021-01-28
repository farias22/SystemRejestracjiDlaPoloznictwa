package dao.impl;

import dao.AbstractSqlDao;
import dao.AppPatientDao;
import models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MySQLPatientDao extends AbstractSqlDao implements AppPatientDao {

    private final Long MAXIUMUM_NUMBER_OF_PATIENT_PER_DAY = 3L;


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
      return entityManager.createQuery("select p from Patient p where p.active = :active", Patient.class)
                .setParameter("active", true)
                .getResultList();


    }

    @Override
    public Patient getPatientById(Long id) {
        return entityManager.createQuery("select p from Patient p where p.id = :id", Patient.class)
                .setParameter("id", id).getSingleResult();


    }

    @Override
    public List<Patient> getSearchingResults(String search) {
        return entityManager.createQuery("select p from Patient p where p.lastName like :search or p.pesel like :search", Patient.class)
                .setParameter("search", "%" + search + "%")
                .getResultList();


    }

    @Override
    public boolean isHospitalizationDateAvailable(Date date, Long idPatient) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String value = sdf.format(date);
        List<Object> unavailableDateList = entityManager.createQuery(
                "select cast(p.hospitalizationDate as date) " +
                        "from Patient p " +
                        "where p.active= :act and p.id != :idP " +
                        "group by cast(p.hospitalizationDate as date) having count(1)>= :counter")
                .setParameter("act", true)
                .setParameter("idP", idPatient)
                .setParameter("counter", MAXIUMUM_NUMBER_OF_PATIENT_PER_DAY)
                .getResultList();

        List<String> unavailableDateListStr = new ArrayList<>();
        for (Object s : unavailableDateList) {
            unavailableDateListStr.add(s.toString());
        }

        return !unavailableDateListStr.contains(value);

    }

    @Override
    public List<String> getAvailableDateList(Long idPatient) {
        List<String> list = new ArrayList<>();

        String pattern = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Date dt = Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        while (list.size()<22) {
            if (isHospitalizationDateAvailable(dt, idPatient)) {
                list.add(sdf.format(dt));
            }
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            dt = c.getTime();
        }
        return list;
    }
}

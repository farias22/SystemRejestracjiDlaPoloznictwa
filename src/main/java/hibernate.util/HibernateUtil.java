package hibernate.util;

import models.AppUser;
import models.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class HibernateUtil {

    private static HibernateUtil instance;
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("myDatabase");
    private final EntityManager manager = factory.createEntityManager();


    private HibernateUtil() {

    }

    public static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }


    public void save(Object object) {
        manager.getTransaction().begin();
        if (!manager.contains(object)) {
            manager.persist(object);
            manager.flush();
        }
        manager.getTransaction().commit();
    }

    public void deletePatient(Class clazz, Long id) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, id);
        if (null != o) {
            Patient updatedPatient = (Patient) o;
            updatedPatient.setActive(false);
            manager.remove(o);
            manager.persist(updatedPatient);
        }
        manager.flush();
        manager.getTransaction().commit();
    }

    public void deleteUser(Class clazz, Long id) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, id);
        if (null != o) {
            manager.remove(o);
        }
        manager.flush();
        manager.getTransaction().commit();
    }


    public void update(Class clazz, Patient a1, Patient a2) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, a1.getId());
        if (null!=o){
            manager.remove(o);
            a2.setRegistrationDate(a1.getRegistrationDate());
            manager.persist(a2);
        }
        manager.flush();
        manager.getTransaction().commit();
    }

    public void resetUserPassword(Class clazz, AppUser user) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, user.getId());
        if (null!=o){
            manager.remove(o);
            user.setPassword(user.getEmail());
            manager.persist(user);
        }
        manager.flush();
        manager.getTransaction().commit();
    }

    public void setAdmin(Class clazz, AppUser user) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, user.getId());
        if (null!=o){
            manager.remove(o);
            user.setAdmin(true);
            manager.persist(user);
        }
        manager.flush();
        manager.getTransaction().commit();
    }

    public void setNoAdmin(Class clazz, AppUser user) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, user.getId());
        if (null!=o){
            manager.remove(o);
            user.setAdmin(false);
            manager.persist(user);
        }
        manager.flush();
        manager.getTransaction().commit();
    }



    public EntityManager getManager() {
        return manager;
    }

}

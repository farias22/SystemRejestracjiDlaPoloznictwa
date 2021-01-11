package dao.impl;

import dao.AbstractSqlDao;
import dao.AppUserDao;
import models.AppUser;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class MySQLUserDao extends AbstractSqlDao implements AppUserDao {


    @Override
    public void saveUser(AppUser user) {
        hibernateUtil.save(user);

    }

    @Override
    public void deleteUser(AppUser user) {
        hibernateUtil.deleteUser(AppUser.class, user.getId());
    }

    @Override
    public void resetUserPassword(AppUser user) {
        hibernateUtil.resetUserPassword(AppUser.class, user);
    }

    @Override
    public void setAdmin(AppUser user) {
        hibernateUtil.setAdmin(AppUser.class, user);
    }

    @Override
    public void setNoAdmin(AppUser user) {
        hibernateUtil.setNoAdmin(AppUser.class, user);
    }

    @Override
    public void updateUser(AppUser appUserOld, AppUser appUserNew) {
        hibernateUtil.updateUser(AppUser.class, appUserOld, appUserNew);
    }

    @Override
    public Optional<AppUser> getAppUserByEmail(String email) {
        TypedQuery<AppUser> query = entityManager.createQuery("select u from AppUser u where u.email =:email", AppUser.class);
        query.setParameter("email", email);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AppUser> getAppUserById(Long id) {
        TypedQuery<AppUser> query = entityManager.createQuery("select u from AppUser u where u.id =:id", AppUser.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<AppUser> getSearchingResults(String search) {

        List<AppUser> usersList = entityManager.createQuery("select u from AppUser u where u.lastName like :search or u.email like :search", AppUser.class)
                .setParameter("search", "%" + search + "%")
                .getResultList();
        return usersList;
    }


}

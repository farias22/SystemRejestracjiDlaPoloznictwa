package services.impl;

import dao.AppUserDao;
import models.AppUser;
import services.UsersAppService;

import java.util.List;
import java.util.Optional;

public class UsersAppServiceImpl implements UsersAppService {


    private AppUserDao appUserDao;

    public UsersAppServiceImpl(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public boolean isLoginAndPasswordValid(String login, String password) {
        Optional<AppUser> userByLogin = appUserDao.getAppUserByLogin(login);
        if (userByLogin.isEmpty()) {
            return false;
        }
        String passFromDB = userByLogin.get().getPassword();
        return passFromDB.equals(password);
    }

    @Override
    public boolean isEmailExsist(String login) {
        Optional<AppUser> userByLogin = appUserDao.getAppUserByLogin(login);
        if (userByLogin.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void addUser(AppUser appUser) {
        appUserDao.saveUser(appUser);
    }

    @Override
    public void editUser(AppUser appUserOld, AppUser appUserNew) {
        appUserDao.updateUser(appUserOld, appUserNew);
    }

    @Override
    public String getUserNameFromEmail(String login) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByLogin(login);
        if (loggedUser.isEmpty()){
            return "";
        }
        return loggedUser.get().getFistName()+" "+loggedUser.get().getLastName();
    }

    @Override
    public boolean isUserIsAdmin(String login) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByLogin(login);
        if (loggedUser.isEmpty()){
            return false;
        }
        return loggedUser.get().isAdmin();
    }

    @Override
    public boolean domainAvailable(String login) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByLogin(login);
        if (loggedUser.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public AppUser getAppUserByLogin(String login) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByLogin(login);
        if (loggedUser.isEmpty()){
            return new AppUser();
        }
        return loggedUser.get();
    }

    @Override
    public AppUser getAppUserById(Long id) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserById(id);
        if (loggedUser.isEmpty()){
            return new AppUser();
        }
        return loggedUser.get();
    }

    @Override
    public List<AppUser> getSearchingResults(String search) {
        return appUserDao.getSearchingResults(search);
    }

    @Override
    public void deleteUser(AppUser user) {
        appUserDao.deleteUser(user);
    }

    @Override
    public void resetUserPassword(AppUser user) {
        appUserDao.resetUserPassword(user);
    }

    @Override
    public void setAdmin(AppUser user) {
        appUserDao.setAdmin(user);
    }

    @Override
    public void setNoAdmin(AppUser user) {
        appUserDao.setNoAdmin(user);
    }
}

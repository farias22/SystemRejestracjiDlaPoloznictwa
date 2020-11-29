package services.impl;

import dao.AppUserDao;
import models.AppUser;
import services.UsersAppService;

import java.util.Optional;

public class UsersAppServiceImpl implements UsersAppService {


    private AppUserDao appUserDao;

    public UsersAppServiceImpl(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public boolean isEmailAndPasswordValid(String email, String password) {
        Optional<AppUser> userByEmail = appUserDao.getAppUserByEmail(email);
        if (userByEmail.isEmpty()) {
            return false;
        }
        String passFromDB = userByEmail.get().getPassword();
        return passFromDB.equals(password);
    }

    @Override
    public boolean isEmailExsist(String email) {
        Optional<AppUser> userByEmail = appUserDao.getAppUserByEmail(email);
        if (userByEmail.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void addUser(AppUser appUser) {
        appUserDao.saveUser(appUser);
    }


    @Override
    public String getUserNameFromEmail(String email) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByEmail(email);
        if (loggedUser.isEmpty()){
            return "";
        }
        return loggedUser.get().getFistName()+" "+loggedUser.get().getLastName();
    }

    @Override
    public boolean isUserIsAdmin(String email) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByEmail(email);
        if (loggedUser.isEmpty()){
            return false;
        }
        return loggedUser.get().isAdmin();
    }

    @Override
    public boolean domainAvailable(String email) {
        Optional<AppUser> loggedUser= appUserDao.getAppUserByEmail(email);
        if (loggedUser.isEmpty()){
            return true;
        }
        return false;
    }
}

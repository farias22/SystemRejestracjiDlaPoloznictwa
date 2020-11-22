package dao;

import models.AppUser;

import java.util.Optional;

public interface AppUserDao {

    void saveUser(AppUser user);

    void deleteUser(AppUser user);

    Optional<AppUser> getAppUserByEmail(String email);

}

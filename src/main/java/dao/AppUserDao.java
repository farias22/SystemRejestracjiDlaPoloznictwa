package dao;

import models.AppUser;
import models.Patient;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    void saveUser(AppUser user);

    void deleteUser(AppUser user);

    Optional<AppUser> getAppUserByEmail(String email);

    Optional<AppUser> getAppUserById(Long id);

    List<AppUser> getSearchingResults(String search);

}

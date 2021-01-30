package dao;

import models.AppUser;
import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    void saveUser(AppUser user);

    void deleteUser(AppUser user);

    Optional<AppUser> getAppUserByLogin(String login);

    Optional<AppUser> getAppUserById(Long id);

    List<AppUser> getSearchingResults(String search);

    void resetUserPassword(AppUser user);

    void setAdmin(AppUser user);

    void setNoAdmin(AppUser user);

    void updateUser(AppUser appUserOld, AppUser appUserNew);

}

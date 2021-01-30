package services;

import models.AppUser;
import models.Patient;

import java.util.List;

public interface UsersAppService {

    boolean isLoginAndPasswordValid(String login, String password);

    boolean isEmailExsist(String login);

    void addUser(AppUser appUser);

    void editUser(AppUser appUserOld, AppUser appUserNew);

    String getUserNameFromEmail(String login);

    boolean isUserIsAdmin(String login);

    boolean domainAvailable(String login);

    AppUser getAppUserByLogin(String login);

    AppUser getAppUserById(Long id);

    List<AppUser> getSearchingResults(String search);

    void deleteUser(AppUser user);

    void resetUserPassword(AppUser user);

    void setAdmin(AppUser user);

    void setNoAdmin(AppUser user);

}

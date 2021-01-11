package services;

import models.AppUser;
import models.Patient;

import java.util.List;

public interface UsersAppService {

    boolean isEmailAndPasswordValid(String email, String password);

    boolean isEmailExsist(String email);

    void addUser(AppUser appUser);

    void editUser(AppUser appUserOld, AppUser appUserNew);

    String getUserNameFromEmail(String email);

    boolean isUserIsAdmin(String email);

    boolean domainAvailable(String email);

    AppUser getAppUserByEmail(String email);

    AppUser getAppUserById(Long id);

    List<AppUser> getSearchingResults(String search);

    void deleteUser(AppUser user);

    void resetUserPassword(AppUser user);

    void setAdmin(AppUser user);

    void setNoAdmin(AppUser user);

}

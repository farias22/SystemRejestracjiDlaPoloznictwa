package services;

import models.AppUser;
import models.Patient;

import java.util.List;

public interface UsersAppService {

    boolean isEmailAndPasswordValid(String email, String password);

    boolean isEmailExsist(String email);

    void addUser(AppUser appUser);

    String getUserNameFromEmail(String email);

    boolean isUserIsAdmin(String email);

    boolean domainAvailable(String email);

    AppUser getAppUserByEmail(String email);

    List<AppUser> getSearchingResults(String search);

}

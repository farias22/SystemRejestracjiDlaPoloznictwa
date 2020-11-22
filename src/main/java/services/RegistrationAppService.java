package services;

import models.AppUser;

public interface RegistrationAppService {

    boolean isEmailAndPasswordValid(String email, String password);

    void addUser(AppUser appUser);

}

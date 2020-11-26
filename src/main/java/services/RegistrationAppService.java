package services;

import models.AppUser;

public interface RegistrationAppService {

    boolean isEmailAndPasswordValid(String email, String password);

    boolean isEmailExsist(String email);

    void addUser(AppUser appUser);

}

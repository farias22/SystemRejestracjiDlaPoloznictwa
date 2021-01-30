package models;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fistName;
    private String lastName;
    private String login;
    private String password;
    private boolean admin;
    private Date registeredSince;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getRegisteredSince() {
        return registeredSince;
    }

    public void setRegisteredSince(Date registeredSince) {
        this.registeredSince = registeredSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class UserBuilder {

        private String fistName;
        private String lastName;
        private String login;
        private String password;
        private boolean admin;

        public static UserBuilder getBuilder() {
            return new UserBuilder();
        }

        public UserBuilder fistName(String fistName) {
            this.fistName = fistName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder admin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public AppUser build() {
            AppUser user = new AppUser();
            user.setRegisteredSince(new Date(System.currentTimeMillis()));
            user.setFistName(this.fistName);
            user.setLastName(this.lastName);
            user.setLogin(this.login);
            user.setPassword(this.password);
            user.setAdmin(this.admin);
            return user;
        }


    }

}

package ru.wv.beans;

import org.mindrot.jbcrypt.BCrypt;
import ru.wv.persistence.entities.Role;
import ru.wv.persistence.entities.User;
import ru.wv.persistence.services.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@RequestScoped
public class UserEdit implements Serializable {

    @EJB
    private UserService userService;

    private long id;
    private String name;
    private Role role;
    private String email;
    private String password;

    public void loadUser(long id) {
        userService.get(id).ifPresent(user -> {
            this.id = user.getId();
            name = user.getName();
            role = user.getRole();
            email = user.getEmail();
        });
    }

    public void saveUser() {
        if (id > 0) {
            if (password.trim().length() == 0)
                password = null;

            userService.update(id,
                    name.trim(),
                    Optional.ofNullable(password),
                    email.trim(),
                    role);
        } else {
            User newUser = new User();
            newUser.setName(name.trim());
            newUser.setPwdHash(BCrypt.hashpw(password.trim(), BCrypt.gensalt()));
            newUser.setEmail(email.trim());
            newUser.setRole(role);
            userService.persist(newUser);
        }
    }

    public void newUser() {
        id = 0;
        email = "";
        password = "";
        role = Role.USER;
        name = "";
    }

    public void deleteUser() {
        userService.delete(id);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

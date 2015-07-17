package ru.wv.beans;

import ru.wv.persistence.entities.User;
import ru.wv.persistence.services.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "userEdit")
@RequestScoped
public class UserEdit {
    private User user;

    @EJB
    private UserService userService;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void loadUser(long id) {
        user = userService.get(id).orElse(null);
    }
}

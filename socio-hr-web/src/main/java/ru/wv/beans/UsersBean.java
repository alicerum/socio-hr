package ru.wv.beans;

import ru.wv.persistence.entities.User;
import ru.wv.persistence.services.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsersBean {

    private List<User> users;

    @EJB
    private UserService userService;

//    public List<User> getUsers() {
//        return userService.get();
//    }
}

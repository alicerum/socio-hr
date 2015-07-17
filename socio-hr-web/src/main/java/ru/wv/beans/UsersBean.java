package ru.wv.beans;

import ru.wv.persistence.entities.User;
import ru.wv.persistence.services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsersBean implements Serializable {

    private List<User> users;

    @EJB
    private UserService userService;
    private HtmlDataTable dataTable;

    @PostConstruct
    public void init() {
        users = userService.get();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }
}

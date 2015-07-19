package ru.wv.beans;

import ru.wv.persistence.entities.Role;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class RolesBean {

    public Role[] getRoles() {
        return Role.values();
    }
}

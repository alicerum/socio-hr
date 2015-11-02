package ru.wv.beans.users;

import org.mindrot.jbcrypt.BCrypt;
import ru.wv.persistence.entities.User;
import ru.wv.persistence.services.UserService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean(name = "loginSession")
@SessionScoped
public class LoginSession implements Serializable {

    @EJB
    private UserService userService;

    private String login;
    private String password;
    private String role;
    private boolean loggedIn;

    public String doLogin() {

        Optional<User> maybeUser = userService.get(login.trim());

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            if (BCrypt.checkpw(password, user.getPwdHash())) {
                role = user.getRole().toString();
                loggedIn = true;
                return "/admin/welcome.xhtml?faces-redirect=true&amp;includeViewParams=true";
            }
        }

        // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // To to login page
        return "/admin/index.xhtml?faces-redirect=true";
    }

    public String doLogout() {
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/admin/index.xhtml?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}

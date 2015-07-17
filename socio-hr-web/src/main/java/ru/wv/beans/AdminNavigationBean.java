package ru.wv.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "adminNavigationBean")
@RequestScoped
public class AdminNavigationBean {

    private String page = "pages/articles.xhtml"; // default page

    public void openPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}

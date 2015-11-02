package ru.wv.beans.categories;

import ru.wv.persistence.entities.Category;
import ru.wv.persistence.services.CategoriesService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "categoriesBean")
@ViewScoped
public class CategoriesBean implements Serializable {
    private List<Category> categories;
    private HtmlDataTable dataTable;

    @EJB
    private CategoriesService categoriesService;

    @PostConstruct
    public void init() {
        categories = categoriesService.get();
    }

    public List<Category> getCategories() {
        return categories;
    }


    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }
}

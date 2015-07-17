package ru.wv.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CATEGORIES")
@SequenceGenerator(name = "categoriesIdGenerator", sequenceName = "sequence_id_generator")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriesIdGenerator")
    private long id;

    @NotNull
    private String name;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Category parent;

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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}

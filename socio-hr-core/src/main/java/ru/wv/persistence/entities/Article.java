package ru.wv.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "ARTICLES")
@SequenceGenerator(name = "articleIdGenerator", sequenceName = "article_id_seq")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articleIdGenerator")
    private long id;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private User author;
    @NotNull @Size(max = 255)
    private String title;
    @NotNull @Lob
    private String text;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Category category;
    @NotNull
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

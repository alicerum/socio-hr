package ru.wv.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ARTICLES",
        uniqueConstraints = {
                @UniqueConstraint(name = "article_evernote_guid_unique",
                        columnNames = {"evernoteGuid"}
                )
        }
)
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
    private String evernoteGuid;
    private String hash;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Category category;

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

    public String getEvernoteGuid() {
        return evernoteGuid;
    }

    public void setEvernoteGuid(String evernoteGuid) {
        this.evernoteGuid = evernoteGuid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

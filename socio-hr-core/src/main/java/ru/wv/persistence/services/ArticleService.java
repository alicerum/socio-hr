package ru.wv.persistence.services;

import ru.wv.persistence.entities.Article;
import ru.wv.persistence.entities.Article_;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ArticleService {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Article> get(long id) {
        return Optional.ofNullable(entityManager.find(Article.class, id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Article persist(Article article) {
        entityManager.persist(article);
        return article;
    }
}

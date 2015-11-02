package ru.wv.persistence.services;

import ru.wv.persistence.entities.Category;
import ru.wv.persistence.entities.Category_;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CategoriesService {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Category persist(Category category) {
        entityManager.persist(category);
        return category;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Category get(long id) {
        return entityManager.find(Category.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Category> get() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> categories = cq.from(Category.class);
        cq.select(categories);

        cq.orderBy(cb.asc(categories.get(Category_.position)));

        return entityManager.createQuery(cq)
                .getResultList();
    }
}

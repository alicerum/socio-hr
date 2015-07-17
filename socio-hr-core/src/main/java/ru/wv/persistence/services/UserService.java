package ru.wv.persistence.services;

import ru.wv.persistence.entities.User;
import ru.wv.persistence.entities.User_;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> get(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public User persist(User user) {
        entityManager.persist(user);
        return user;
    }

    public Optional<User> get(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> users = cq.from(User.class);
        cq.select(users);
        cq.where(cb.equal(users.get(User_.name), name));

        try {
            return Optional.of(entityManager.createQuery(cq).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<User> get() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> users = cq.from(User.class);
        cq.select(users);

        return entityManager.createQuery(cq).getResultList();
    }
}

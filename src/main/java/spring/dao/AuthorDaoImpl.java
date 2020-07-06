package spring.dao;

import org.springframework.stereotype.Repository;
import spring.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> findByName(String lastname) {
        TypedQuery<Author> query = em.createQuery("select s " +
                                                       "from Author s " +
                                                       "where s.lastname = :lastname",
                Author.class);
        query.setParameter("lastname", lastname);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public void delete(Author object) {
        em.remove(object);
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select s from Author s", Author.class);
        return query.getResultList();
    }

    @Override
    public Author save(Author object) {
        if (object.getId() <= 0) {
            em.persist(object);
            return object;
        } else {
            return em.merge(object);
        }
    }
}


package spring.dao;

import org.springframework.stereotype.Repository;
import spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> findByGenre(String genre) {
        TypedQuery<Genre> query = em.createQuery("select s " +
                                                  "from Genre s " +
                                                  "where s.genre = :genre",
                Genre.class);
        query.setParameter("genre", genre);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public void delete(Genre object) {
        Query query = em.createQuery("delete " +
                                     "from Genre s " +
                                     "where s.id = :id");
        query.setParameter("id", object.getId());
        query.executeUpdate();
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select s from Genre s", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre save(Genre object) {
        if (object.getId() <= 0) {
            em.persist(object);
            return object;
        } else {
            return em.merge(object);
        }
    }
}

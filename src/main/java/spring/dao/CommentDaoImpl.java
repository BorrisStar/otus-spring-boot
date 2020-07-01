package spring.dao;

import org.springframework.stereotype.Repository;
import spring.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager em;
    

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void delete(Comment object) {
        Query query = em.createQuery("delete " +
                                     "from Comment s " +
                                     "where s.id = :id");
        query.setParameter("id", object.getId());
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select s from Comment s", Comment.class);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAllByTitle(String title) {

        TypedQuery<Comment> query = em.createQuery("select s from Comment s where s.book.title = :title", Comment.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public Comment save(Comment object) {
        if (object.getId() <= 0) {
            em.persist(object);
            return object;
        } else {
            return em.merge(object);
        }
    }
}

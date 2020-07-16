package spring.dao;


import org.springframework.stereotype.Repository;
import spring.model.Author;
import spring.model.Book;
import spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoImpl(AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select s from Book s where s.title = :title", Book.class);
        query.setParameter("title", title);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> findByAuthor(String lastname) {
        Optional<Author> author = authorDao.findByName(lastname);
        if(author.isPresent()){
            TypedQuery<Book> query = em.createQuery("select s from Book s where s.author = :author", Book.class);
            query.setParameter("author", author.get().getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findByGenre(String name) {
        Optional<Genre> genre = genreDao.findByGenre(name);
        if(genre.isPresent()){
            TypedQuery<Book> query = em.createQuery("select s from Book s where s.genre = :genre", Book.class);
            query.setParameter("genre", genre.get().getId());
            return query.getResultList();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public void delete(Book object) {
        em.remove(object);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select s from Book s", Book.class);
        return query.getResultList();
    }

    @Override
    public Book save(Book object) {
        if (object.getId() <= 0) {
            em.persist(object);
            return object;
        } else {
            return em.merge(object);
        }
    }
}

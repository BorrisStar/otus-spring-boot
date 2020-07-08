package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.CommentDao;
import spring.model.Book;
import spring.model.Comment;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDaoService {

    private final CommentDao commentDao;

    public CommentDaoService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Optional<Comment> findById(long id) {
        return commentDao.findById(id);
    }

    @Transactional()
    public void delete(Comment object) {
        commentDao.delete(object);
    }

    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    public List<Comment> findAllForBook(Book book) {
        return book.getCommentList();
    }

    @Transactional()
    public Comment save(Comment object) {
        return commentDao.save(object);
    }
}

package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.CommentDao;
import spring.model.Comment;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDaoService {

    private final CommentDao commentDao;

    public CommentDaoService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return commentDao.findById(id);
    }

    @Transactional()
    public void delete(Comment object) {
        commentDao.delete(object);
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllForBook(String title) {
        return commentDao.findAllByTitle(title);
    }

    @Transactional()
    public Comment save(Comment object) {
        return commentDao.save(object);
    }
}

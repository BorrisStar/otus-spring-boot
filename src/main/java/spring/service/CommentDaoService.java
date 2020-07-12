package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.CommentDao;
import spring.model.Book;
import spring.model.Comment;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentDaoService {
    private final BookDaoService bookDaoService;

    private final CommentDao commentDao;

    public CommentDaoService(CommentDao commentDao,  BookDaoService bookDaoService) {
        this.commentDao = commentDao;
        this.bookDaoService = bookDaoService;
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

    public List<Comment> findAllForBook(Long bookId) {
        Optional <Book> book = bookDaoService.findById(bookId);
        if( book.isPresent()){
            return book.get().getCommentList();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional()
    public Comment save(Comment object) {
        return commentDao.save(object);
    }
}

package spring.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    public List<Comment> findAll() {
        return (List<Comment>) commentDao.findAll();
    }
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public List<Comment> findAllForBook(Long bookId) {
        Optional <Book> book = bookDaoService.findById(bookId);
        if( book.isPresent()){
            Book bookProxy = book.get();
            Hibernate.initialize(bookProxy.getCommentList());
            return bookProxy.getCommentList();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public Comment save(Comment object) {
        return commentDao.save(object);
    }
}

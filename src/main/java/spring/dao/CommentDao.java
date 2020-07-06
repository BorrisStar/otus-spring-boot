package spring.dao;

import spring.model.Book;
import spring.model.Comment;

import java.util.List;

public interface CommentDao extends StoreRepository<Comment>{
    List<Comment> findAllByBook(Book book);
}

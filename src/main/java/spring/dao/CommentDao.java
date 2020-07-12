package spring.dao;

import org.springframework.data.repository.CrudRepository;
import spring.model.Comment;

public interface CommentDao extends CrudRepository<Comment, Long> {

}

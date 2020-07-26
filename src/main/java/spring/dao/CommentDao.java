package spring.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.model.Comment;

public interface CommentDao extends MongoRepository<Comment, Long> {

}

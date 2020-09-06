package spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.model.Book;

public interface BookDao extends ReactiveMongoRepository<Book, Long> {

    Flux<Book> findBooksByAuthor(long author);

    Mono<Void> deleteById(Long id);
}

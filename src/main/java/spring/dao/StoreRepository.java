package spring.dao;

import java.util.List;
import java.util.Optional;

public interface StoreRepository<T> {

    Optional<T> findById(long id);

    void delete(T object);

    List<T> findAll();

    T save(T object);

}

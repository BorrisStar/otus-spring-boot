package spring.dao;

import java.util.Collection;

public interface StoreRepository<T> {

    T findById(long id);

    void delete(T object);

    Iterable<T> findAll();

    T save(T object);

    Iterable<T> save(Collection<T> objects);
}

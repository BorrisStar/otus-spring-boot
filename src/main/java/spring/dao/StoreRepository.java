package spring.dao;

import java.util.Collection;

public interface StoreRepository<T> {

    T findById(long id);

    void delete(T domain);

    Iterable<T> findAll();

    T save(T domain);

    Iterable<T> save(Collection<T> domains);
}

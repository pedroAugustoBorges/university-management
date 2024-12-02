package org.pedro.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T, ID>{

    void save(T entity);

    void update (T entity);

    boolean removeById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();
}

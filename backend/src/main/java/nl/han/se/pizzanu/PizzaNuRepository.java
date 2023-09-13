package nl.han.se.pizzanu;

import java.util.List;

public interface PizzaNuRepository<T,ID> {
    int save(T object);

    int update(T object);

    T findById(ID id);

    int deleteById(ID id);

    List<T> findAll();

    List<T> findByNameContaining(String name);

    int deleteAll();
}

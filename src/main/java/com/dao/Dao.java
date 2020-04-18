package com.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Repository
public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
package com.dao;

import com.entities.Answer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnswerDao implements Dao<Answer> {
    private SessionFactory sessionFactory;

    @Override
    public Optional<Answer> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Answer> getAll() {
        return null;
    }

    @Override
    public void save(Answer answer) {

    }

    @Override
    public void update(Answer answer, String[] params) {

    }

    @Override
    public void delete(Answer answer) {

    }
}

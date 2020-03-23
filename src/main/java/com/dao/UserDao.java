package com.dao;
import com.entities.Questionnaire;
import com.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao implements Dao<User>{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }


    public int add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        return 1;
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user, String[] params) {

    }

    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public int findUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User u where u.login = :login and u.password =:password");
        List result = query
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .getResultList();
        if (result.size() == 1) {
            return ((User)result.get(0)).getId();
        }else {
            return -1;
        }
    }

    public List<Questionnaire> getQuestionnaireList(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Questionnaire q where q.user_id= :user_id");
        query.setParameter("user_id", id);
        return query.getResultList();
    }
}
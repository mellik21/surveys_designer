package com.dao;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public int add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        return 1;
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
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
}
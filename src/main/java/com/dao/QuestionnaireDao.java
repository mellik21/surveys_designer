package com.dao;

import com.entities.Question;
import com.entities.Questionnaire;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class QuestionnaireDao implements Dao<Questionnaire> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Questionnaire> getAll() {
        return (List<Questionnaire>) sessionFactory.openSession().createQuery("From Questionnaire").list();

    }


    @Override
    public void delete(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(questionnaire);
    }

    public int save(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        return (int) session.save(questionnaire);
    }

    public void merge(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(questionnaire);
    }

    public Questionnaire get(int id) {
        return sessionFactory.getCurrentSession().get(Questionnaire.class, id);
    }


    public List<Question> getQuestions(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Question where questionnaireId = : id");
        query.setParameter("id", id);
        return (List<Question>) query.list();
    }

    public void update(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.update(questionnaire);

    }

    @Override
    public void persist(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(questionnaire);

    }

    public void replicate(Questionnaire questionnaire, ReplicationMode replicationMode) {
        Session session = sessionFactory.getCurrentSession();
        session.replicate(questionnaire, replicationMode);
    }

    /*
        public List search(String searchQuesry) {
            Session session = sessionFactory.getCurrentSession();

            SQLQuery query = session.createSQLQuery("FROM SURVEYS.QUESTIONNAIRE where SELECT POSITION ('" + searchQuesry + "' in TITLE ) >0 ");
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                Questionnaire q = new Questionnaire();
                q.setId(Integer.parseInt(row[0].toString()));
                q.setUserId(Integer.parseInt(row[1].toString()));
                q.setSize(Integer.parseInt(row[2].toString()));
                q.setDescription(row[3].toString());
                q.setNumberOfAnswers(Integer.parseInt(row[4].toString()));
                q.setQuestions((List<Question>) row[5]);
            }

            System.out.println("НАШЛОСЬ :" + query.list().size());
            return query.list();
        }*/

    public List<Questionnaire> search(String searchQuesry) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Questionnaire where title like :title");
        query.setParameter("title", "%" + searchQuesry + "%");
       return query.list();
    }

}

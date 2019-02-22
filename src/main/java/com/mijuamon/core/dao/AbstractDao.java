package com.mijuamon.core.dao;

import com.mijuamon.core.model.Identificable;
import com.mijuamon.core.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Identificable> {
    protected Session session = null;
    protected Transaction tx = null;


    protected static String MODEL_NAME = "<modelName>";
    protected static String LOCAL = "<local>";
    protected static String VISITOR = "<visitor>";
    protected static String YEAR = "<year>";
    protected static String WEEK = "<week>";

    protected void handleException(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    protected void startOperation() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    public Query executeQuery(final String sql) {
        Query query = null;

        startOperation();
        query = session.createQuery(sql);

        return query;
    }

    public List<T> getAll(String modelName) {
        Query query = executeQuery("from " + modelName);
        List<T> objects = query == null ? new ArrayList<T>() : query.list();
        return objects;
    }

    public T get(final Class clase, final int id) {
        T result = null;
        try {
            startOperation();

            result = (T) session.load(clase, id);

        } catch (
                HibernateException he) {
            handleException(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
        return result;
    }

    public T get(String modelName, int id) {
        Query query = executeQuery("from " + modelName + " where 'id'=" + id);
        ;
        return query == null ? null : (T) query;
    }

    public List<T> search(String sql) {
        Query query = executeQuery(sql);
        List<T> objects = query == null ? new ArrayList<T>() : query.list();
        return objects;
    }

    public boolean exist(String sql) {
        return (executeQuery(sql).uniqueResult() != null);

    }

    public Object get(T obj) {
        Object objAux;

        startOperation();
        objAux = session.get(obj.getClass(), obj.getID());

        return objAux;
    }


    public void add(T obj) {
        try {
            startOperation();

            session.save(obj);
            tx.commit();

        } catch (
                HibernateException he) {
            handleException(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    public void update(T obj) {
        try {
            startOperation();
            session.update(obj);
            tx.commit();

        } catch (
                HibernateException he) {
            handleException(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    public void delete(T obj) {
        try {
            startOperation();

            session.remove(obj);
            tx.commit();

        } catch (
                HibernateException he) {
            handleException(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

}

package com.mijuamon.core.dao;

import com.mijuamon.core.model.AbstractModel;
import com.mijuamon.core.model.team.TeamModel;
import com.mijuamon.core.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateDao {

    private static HibernateDao instance;


    private Session session;
    private Transaction tx;

    private HibernateDao() {
    }

    public static synchronized HibernateDao getInstance() {
        if (instance == null) {
            instance = new HibernateDao();
        }
        return instance;
    }

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public List<Object> getAll(String modelName) {
        List<Object> objects;
        try {
            iniciaOperacion();
            objects = session.createQuery("from " + modelName).list();
        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return objects;
    }

    public Object get(String modelName, int id)
    {
        Object obj;
        try {
            iniciaOperacion();
            obj = session.createQuery("from " + modelName + " where 'id'="+id);
        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return obj;
    }



    public void add(AbstractModel obj) {
        try {
            iniciaOperacion();

            //session.save(obj.get);
            tx.commit();

        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    public void update(AbstractModel obj) {
        try {
            iniciaOperacion();

            session.lock(session.get(obj.get), LockMode.PESSIMISTIC_WRITE);
            session.update(obj);
            tx.commit();

        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    public void delete(AbstractModel obj) {
        try {
            iniciaOperacion();

            session.remove(obj);
            tx.commit();

        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }
}

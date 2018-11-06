package com.mijuamon.core.dao;

import com.mijuamon.core.model.team.TeamModel;
import com.mijuamon.core.util.HibernateUtil;
import org.hibernate.HibernateException;
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

    public List<TeamModel> getAll(String modelName) {
        List<TeamModel> teams;
        try {
            iniciaOperacion();
            teams = session.createQuery("from " + modelName).list();
        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return teams;
    }

    public void add(Object obj) {
        try {
            iniciaOperacion();
            session.save(obj);

        } catch (
                HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
}

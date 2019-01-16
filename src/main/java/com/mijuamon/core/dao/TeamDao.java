package com.mijuamon.core.dao;

import com.mijuamon.core.model.TeamModel;
import org.hibernate.HibernateException;

import java.util.List;

public class TeamDao extends AbstractDao<TeamModel> {
    private static TeamDao instance = null;


    public static synchronized TeamDao getInstance() {
        if (instance == null) {
            instance = new TeamDao();
        }
        return instance;
    }

    @Override
    public List<TeamModel> getAll(String modelName) {
        try {
            return (List<TeamModel>) (List<?>) super.getAll(modelName);
        } catch (
                HibernateException he) {
            handleException(he);
            throw he;
        } finally {
            session.close();
        }

    }
}

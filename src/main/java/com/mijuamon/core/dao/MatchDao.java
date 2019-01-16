package com.mijuamon.core.dao;

import com.mijuamon.core.model.MatchModel;
import org.hibernate.HibernateException;

import java.util.List;

public class MatchDao extends AbstractDao {

    private static MatchDao instance;

    public MatchDao() {
        super();
    }

    public static synchronized MatchDao getInstance() {
        if (instance == null) {
            instance = new MatchDao();
        }
        return instance;
    }

    public boolean exist(final MatchModel model) {
        for(MatchModel match:getAll(model.getModelName()))
        {
            if(match.equals(model))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MatchModel> getAll(String modelName) {
        try {
            return (List<MatchModel>) (List<?>) super.getAll(modelName);

        } catch (
                HibernateException he) {
            handleException(he);
            throw he;
        } finally {
            session.close();
        }

    }

}

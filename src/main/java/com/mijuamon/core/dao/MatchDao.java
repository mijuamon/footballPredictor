package com.mijuamon.core.dao;

import com.mijuamon.core.model.match.MatchModel;
import com.mijuamon.core.model.team.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class MatchDao extends HibernateDao{

    private String searchQuery = "from <modelName> where 'local'=<local> " +
            "AND 'visitor'=<visitor>" +
            "AND 'year'=<year>" +
            "AND 'week'=<week>";

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

    public boolean exist(final MatchModel model)
    {
        String query= searchQuery.replace(MODEL_NAME,model.getModelName())
                .replace(LOCAL,model.getLocal().getID()+"")
                .replace(VISITOR, model.getVisitor().getID()+"")
                .replace(YEAR,model.getYear())
                .replace(WEEK,model.getWeek());
        List<MatchModel> list = (List<MatchModel>)(List<?>)search(model.getModelName(),query);
        return list.size()!=0;
    }
}

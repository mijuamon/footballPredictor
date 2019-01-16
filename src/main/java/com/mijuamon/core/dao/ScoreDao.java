package com.mijuamon.core.dao;

import com.mijuamon.core.model.ScoreModel;

public class ScoreDao extends AbstractDao<ScoreModel> {

    private String searchQuery = "from <modelName> where 'local'=<local> " +
            "AND 'visitor'=<visitor>" +
            "AND 'year'=<year>" +
            "AND 'week'=<week>";

    private static ScoreDao instance;

    public ScoreDao() {
        super();
    }

    public static synchronized ScoreDao getInstance() {
        if (instance == null) {
            instance = new ScoreDao();
        }
        return instance;
    }
}

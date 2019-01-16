package com.mijuamon.core.dao;

import com.mijuamon.core.model.PlayerModel;

public class PlayerDao extends AbstractDao<PlayerModel> {

    private String searchQuery = "from <modelName> where 'local'=<local> " +
            "AND 'visitor'=<visitor>" +
            "AND 'year'=<year>" +
            "AND 'week'=<week>";

    private static PlayerDao instance;

    public PlayerDao() {
        super();
    }

    public static synchronized PlayerDao getInstance() {
        if (instance == null) {
            instance = new PlayerDao();
        }
        return instance;
    }
}

package com.mijuamon.core;

import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import java.util.List;
import java.util.logging.Logger;

import static com.mijuamon.core.constants.Constants.getPropertie;

public class Loader {
    private static final Logger LOG = Logger.getLogger(Loader.class.toString());

    protected List<TeamModel> teams;
    protected List<PlayerModel> players;
    protected List<ScoreModel> scores;
    protected List<MatchModel> matches;

    public static List loadInitialData() {

        if (getPropertie("load.files") != null && Boolean.parseBoolean(getPropertie("load.fileMode"))) {
            LOG.warning("Modo Fichero");

            return FileLoader.loadInitialData();


        } else {
            LOG.warning("Modo Base de datos");
            return null;
        }
    }
}

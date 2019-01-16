package com.mijuamon.core.loaders;

import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.FileLoaderUtil;

import java.util.List;
import java.util.logging.Logger;

import static com.mijuamon.core.constants.Constants.getPropertie;

public class Loader {
    private static final Logger LOG = Logger.getLogger(Loader.class.toString());

    protected List<TeamModel> teams;
    protected List<PlayerModel> players;
    protected List<ScoreModel> scores;
    protected List<MatchModel> matches;

    public static List<TeamModel> loadInitialData() {

        if (getPropertie("load.fileMode") != null && Boolean.parseBoolean(getPropertie("load.fileMode"))) {
            LOG.warning("Modo Fichero");

            return FileLoaderUtil.loadInitialData();


        } else {
            LOG.warning("Modo Base de datos");
            return null;
        }
    }
}

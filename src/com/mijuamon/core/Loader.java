package com.mijuamon.core;

import static com.mijuamon.core.constants.Constants.*;

import com.mijuamon.core.constants.Constants;
import com.mijuamon.core.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Loader {
    private static final Logger LOG = Logger.getLogger(Loader.class.toString());

    protected List<TeamModel> teams;
    protected List<PlayerModel> players;
    protected List<ScoreModel> scores;
    protected List<MatchModel> matches;

    public static void loadInitialData (List teams){
        teams=new ArrayList();

        if(getPropertie("load.files")!=null && Boolean.parseBoolean(getPropertie("load.fileMode")))
        {
            LOG.warning("Modo Fichero");
            teams = Arrays.asList(loadFile(TEAMS_DATA, new TeamModel()).split(";"));

        }
        else
        {
            LOG.warning("Modo Base de datos");
        }


    }

    private static String loadFile(String url, AbstractItemModel model) {

        ArrayList output= new ArrayList<>();
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        File file = new File(url);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                AbstractItemModel.convert(scanner.nextLine(), model);
                output.add(model);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}

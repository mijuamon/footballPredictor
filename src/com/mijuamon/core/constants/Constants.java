package com.mijuamon.core.constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

    public static final String UPDATE_LABEL ="Actualizar";
    public static final String SAVE_LABEL   =" Guardar ";


    public static final String CONFIG_FILE = "resources/configuration.properties";

    public static final String TEAMS_DATA = "resources/data/teams.dat";
    public static final String PLAYERS_DATA = "resources/data/players.dat";
    public static final String SCORE_DATA = "resources/data/scores.dat";
    public static final String MATCH_DATA = "resources/data/matches.dat";

    public static int NUM_TEAMS=0;
    public static int NUM_SCORES=0;
    public static int NUM_MATCHES=0;
    public static int NUM_PLAYERS=0;

    //Conditionals

    public static final int DERBI=1;
    public static final int LOCAL_2_COMPETITIONS=2;
    public static final int LOCAL_3_COMPETITIONS=3;
    public static final int VISITOR_2_COMPETITIONS=4;
    public static final int VISITOR_3_COMPETITIONS=5;
    public static final int HISTORIC_RIVALITY=6;
    public static final int MATCHS_OVERLOAD=7;
    public static final int COMPETITION_ELIMINATION=8;
    public static final int DIRECT_COMPETITION=9;
    public static final int INDIRECT_COMPETITION=10;
    public static final int COACH_CHANGE=11;
    public static final int ROTATIONS=12;


    //Functionality conditionals

    //Use players median for players score
    //  true -> use Median
    //  false -> use sum of all players score
    public static boolean PLAYERS_MEDIAN=true;


    public static Properties prop = new Properties();
    private static InputStream input = null;

    public static String getPropertie(String propertie) {
        String output=null;
        try {
            if (input == null) {

                input = new FileInputStream(CONFIG_FILE);
                prop.load(input);
            }

            output = prop.getProperty(propertie);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }

    public static String nextPlayerID()
    {
        NUM_PLAYERS++;
        return NUM_PLAYERS+"";
    }

    public static void deletePlayerID()
    {
        NUM_PLAYERS--;
    }

    public static String nextScoreID()
    {
        NUM_SCORES++;
        return NUM_SCORES+"";
    }
    public static void deleteScoreIO()
    {
        NUM_SCORES--;
    }

    public static String nextTeamID()
    {
        NUM_TEAMS++;
        return NUM_TEAMS+"";
    }
    public static void deleteTeamIO()
    {
        NUM_TEAMS--;
    }
}

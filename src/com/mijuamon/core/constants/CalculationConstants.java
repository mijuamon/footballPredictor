package com.mijuamon.core.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculationConstants {


    //Conditionals

    public static final int DERBI=1;
    public static final int COMPETITIONS_2=2;
    public static final int COMPETITIONS_3=3;
    public static final int HISTORIC_RIVALITY=6;
    public static final int MATCHS_OVERLOAD=7;
    public static final int COMPETITION_ELIMINATION=8;
    public static final int DIRECT_COMPETITION=9;
    public static final int INDIRECT_COMPETITION=10;
    public static final int COACH_CHANGE=11;
    public static final int ROTATIONS=12;
    public static final int LAST_MATCHS=13;

    //Values

    public static final double DERBI_LOCAL_VALUE=1d;
    public static final double DERBI_VISITOR_VALUE=0d;

    public static final double COMPETITIONS_2_LOCAL_VALUE=-1d;
    public static final double COMPETITIONS_3_LOCAL_VALUE=-2d;

    public static final double COMPETITIONS_2_VISITOR_VALUE=-1.5d;
    public static final double COMPETITIONS_3_VISITOR_VALUE=-2.5d;

    public static final double HISTORIC_RIVALITY_LOCAL_VALUE=0d;
    public static final double HISTORIC_RIVALITY_VISITOR_VALUE=1d;

    public static final double MATCHS_OVERLOAD_LOCAL_VALUE=-1d;
    public static final double MATCHS_OVERLOAD_VISITOR_VALUE=-2d;

    public static final double COMPETITION_ELIMINATION_LOCAL_VALUE=-1d;
    public static final double COMPETITION_ELIMINATION_VISITOR_VALUE=-2d;

    public static final double COACH_CHANGE_LOCAL_VALUE=-1d;
    public static final double COACH_CHANGE_VISITOR_VALUE=-2d;

    public static final double ROTATIONS_LOCAL_VALUE=-1d;
    public static final double ROTATIONS_VISITOR_VALUE=-1d;

    public static final double DIRECT_COMPETITION_LOCAL=1d;//TODO: Implementar
    public static final double DIRECT_COMPETITION_VISITOR=0d;//TODO: Implementar

    public static final double INDIRECT_COMPETITION_LEAGUE_CHAMPIONS=1d;
    public static final double INDIRECT_COMPETITION_LEAGUE_UEFA=2d;
    public static final double INDIRECT_COMPETITION_LEAGUE_DECENT=1d;
    public static final double INDIRECT_COMPETITION_LEAGUE_NOTHING=5d;
    public static final double INDIRECT_COMPETITION_CHAMPIONS_LEAGUE=-1d;
    public static final double INDIRECT_COMPETITION_CHAMPIONS_UEFA=1d;
    public static final double INDIRECT_COMPETITION_CHAMPIONS_DESCENT=3d;
    public static final double INDIRECT_COMPETITION_CHAMPIONS_NOTHING=4d;
    public static final double INDIRECT_COMPETITION_UEFA_LEAGUE=-2d;
    public static final double INDIRECT_COMPETITION_UEFA_CHAMPIONS=-1d;
    public static final double INDIRECT_COMPETITION_UEFA_DESCENT=1d;
    public static final double INDIRECT_COMPETITION_UEFA_NOTHING=2d;
    public static final double INDIRECT_COMPETITION_DESCENT_LEAGUE=-1d;
    public static final double INDIRECT_COMPETITION_DESCENT_CHAMPIONS=-3d;
    public static final double INDIRECT_COMPETITION_DESCENT_UEFA=-1d;
    public static final double INDIRECT_COMPETITION_DESCENT_NOTHING=5d;
    public static final double INDIRECT_COMPETITION_NOTHING_LEAGUE=-5d;
    public static final double INDIRECT_COMPETITION_NOTHING_CHAMPIONS=-4d;
    public static final double INDIRECT_COMPETITION_NOTHING_UEFA=-2d;
    public static final double INDIRECT_COMPETITION_NOTHING_DESCENT=-5d;
    public static final double INDIRECT_COMPETITION_EQUALS=0d;

    public static final double INDIRECT_COMPETITION_LAST_MATCHS=2d;

    public static Map<String,Double> INDIRECT_MATRIX;
    static{
        INDIRECT_MATRIX= new HashMap<>();
        INDIRECT_MATRIX.put("1/1",INDIRECT_COMPETITION_EQUALS);
        INDIRECT_MATRIX.put("2/2",INDIRECT_COMPETITION_EQUALS);
        INDIRECT_MATRIX.put("3/3",INDIRECT_COMPETITION_EQUALS);
        INDIRECT_MATRIX.put("4/4",INDIRECT_COMPETITION_EQUALS);
        INDIRECT_MATRIX.put("5/5",INDIRECT_COMPETITION_EQUALS);

        INDIRECT_MATRIX.put("1/2",INDIRECT_COMPETITION_LEAGUE_CHAMPIONS);
        INDIRECT_MATRIX.put("1/3",INDIRECT_COMPETITION_LEAGUE_UEFA);
        INDIRECT_MATRIX.put("1/4",INDIRECT_COMPETITION_LEAGUE_DECENT);
        INDIRECT_MATRIX.put("1/5",INDIRECT_COMPETITION_LEAGUE_NOTHING);

        INDIRECT_MATRIX.put("2/1",INDIRECT_COMPETITION_CHAMPIONS_LEAGUE);
        INDIRECT_MATRIX.put("2/3",INDIRECT_COMPETITION_CHAMPIONS_UEFA);
        INDIRECT_MATRIX.put("2/4",INDIRECT_COMPETITION_CHAMPIONS_DESCENT);
        INDIRECT_MATRIX.put("2/5",INDIRECT_COMPETITION_CHAMPIONS_NOTHING);

        INDIRECT_MATRIX.put("3/1",INDIRECT_COMPETITION_UEFA_LEAGUE);
        INDIRECT_MATRIX.put("3/2",INDIRECT_COMPETITION_UEFA_CHAMPIONS);
        INDIRECT_MATRIX.put("3/4",INDIRECT_COMPETITION_UEFA_DESCENT);
        INDIRECT_MATRIX.put("3/5",INDIRECT_COMPETITION_UEFA_NOTHING);

        INDIRECT_MATRIX.put("4/1",INDIRECT_COMPETITION_DESCENT_LEAGUE);
        INDIRECT_MATRIX.put("4/2",INDIRECT_COMPETITION_DESCENT_CHAMPIONS);
        INDIRECT_MATRIX.put("4/3",INDIRECT_COMPETITION_DESCENT_UEFA);
        INDIRECT_MATRIX.put("4/5",INDIRECT_COMPETITION_DESCENT_NOTHING);

        INDIRECT_MATRIX.put("5/1",INDIRECT_COMPETITION_NOTHING_LEAGUE);
        INDIRECT_MATRIX.put("5/2",INDIRECT_COMPETITION_NOTHING_CHAMPIONS);
        INDIRECT_MATRIX.put("5/3",INDIRECT_COMPETITION_NOTHING_UEFA);
        INDIRECT_MATRIX.put("5/4",INDIRECT_COMPETITION_NOTHING_DESCENT);
    }

      //Sistema base
    public static final int SYSTEM_BASE=1;

    public static final double SYSTEM_BASE_PLAYERS = 0.35d;
    public static final double SYSTEM_BASE_TEAMS = 0.35d;
    public static final double SYSTEM_BASE_CONDITIONALS = 0.2d;
    public static final double SYSTEM_BASE_UNCERTAINTY = 0.1d;

    //Sistema + jugadores
    public static final int SYSTEM_PLAYERS=2;

    public static final double SYSTEM_PLAYERS_PLAYERS = 0.4d;
    public static final double SYSTEM_PLAYERS_TEAMS = 0.3d;
    public static final double SYSTEM_PLAYERS_CONDITIONALS = 0.2d;
    public static final double SYSTEM_PLAYERS_UNCERTAINTY = 0.1d;

    //Sistema + equipo
    public static final int SYSTEM_TEAMS=3;

    public static final double SYSTEM_TEAMS_PLAYERS = 0.3d;
    public static final double SYSTEM_TEAMS_TEAMS = 0.4d;
    public static final double SYSTEM_TEAMS_CONDITIONALS = 0.2d;
    public static final double SYSTEM_TEAMS_UNCERTAINTY = 0.1d;

    //Sistema + incertidumbre
    public static final int SYSTEM_UNCERTAINTY=4;

    public static final double SYSTEM_UNCERTAINTY_PLAYERS = 0.25d;
    public static final double SYSTEM_UNCERTAINTY_TEAMS = 0.25d;
    public static final double SYSTEM_UNCERTAINTY_CONDITIONALS = 0.3d;
    public static final double SYSTEM_UNCERTAINTY_UNCERTAINTY = 0.2d;


    public static List<Integer> SYSTEM_LIST;
    static {
        SYSTEM_LIST=new ArrayList<>();
        SYSTEM_LIST.add(SYSTEM_BASE);
        SYSTEM_LIST.add(SYSTEM_PLAYERS);
        SYSTEM_LIST.add(SYSTEM_TEAMS);
        SYSTEM_LIST.add(SYSTEM_UNCERTAINTY);
    }

}

package com.mijuamon.core.constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

    public static final String CONFIG_FILE = "../resources/configuration.properties";
    public static final String TEAMS_DATA = "../resources/data/teams.dat";
    public static final String PLAYERS_DATA = "../resources/data/players.dat";

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
}

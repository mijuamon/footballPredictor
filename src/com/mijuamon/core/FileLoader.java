package com.mijuamon.core;

import com.mijuamon.core.model.AbstractItemModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.TeamModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.mijuamon.core.constants.Constants.TEAMS_DATA;

public class FileLoader {

    private static final Logger LOG = Logger.getLogger(FileLoader.class.toString());

    public static List loadInitialData() {

        List teams = Arrays.asList(loadFile(TEAMS_DATA, new TeamModel()).split(";"));//NOSONNAR

        getPlayers(teams);
        return teams;
    }

    private static void getPlayers(List teams) {
        List players = Arrays.asList(loadFile(TEAMS_DATA, new PlayerModel()).split(";"));//NOSONNAR


    }


    private static String loadFile(String url, AbstractItemModel model) {

        ArrayList output = new ArrayList<>();
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        File file = new File(url);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                model.convert(scanner.nextLine());
                output.add(model);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}

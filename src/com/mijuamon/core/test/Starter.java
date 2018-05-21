package com.mijuamon.core.test;

import com.mijuamon.core.loaders.Loader;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.gui.MainScreen;

import javax.swing.*;
import java.util.List;

public class Starter {

    public static void main (String args[])
    {

        JFrame frame = new JFrame("MainScreen");
        frame.setContentPane(new MainScreen().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


       // List<TeamModel> teams = Loader.loadInitialData();
    }
}

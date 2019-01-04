package com.mijuamon.gui;

import com.mijuamon.core.loaders.Loader;
import com.mijuamon.core.model.team.TeamModel;
import com.mijuamon.gui.matches.MatchesManagementDialog;
import com.mijuamon.gui.teams.TeamsManagementDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainScreen {
    public JPanel panel;
    private JPanel buttonsPanel;
    private JPanel journeyPanel;
    private JPanel calculatePanel;
    private JButton calculateButton;
    private JPanel numberMatchPanel;
    private JComboBox localCB1;
    private JComboBox localCB2;
    private JComboBox localCB3;
    private JComboBox localCB4;
    private JComboBox localCB5;
    private JComboBox localCB6;
    private JComboBox localCB7;
    private JComboBox localCB8;
    private JComboBox localCB9;
    private JComboBox localCB10;
    private JComboBox localCB11;
    private JComboBox localCB12;
    private JComboBox localCB13;
    private JComboBox localCB14;
    private JComboBox visitorCB1;
    private JPanel localPanel;
    private JPanel chancePanel;
    private JPanel resultPanel;
    private JPanel visitorPanel;
    private JButton gestionarEquiposButton;
    private JLabel nMatch1;
    private JLabel nMatch2;
    private JLabel nMatch3;
    private JLabel nMatch4;
    private JLabel nMatch5;
    private JLabel nMatch6;
    private JLabel nMatch7;
    private JLabel nMatch8;
    private JLabel nMatch9;
    private JLabel nMatch10;
    private JLabel nMatch11;
    private JLabel nMatch12;
    private JLabel nMatch13;
    private JLabel nMatch14;
    private JLabel nMatch15;
    private JComboBox visitorCB2;
    private JComboBox visitorCB3;
    private JComboBox visitorCB4;
    private JComboBox visitorCB5;
    private JComboBox visitorCB6;
    private JComboBox visitorCB7;
    private JComboBox visitorCB8;
    private JComboBox visitorCB9;
    private JComboBox visitorCB10;
    private JComboBox visitorCB11;
    private JComboBox visitorCB12;
    private JComboBox visitorCB13;
    private JComboBox visitorCB14;
    private JComboBox visitorCB15;
    private JLabel result1;
    private JLabel result2;
    private JLabel result3;
    private JLabel result4;
    private JLabel result5;
    private JLabel result6;
    private JLabel result7;
    private JLabel result8;
    private JLabel result9;
    private JLabel result10;
    private JLabel result11;
    private JLabel result12;
    private JLabel result13;
    private JLabel result14;
    private JLabel result15;
    private JLabel chance1;
    private JLabel chance2;
    private JLabel chance3;
    private JLabel chance4;
    private JLabel chance5;
    private JLabel chance6;
    private JLabel chance7;
    private JLabel chance8;
    private JLabel chance9;
    private JLabel chance10;
    private JLabel chance11;
    private JLabel chance12;
    private JLabel chance13;
    private JLabel chance14;
    private JLabel chance15;
    private JComboBox localCB15;
    private JButton gestionarPartidosButton;

    protected List<JLabel> numMatchesList;
    protected List<JComboBox> localList;
    protected List<JComboBox> visitorList;
    protected List<JLabel> resultList;
    protected List<JLabel> chanceList;

    protected List<TeamModel> teams;


    public MainScreen() {
        initialize();
        gestionarEquiposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final TeamsManagementDialog dialog = teams!=null?new TeamsManagementDialog(teams):new TeamsManagementDialog();
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        gestionarPartidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final MatchesManagementDialog dialog = new MatchesManagementDialog(teams);
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    private void initialize() {
        numMatchesList = new ArrayList<>();
        localList = new ArrayList<>();
        visitorList = new ArrayList<>();
        resultList = new ArrayList<>();
        chanceList = new ArrayList<>();

        numMatchesList.add(nMatch1);
        numMatchesList.add(nMatch2);
        numMatchesList.add(nMatch3);
        numMatchesList.add(nMatch4);
        numMatchesList.add(nMatch5);
        numMatchesList.add(nMatch6);
        numMatchesList.add(nMatch7);
        numMatchesList.add(nMatch8);
        numMatchesList.add(nMatch9);
        numMatchesList.add(nMatch10);
        numMatchesList.add(nMatch11);
        numMatchesList.add(nMatch12);
        numMatchesList.add(nMatch13);
        numMatchesList.add(nMatch14);
        numMatchesList.add(nMatch15);

        localList.add(localCB1);
        localList.add(localCB2);
        localList.add(localCB3);
        localList.add(localCB4);
        localList.add(localCB5);
        localList.add(localCB6);
        localList.add(localCB7);
        localList.add(localCB8);
        localList.add(localCB9);
        localList.add(localCB10);
        localList.add(localCB11);
        localList.add(localCB12);
        localList.add(localCB13);
        localList.add(localCB14);
        localList.add(localCB15);

        visitorList.add(visitorCB1);
        visitorList.add(visitorCB2);
        visitorList.add(visitorCB3);
        visitorList.add(visitorCB4);
        visitorList.add(visitorCB5);
        visitorList.add(visitorCB6);
        visitorList.add(visitorCB7);
        visitorList.add(visitorCB8);
        visitorList.add(visitorCB9);
        visitorList.add(visitorCB10);
        visitorList.add(visitorCB11);
        visitorList.add(visitorCB12);
        visitorList.add(visitorCB13);
        visitorList.add(visitorCB14);
        visitorList.add(visitorCB15);

        resultList.add(result1);
        resultList.add(result2);
        resultList.add(result3);
        resultList.add(result4);
        resultList.add(result5);
        resultList.add(result6);
        resultList.add(result7);
        resultList.add(result8);
        resultList.add(result9);
        resultList.add(result10);
        resultList.add(result11);
        resultList.add(result12);
        resultList.add(result13);
        resultList.add(result14);
        resultList.add(result15);

        chanceList.add(chance1);
        chanceList.add(chance2);
        chanceList.add(chance3);
        chanceList.add(chance4);
        chanceList.add(chance5);
        chanceList.add(chance6);
        chanceList.add(chance7);
        chanceList.add(chance8);
        chanceList.add(chance9);
        chanceList.add(chance10);
        chanceList.add(chance11);
        chanceList.add(chance12);
        chanceList.add(chance13);
        chanceList.add(chance14);
        chanceList.add(chance15);

        teams = Loader.loadInitialData();
    }

}

package com.mijuamon.gui.managementGui.editDialog.newEditDialog;

import static  com.mijuamon.core.constants.Controller.*;
import static  com.mijuamon.core.constants.Constants.*;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.gui.managementGui.editDialog.newEditDialog.scoreDialogs.ScoreDialog;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerManagementDialog extends JDialog {
    private JPanel contentPane;
    private JPanel scoresPanel;
    private JPanel buttonsPanel;
    private JPanel playerInfoPanel;
    private JLabel playeLabel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList scoresJList;
    private JButton eliminarButton;
    private JButton editButton;
    private JButton addNewButton;
    private JButton changeNameButton;
    private JTextField playerTextF;
    private JButton actualizarButton;

    List<ScoreModel> scores = new ArrayList<>();
    PlayerModel player = new PlayerModel();
    TeamModel team;
    List<TeamModel> teams;
    private boolean isNew=false;

    public PlayerManagementDialog(List<TeamModel> teams, TeamModel team, PlayerModel player) {
        this.team = team;
        this.player = player;
        this.scores = player.getScores();
        this.teams = teams;

        startDialog();

        //Edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (scoresJList.getSelectedValue() != null) {
                    ScoreDialog dialog = new ScoreDialog(teams, player, (ScoreModel) scoresJList.getSelectedValue());
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ScoreDialog dialog = new ScoreDialog(teams, player);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                scores.remove(scoresJList.getSelectedValue());
                refreshList();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshList();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.getScores().remove( scoresJList.getSelectedValue());
                refreshList();
            }
        });
    }

    private void refreshList() {
        DefaultListModel listModel = new DefaultListModel();
        scores.stream().forEach(x -> listModel.addElement(x));
        scoresJList.setModel(listModel);
    }


    public PlayerManagementDialog(List<TeamModel> teams, TeamModel team) {
        this.team = team;
        this.teams = teams;
        isNew=true;
        startDialog();
    }

    private void startDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        DefaultListModel listModel = new DefaultListModel();
        scoresJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scoresJList.setLayoutOrientation(JList.VERTICAL);
        playerTextF.setText(player.toString());

        scores.stream().forEach(x -> listModel.addElement(x));
        scoresJList.setModel(listModel);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if(isNew)
        {
            addPlayer(team, playerTextF.getText());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}

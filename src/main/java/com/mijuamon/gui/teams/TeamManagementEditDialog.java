package com.mijuamon.gui.teams;

import com.mijuamon.core.constants.Controller;
import com.mijuamon.core.dao.TeamDao;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import static com.mijuamon.core.constants.Constants.UPDATE_LABEL;


public class TeamManagementEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel teamInfoPanel;
    private JPanel teamPlayersPanel;
    private JTextField teamTF;
    private JList playerJList;
    private JPanel buttonsPanel;
    private JButton newPlayerButton;
    private JButton editButton;
    private JButton eliminarButton;
    private JButton traspasarButton;
    private JButton guardarButton;
    private JButton actualizarButton;

    private TeamModel team;

    //new team
    public TeamManagementEditDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DefaultListModel listModel = new DefaultListModel();
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerJList.setLayoutOrientation(JList.VERTICAL);
        playerJList.setModel(listModel);
        newPlayerButton.setEnabled(false);

        initializeListeners();
        refreshList();
    }

    public TeamManagementEditDialog(int teamId) {
        this.team = Controller.getTeam(teamId);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DefaultListModel listModel = new DefaultListModel();
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerJList.setLayoutOrientation(JList.VERTICAL);

        teamTF.setText(team.getName());
        this.team.getPlayers().stream().forEach(x -> listModel.addElement(x));
        playerJList.setModel(listModel);
        newPlayerButton.setEnabled(true);
        initializeListeners();
        refreshList();

    }

    private void initializeListeners() {

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

        //New Player Button
        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (team == null) {
                    return;
                }
                PlayerManagementDialog dialog = new PlayerManagementDialog(team);
                dialog.pack();
                dialog.setVisible(true);

            }
        });

        //Edit Player Button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (team == null || playerJList.getSelectedValue() == null) {
                    return;
                }

                PlayerManagementDialog dialog = new PlayerManagementDialog(team, (PlayerModel) playerJList.getSelectedValue());
                dialog.pack();
                dialog.setVisible(true);

            }
        });

        //Remove player button
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (team == null || playerJList.getSelectedValue() == null) {
                    return;
                }
                team.getPlayers().remove(playerJList.getSelectedValue());
                Controller.deletePlayer((PlayerModel) playerJList.getSelectedValue());
                refreshList();
            }
        });

        //Move player button
        traspasarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        //Save button
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (team == null && teamTF.getText() != null && !teamTF.getText().equals("")) {
                    guardarButton.setText(UPDATE_LABEL);
                    team = new TeamModel(teamTF.getText());
                    Controller.addTeam(team);
                    newPlayerButton.setEnabled(true);
                    DialogsUtil.infoMessage("Se ha creado un nuevo equipo");

                } else if (teamTF.getText() != null && !teamTF.getText().equals("")) {
                    team.setName(teamTF.getText());
                    TeamDao.getInstance().update(team);
                    DialogsUtil.infoMessage("Se ha actualizado el equipo");
                }
                refreshList();
            }
        });

        //Refresh button
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshList();
            }
        });
    }

    private void refreshList() {
        this.team=Controller.getTeam((team.getID()));

        List<PlayerModel> sortedList=new ArrayList(team.getPlayers());

        Collections.sort(sortedList, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        DefaultListModel listModel = new DefaultListModel();
        team.getPlayers().stream().forEach(x -> listModel.addElement(x));
        playerJList.setModel(listModel);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

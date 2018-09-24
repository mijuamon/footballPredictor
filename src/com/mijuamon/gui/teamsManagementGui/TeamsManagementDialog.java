package com.mijuamon.gui.teamsManagementGui;

import com.mijuamon.core.model.TeamModel;
import com.mijuamon.gui.teamsManagementGui.editDialog.TeamManagementEditDialog;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TeamsManagementDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton editRadioButon;
    private JRadioButton newTeamRadioButtton;
    private JComboBox editTeamCB;
    private JRadioButton descendRadioButton;
    private JComboBox descendTeamCB;
    private JRadioButton asscentionRadioButton;
    private JComboBox AsscentionCB;
    private JButton updateButton;

    protected List<TeamModel> teams;

    public TeamsManagementDialog(List<TeamModel> teams) {
        this.teams = teams;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        teams.stream().forEach(team -> editTeamCB.addItem(team));


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshLists();
            }
        });
    }

    private void refreshLists() {
        editTeamCB.removeAllItems();
        teams.stream().forEach(team -> editTeamCB.addItem(team));

    }

    private void onOK() {
        // add your code here
        TeamManagementEditDialog dialog;
        if (editRadioButon.isSelected()) {

            TeamModel selTeam = (TeamModel) editTeamCB.getSelectedItem();

            dialog = new TeamManagementEditDialog(selTeam, teams);
            dialog.pack();
            dialog.setVisible(true);
        }
        else if (newTeamRadioButtton.isSelected())
        {
            dialog = new TeamManagementEditDialog(teams);
            dialog.pack();
            dialog.setVisible(true);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}

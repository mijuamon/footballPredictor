package com.mijuamon.gui.managementGui;

import com.mijuamon.core.model.TeamModel;
import com.mijuamon.gui.managementGui.editDialog.TeamManagementEditDialog;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TeamManagementDialog extends JDialog {
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

    protected List<TeamModel> teams;

    public TeamManagementDialog(List<TeamModel> teams) {
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
    }

    private void onOK() {
        // add your code here
        TeamModel selTeam = (TeamModel) editTeamCB.getSelectedItem();

        TeamManagementEditDialog dialog = new TeamManagementEditDialog(selTeam, teams);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}

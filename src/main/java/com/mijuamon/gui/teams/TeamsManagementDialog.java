package com.mijuamon.gui.teams;

import com.mijuamon.core.dao.TeamDao;
import com.mijuamon.core.model.TeamModel;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TeamsManagementDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton editRadioButon;
    private JRadioButton newTeamRadioButtton;
    private JRadioButton descendRadioButton;
    private JComboBox descendTeamCB;
    private JRadioButton asscentionRadioButton;
    private JComboBox AsscentionCB;
    private JComboBox editTeamCB;
    private JButton updateButton;

    protected List<TeamModel> teams;

    public TeamsManagementDialog() {
        super();
        this.teams = TeamDao.getInstance().getAll(TeamModel.getModelName());
        initializeTeamsManagementDialog();

    }

    public TeamsManagementDialog(final List<TeamModel> teams) {
        super();
        this.teams = teams;
        initializeTeamsManagementDialog();
    }

    private void initializeTeamsManagementDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        refreshLists();

        if (CollectionUtils.isEmpty(teams)) {
            editRadioButon.setEnabled(false);
            editTeamCB.setEnabled(false);
        } else {
            teams.stream().forEach(team -> editTeamCB.addItem(team));
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent event) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                refreshLists();
                if (CollectionUtils.isEmpty(teams)) {
                    editRadioButon.setEnabled(false);
                    editTeamCB.setEnabled(false);
                } else {
                    teams.stream().forEach(team -> editTeamCB.addItem(team));
                }
            }
        });
    }

    private void refreshLists() {
        editTeamCB.removeAllItems();
        if (teams == null) {
            editRadioButon.setEnabled(false);
            editTeamCB.setEnabled(false);

        } else {
            editRadioButon.setEnabled(true);
            editTeamCB.setEnabled(true);
        }


    }

    private void onOK() {
        // add your code here
        TeamManagementEditDialog dialog;
        if (editRadioButon.isSelected()) {

            final TeamModel selTeam = (TeamModel) editTeamCB.getSelectedItem();

            dialog = new TeamManagementEditDialog(selTeam, teams);
            dialog.pack();
            dialog.setVisible(true);
        } else if (newTeamRadioButtton.isSelected()) {
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

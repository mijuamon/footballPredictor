package com.mijuamon.gui.managementGui.editDialog;

import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.gui.managementGui.editDialog.newEditDialog.PlayerManagementDialog;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;


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
    private JButton cambiarNombreButton;

    public TeamManagementEditDialog(TeamModel team, List<TeamModel> teams) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DefaultListModel listModel = new DefaultListModel();
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerJList.setLayoutOrientation(JList.VERTICAL);

        teamTF.setText(team.getName());
        team.getPlayers().stream().forEach(x -> listModel.addElement(x));
        playerJList.setModel(listModel);

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
                PlayerManagementDialog dialog = new PlayerManagementDialog(teams,team);
                dialog.pack();
                dialog.setVisible(true);

            }
        });

        //Edit Player Button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(playerJList.getSelectedValue()!=null) {
                    PlayerManagementDialog dialog = new PlayerManagementDialog(teams,team,(PlayerModel)playerJList.getSelectedValue());
                    dialog.pack();
                    dialog.setVisible(true);
                }

            }
        });

        //Remove player button
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        //Move player button
        traspasarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
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

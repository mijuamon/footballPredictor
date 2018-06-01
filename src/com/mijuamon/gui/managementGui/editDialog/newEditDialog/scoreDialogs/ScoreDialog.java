package com.mijuamon.gui.managementGui.editDialog.newEditDialog.scoreDialogs;

import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox localComboBox;
    private JComboBox visitorComboBox;
    private JTextField resultTF;
    private JTextField playerTF;
    private JSpinner scoreSpinner;

    public ScoreDialog(List<TeamModel> teams, PlayerModel player, ScoreModel score) {

        teams.stream().forEach(team -> localComboBox.addItem(team));
        teams.stream().forEach(team -> visitorComboBox.addItem(team));

        localComboBox.setSelectedItem(score.getMatch().getLocal());
        visitorComboBox.setSelectedItem(score.getMatch().getVisitor());

        playerTF.setText(player.toString());
        scoreSpinner.setValue(score.getScore());
        startDialog();
    }

    public ScoreDialog(List<TeamModel> teams) {
        teams.stream().forEach(team -> localComboBox.addItem(team));
        teams.stream().forEach(team -> visitorComboBox.addItem(team));
        startDialog();
    }

    private void startDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

package com.mijuamon.gui.managementGui.editDialog.newEditDialog;

import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class playerManagementDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTF;
    private JPanel buttonsPanel;
    private JPanel listPamel;
    private JButton addButton;
    private JList scoresJList;
    private JButton editarButton;
    private JButton eliminarButton;

    List<ScoreModel> scores = new ArrayList<>();
    PlayerModel player = new PlayerModel();
    TeamModel team = new TeamModel();

    public playerManagementDialog(TeamModel team, PlayerModel player) {
        this.team=team;
        this.player=player;
        this.scores=player.getScores();

        startDialog();
    }


    public playerManagementDialog(TeamModel team) {
        this.team=team;
        startDialog();
    }

    private void startDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        DefaultListModel listModel = new DefaultListModel();
        scoresJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scoresJList.setLayoutOrientation(JList.VERTICAL);

        nameTF.setText(player.getName());
        scores.stream().forEach(x -> listModel.addElement(x));
        scoresJList.setModel(listModel);



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

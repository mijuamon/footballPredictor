package com.mijuamon.gui.managementGui.editDialog.newEditDialog.scoreDialogs;

import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox matchComboBox;
    private JTextField resultTF;
    private JTextField playerTF;
    private JSpinner scoreSpinner;

    private List<TeamModel>teams;
    private PlayerModel player;
    private ScoreModel score;

    private boolean isNew;

    public ScoreDialog(List<TeamModel> teams, PlayerModel player, ScoreModel score) {
        //edit score
        isNew=false;
        this.teams=teams;
        this.player=player;
        this.score=score;


        matchComboBox.addItem(score.getMatch());

        matchComboBox.setSelectedItem(score.getMatch().getLocal());
        matchComboBox.setEnabled(false);
        resultTF.setEnabled(false);
        resultTF.setText(score.getMatch().getResult());
        playerTF.setText(player.toString());
        scoreSpinner.setValue(score.getScore());
        startDialog();
    }

    public ScoreDialog(List<TeamModel> teams, PlayerModel player) {
        isNew=true;
        this.teams=teams;
        this.player=player;
        matchComboBox.addItem(null);
        player.getTeam().getMatches().stream().forEach(x->matchComboBox.addItem(x));

        teams.stream().forEach(team -> matchComboBox.addItem(team));

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
        if(isNew) {
            ScoreModel score = new ScoreModel();
            score.setScore((Integer)scoreSpinner.getValue());
            score.setPlayerID(player.getPlayerID());
            score.setPlayer(player);
            score.setMatchID(((MatchModel)matchComboBox.getSelectedItem()).getMatchId());
            score.setMatch(((MatchModel)matchComboBox.getSelectedItem()));
        }
        else {
            score.setScore((Integer) scoreSpinner.getValue());
        }
        dispose();
    }
}

package com.mijuamon.gui.teams;

import com.mijuamon.core.constants.Controller;
import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ScoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox matchComboBox;
    private JTextField playerTF;
    private JSpinner scoreSpinner;

    private PlayerModel player;
    private ScoreModel score;

    private boolean isNew;

    //Edit score
    public ScoreDialog(PlayerModel player, ScoreModel score) {
        //edit score
        isNew = false;
        this.player = player;
        this.score = score;


        matchComboBox.addItem(score.getMatch());

        matchComboBox.setSelectedItem(score.getMatch().getLocal());
        matchComboBox.setEnabled(false);
        playerTF.setText(player.toString());
        scoreSpinner.setValue(score.getScore());
        startDialog();
    }

    // New Score
    public ScoreDialog(PlayerModel player, List<MatchModel> matchs) {
        if (player != null) {
            isNew = true;
            this.player = player;
            playerTF.setText(player.toString());
            matchComboBox.addItem(null);
            matchs.stream().forEach(match->matchComboBox.addItem(match));
            if (matchComboBox.getItemCount() > 0) {
                startDialog();
            }
        } else {
            DialogsUtil.errorMessage("Este jugador aun no existe",
                    "Jugador no existente");
        }
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
        if (isNew) {
            ScoreModel score = new ScoreModel();
            score.setScore((Integer) scoreSpinner.getValue());
            score.setPlayer(player);
            score.setMatch(((MatchModel) matchComboBox.getSelectedItem()));
            player.getScores().add(score);

            Controller.addScore(score);
            Controller.updatePlayer(player);
            DialogsUtil.infoMessage("Se ha creado una puntuación");
        } else {
            score.setScore((Integer) scoreSpinner.getValue());
            Controller.updateScore(score);
            DialogsUtil.infoMessage("Se ha actualizado la puntuación");
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

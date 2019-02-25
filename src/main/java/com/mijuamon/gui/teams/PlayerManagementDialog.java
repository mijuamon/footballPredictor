package com.mijuamon.gui.teams;

import com.mijuamon.core.constants.Controller;
import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.PlayerModel;
import com.mijuamon.core.model.ScoreModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mijuamon.core.constants.Controller.addPlayer;

public class PlayerManagementDialog extends JDialog {
    private JPanel contentPane;
    private JPanel scoresPanel;
    private JPanel buttonsPanel;
    private JPanel playerInfoPanel;
    private JLabel playeLabel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList scoresJList;
    private JButton removeButton;
    private JButton editButton;
    private JButton addNewButton;
    private JTextField playerTextF;
    private JButton updateButton;
    private JButton guardarButton;

    private Set<ScoreModel> scores = new HashSet<>();
    private PlayerModel player;
    private TeamModel team;
    private boolean isNew;

    //update player
    public PlayerManagementDialog(TeamModel team, PlayerModel player) {
        super();
        this.team = team;
        this.player = player;
        this.scores =  new HashSet<>(player.getScores());

        startDialog();
        initializeListeners();
    }

    //new player
    public PlayerManagementDialog(TeamModel team) {
        super();
        this.team = team;
        isNew = true;
        addNewButton.setEnabled(false);
        editButton.setEnabled(false);
        removeButton.setEnabled(false);
        startDialog();
        initializeListeners();
    }

    private void initializeListeners() {
        //Edit score
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (player == null || scoresJList.getSelectedValue() == null) {
                    return;
                }

                ScoreDialog dialog = new ScoreDialog(player, (ScoreModel) scoresJList.getSelectedValue());
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        //Nuevo score
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (player == null) {
                    return;
                }
                addNewButton.setEnabled(true);
                editButton.setEnabled(true);
                removeButton.setEnabled(true);
                List<MatchModel> matchs = player.getTeam().getMatches().stream()
                        .filter(match -> !player.getScores().stream()
                                .anyMatch(score -> score.getMatch().equals(match)))
                        .collect(Collectors.toList());
                if (matchs.size() > 0) {
                    ScoreDialog dialog = new ScoreDialog(player, matchs);
                    dialog.pack();
                    dialog.setVisible(true);
                } else {
                    DialogsUtil.errorMessage("No quedan mas partidos sin puntuar",
                            "Error nueva puntuación");
                }
            }
        });
        //Remove score
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (player == null || scoresJList.getSelectedValue() == null) {
                    return;
                }
                if (DialogsUtil.questionMessage("¿Esta seguro de eliminar la puntuacion?", "eliminar puntuacion")) {
                    scores.remove(scoresJList.getSelectedValue());
                }
                refreshList();
            }
        });
        //Update form
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshList();
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerTextF.getText() == null || playerTextF.getText().equals("")) {
                    DialogsUtil.infoMessage("Es necesario que tenga nombre");
                } else {
                    if (player == null) {
                        player = new PlayerModel(playerTextF.getText(), team);
                        team.addPlayer(player);
                        Controller.addPlayer(player);
                        Controller.updateTeam(team);
                        DialogsUtil.infoMessage("Crear jugador", "Se ha creado el jugador");

                    } else {
                        player.setName(playerTextF.getText());
                        Controller.updatePlayer(player);
                        DialogsUtil.infoMessage("Actualizar jugador", "Se ha actualizado el jugador");
                    }
                }
                refreshList();
                addNewButton.setEnabled(true);
                editButton.setEnabled(true);
                removeButton.setEnabled(true);
            }
        });
    }

    private void refreshList() {
        scores = Controller.getAllScores(player.getID());

        DefaultListModel listModel = new DefaultListModel();
        scores.stream().forEach(x -> listModel.addElement(x));
        scoresJList.setModel(listModel);
    }


    private void startDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        DefaultListModel listModel = new DefaultListModel();
        scoresJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scoresJList.setLayoutOrientation(JList.VERTICAL);
        playerTextF.setText(player == null ? "" : player.toString());

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
        if (isNew) {
            addPlayer(team, playerTextF.getText());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}

package com.mijuamon.gui.matches;

import com.mijuamon.core.constants.Controller;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class NewMatchDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox localBox;
    private JComboBox visitorBox;
    private JTextField resultTF;
    private JTextField seasonTF;
    private JTextField weekTF;
    private static String week = "1";
    private static String season = "2019";

    public NewMatchDialog(List<TeamModel> teams) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        weekTF.setText(week);
        seasonTF.setText(season);

        teams.stream().forEach(team -> localBox.addItem(team));
        teams.stream().forEach(team -> visitorBox.addItem(team));


        initializeListeners();
    }

    private void initializeListeners() {
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
        week = weekTF.getText();
        season = seasonTF.getText();

        if (localBox.getSelectedItem() == visitorBox.getSelectedItem()) {
            DialogsUtil.errorMessage("El equipo local y visitante no puede ser el mismo");
        } else if (resultTF.getText().split("-").length != 2) {
            DialogsUtil.errorMessage("El formato del resultado es incorrecto. Se espera en formato \"<local>-<visitante>\"");
        } else {
            Controller.addMatch((TeamModel) localBox.getSelectedItem(), (TeamModel) visitorBox.getSelectedItem(), resultTF.getText(), week, season);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

package com.mijuamon.gui.matches;

import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.util.DialogsUtil;

import javax.swing.*;
import java.awt.event.*;

public class EditMatchDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField resultTF;
    private MatchModel match;

    public EditMatchDialog(final MatchModel match) {
        this.match = match;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        resultTF.setText(match.getResult());
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
        if (resultTF.getText().split("-").length != 2) {
            DialogsUtil.errorMessage("El formato del resultado es incorrecto. Se espera en formato \"<local>-<visitante>\"");
        }
        else {
            match.setResult(resultTF.getText());

            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

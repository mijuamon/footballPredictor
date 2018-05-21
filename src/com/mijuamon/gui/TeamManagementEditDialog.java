package com.mijuamon.gui;

import com.mijuamon.core.model.TeamModel;

import javax.swing.*;
import java.awt.event.*;


public class TeamManagementEditDialog extends JDialog {
    private JPanel contentPane = new JPanel();
    private JButton buttonOK;
    private JPanel teamInfoPanel;
    private JPanel teamPlayersPanel;
    private JTextField textField1;
    private JList playerJList;
    private JPanel buttonsPanel;
    private JButton newPlayerButton;
    private JButton editButton;
    private JButton eliminarButton;
    private JButton traspasarButton;
    private JButton cambiarNombreButton;

    public TeamManagementEditDialog(TeamModel team) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DefaultListModel listModel = new DefaultListModel();
        playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerJList.setLayoutOrientation(JList.VERTICAL);


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

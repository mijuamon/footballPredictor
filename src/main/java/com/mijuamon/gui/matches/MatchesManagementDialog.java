package com.mijuamon.gui.matches;

import com.mijuamon.core.constants.Controller;
import com.mijuamon.core.dao.MatchDao;
import com.mijuamon.core.dao.TeamDao;
import com.mijuamon.core.model.MatchModel;
import com.mijuamon.core.model.TeamModel;
import com.mijuamon.core.util.DialogsUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MatchesManagementDialog extends JDialog {
    private JPanel contentPane;
    private JButton updateButton;
    private JList matchList;
    private JButton añadirButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JButton closeButton;
    private List<MatchModel> matches;
    private List<TeamModel> teams;

    public MatchesManagementDialog() {
        this.teams = (List<TeamModel>) (List<?>) TeamDao.getInstance().getAll(TeamModel.getModelName());
        setContentPane(contentPane);
        setModal(true);

        DefaultListModel listModel = new DefaultListModel();
        matchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matchList.setLayoutOrientation(JList.VERTICAL);
        refreshList();

        initializeListeners();

    }

    private void initializeListeners() {
        //Añadir
        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMatchDialog dialog = new NewMatchDialog(teams);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        //Editar
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (matchList.getSelectedIndex() >= 0) {
                    EditMatchDialog dialog = new EditMatchDialog((MatchModel) matchList.getSelectedValue());
                    dialog.pack();
                    dialog.setVisible(true);
                } else {
                    DialogsUtil.warningMessage("Debe seleccionar un partido");
                }
            }
        });
        //Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DialogsUtil.questionMessage("¿estas seguro de borrar el resultado seleccionado?", "Eliminacion de resultado")) {
                    Controller.deleteMatch((MatchModel) matchList.getSelectedValue());
                }
            }
        });
        //Actualizar
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshList();
            }
        });
        //Cerrar
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void refreshList() {
        matches = MatchDao.getInstance().getAll(MatchModel.getModelName());

        DefaultListModel listModel = new DefaultListModel();
        matches.stream().forEach(x -> listModel.addElement(x));
        matchList.setModel(listModel);
    }

}

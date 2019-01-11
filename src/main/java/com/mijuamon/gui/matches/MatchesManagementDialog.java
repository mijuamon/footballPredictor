package com.mijuamon.gui.matches;

import com.mijuamon.core.dao.HibernateDao;
import com.mijuamon.core.model.match.MatchModel;
import com.mijuamon.core.model.team.TeamModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MatchesManagementDialog extends JDialog {
    private JPanel contentPane;
    private JButton updateButton;
    private JList matchList;
    private JButton añadirButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private ArrayList<MatchModel> matches;
    private List<TeamModel> teams;

    public MatchesManagementDialog(List<TeamModel> teams) {
        this.teams=teams;
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

            }
        });
        //Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Actualizar
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshList();
            }
        });
    }

    private void refreshList() {
        matches = (ArrayList<MatchModel>) (List<?>) HibernateDao.getInstance().getAll(MatchModel.getModelName());

        DefaultListModel listModel = new DefaultListModel();
        matches.stream().forEach(x -> listModel.addElement(x));
        matchList.setModel(listModel);
    }

}

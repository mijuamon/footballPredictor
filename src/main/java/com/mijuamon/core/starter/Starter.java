package com.mijuamon.core.starter;

import com.mijuamon.core.util.HibernateUtil;
import com.mijuamon.gui.MainScreen;
import org.hibernate.Session;

import javax.swing.*;

public class Starter {

    public static void main (String args[])
    {

        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.close();
        JFrame frame = new JFrame("MainScreen");
        frame.setContentPane(new MainScreen().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



       // List<TeamModel> teams = Loader.loadInitialData();
    }
}

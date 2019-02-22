package com.mijuamon.core.util;

import javax.swing.*;

public class DialogsUtil {
    private final static String ERROR_TITLE = "Error";
    private final static String INFO_TITLE = "Informacion";
    private final static String WARNING_TITLE = "Aviso";
    private final static String QUESTION_TITLE = "Aviso";

    public static void warningMessage(final String msg) {
        warningMessage(WARNING_TITLE, msg);
    }

    public static void warningMessage(final String title, final String msg) {
        showMessage(title, msg, JOptionPane.WARNING_MESSAGE);
    }

    public static void infoMessage(final String msg) {
        infoMessage(INFO_TITLE, msg);
    }

    public static void infoMessage(final String title, final String msg) {
        showMessage(title, msg, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorMessage(final String msg) {
        errorMessage(ERROR_TITLE, msg);
    }

    public static void errorMessage(final String title, final String msg) {
        showMessage(title, msg, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean questionMessage(final String msg, final String title){

        int dialogResult = JOptionPane.showConfirmDialog (null, msg,title,JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }

    private static void showMessage(final String title, final String text, int option) {
        JOptionPane.showMessageDialog(null,
                text,
                title,
                option);
    }


}

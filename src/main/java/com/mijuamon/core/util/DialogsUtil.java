package com.mijuamon.core.util;

import javax.swing.*;

public class DialogsUtil {
    private final static String ERROR_TITLE = "Error";
    private final static String INFO_TITLE = "Informacion";
    private final static String WARNING_TITLE = "Aviso";
    private final static String QUESTION_TITLE = "Aviso";


    public static void questionMessage(final String text) {
        questionMessage(QUESTION_TITLE, text);
    }

    public static void questionMessage(final String title, final String text) {
        showMessage(title, text, JOptionPane.QUESTION_MESSAGE);
    }

    public static void warningMessage(final String text) {
        warningMessage(WARNING_TITLE, text);
    }

    public static void warningMessage(final String title, final String text) {
        showMessage(title, text, JOptionPane.WARNING_MESSAGE);
    }

    public static void infoMessage(final String text) {
        infoMessage(INFO_TITLE, text);
    }

    public static void infoMessage(final String title, final String text) {
        showMessage(title, text, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorMessage(final String text) {
        errorMessage(ERROR_TITLE, text);
    }

    public static void errorMessage(final String title, final String text) {
        showMessage(title, text, JOptionPane.ERROR_MESSAGE);
    }

    private static void showMessage(final String title, final String text, int option) {
        JOptionPane.showMessageDialog(null,
                text,
                title,
                option);
    }


}

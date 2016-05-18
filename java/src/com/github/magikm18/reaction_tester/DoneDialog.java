package com.github.magikm18.reaction_tester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoneDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea1;

    public DoneDialog(String text) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        textArea1.setText(text);
        setMinimumSize(new Dimension(600, 400));

        buttonOK.addActionListener(e -> dispose());
    }
}

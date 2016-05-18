package com.github.magikm18.reaction_tester;

import javax.swing.SwingUtilities;
import java.io.InputStreamReader;

public final class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame(new Config(new InputStreamReader(Main.class.getResourceAsStream("config.json")))).setVisible(true));
    }
}

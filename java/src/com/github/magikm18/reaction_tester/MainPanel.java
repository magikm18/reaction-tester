package com.github.magikm18.reaction_tester;

import javax.swing.*;
import java.awt.*;

final class MainPanel extends JPanel {
    char text;
    int pos;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth() / 6, getHeight() / 3);
        g.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 0, 360);
        g.drawLine(centerX - radius, centerY, centerX + radius, centerY);
        int off30X = radius / 2;
        int off30Y = (int) (Math.cos(Math.toRadians(30)) * radius);
        g.drawLine(centerX - off30X, centerY - off30Y, centerX + off30X, centerY + off30Y);
        g.drawLine(centerX - off30X, centerY + off30Y, centerX + off30X, centerY - off30Y);
        if (text == '\0')
            return;
        g.setFont(new Font("Times New Roman", 0, 40));
        FontMetrics metrics = g.getFontMetrics();
        int textX, textY;
        if (pos % 3 == 1) {
            textX = centerX;
            textY = centerY + radius / (pos == 4 ? 2 : -2);
        } else {
            textX = centerX + (pos == 2 || pos == 3 ? off30Y : -off30Y) / 2;
            textY = centerY + (int) ((pos > 2 ? radius : -radius) / 4);
        }
        g.drawString(String.valueOf(text), textX - metrics.charWidth(text) / 2, textY - metrics.getHeight() / 2 + 30);
    }
}

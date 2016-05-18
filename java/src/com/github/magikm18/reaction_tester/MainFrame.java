package com.github.magikm18.reaction_tester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.stream.Collectors;

final class MainFrame extends JFrame implements KeyListener {
    MainPanel panel;
    Config config;
    int posCount = -1, typeCount = 0, round = 0, segment = 0, character = 0, numerator = 0, denominator = 0;
    boolean type = false, started = false;
    Random random = new Random();
    long start;

    MainFrame(Config config) {
        this.config = config;
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(this);
        add(panel = new MainPanel());
    }

    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    @SuppressWarnings("deprecation")
    private void next() {
        CharInfo c;
        RoundSegment s;
        if (started) {
            RoundDefinition r = config.rounds[round];
            s = r.segments[segment % r.segments.length];
            if (++character == s.count) {
                posCount = -1;
                character = 0;
                if (++segment == r.segments.length * r.count) {
                    float difference = (float)(getCurrentTime() - start) / 1000;
                    panel.text = '\0';
                    panel.repaint();
                    new DoneDialog(String.format("Reaction Time Tester\nCopyright (c) 2016 Nicholas Mertin\n\n%d/%d = %.2f%%\n\nTotal time: %.3fs (%.3fs per glyph)", numerator, denominator, (float)numerator / denominator * 100, difference, difference / denominator)).show();
                    segment = typeCount = numerator = denominator = 0;
                    started = false;
                    if (++round == config.rounds.length)
                        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    return;
                }
                s = r.segments[segment % r.segments.length];
            }
        } else {
            start = getCurrentTime();
            started = true;
            s = config.rounds[round].segments[0];
        }
        if (typeCount == 3) {
            java.util.List<CharInfo> charInfos = Arrays.stream(s.chars).filter(i -> i.type != type).collect(Collectors.toList());
            c = charInfos.get(random.nextInt(charInfos.size()));
        } else
            c = s.chars[random.nextInt(s.chars.length)];
        posCount = (posCount + 1) % 3;
        panel.pos = posCount + (c.bottom ? 3 : 0);
        panel.text = c.character;
        if (c.type == type)
            ++typeCount;
        else {
            type = c.type;
            typeCount = 1;
        }
        ++denominator;
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!started)
            next();
        else {
            if (e.getKeyChar() == (type ? '3' : '2'))
                ++numerator;
            if (e.getKeyChar() == '2' || e.getKeyChar() == '3')
                next();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}

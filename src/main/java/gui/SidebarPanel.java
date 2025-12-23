package gui;

import main.BioskopApp;
import util.Theme;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {
        setPreferredSize(new Dimension(220, 0));
        setBackground(Theme.BG_SIDEBAR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        JLabel logo = new JLabel("🎬 BIOSKOP");
        logo.setFont(Theme.TITLE);
        logo.setForeground(Theme.ACCENT);
        logo.setAlignmentX(CENTER_ALIGNMENT);
        add(logo);

        add(Box.createVerticalStrut(30));

        add(menuButton("Dashboard", "dashboard"));
        add(menuButton("Pesan Tiket", "form"));
        add(menuButton("List Tiket", "list"));
        add(menuButton("Riwayat", "riwayat"));
    }

    private JButton menuButton(String text, String cardName) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setBackground(Theme.BG_MAIN);
        btn.setForeground(Theme.TEXT);
        btn.setFont(Theme.NORMAL);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btn.addActionListener(e -> {
            if (cardName.equals("riwayat")) {
                BioskopApp.riwayatPanel.refresh();
            }

            BioskopApp.card.show(BioskopApp.content, cardName);
        });

        return btn;
    }
}

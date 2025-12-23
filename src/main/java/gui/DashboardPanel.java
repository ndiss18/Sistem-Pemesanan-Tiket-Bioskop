package gui;

import util.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setBackground(Theme.BG_MAIN);
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("🎬 Dashboard Bioskop");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.ACCENT);
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 1));
        content.setBackground(Theme.BG_MAIN);

        content.add(buildPosterPanel());

        add(content, BorderLayout.CENTER);
    }

    private JPanel buildPosterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JLabel poster = new JLabel("POSTER");
        poster.setForeground(Color.WHITE);
        poster.setHorizontalAlignment(JLabel.CENTER);

        URL url = ClassLoader.getSystemResource("assets/poster1.jpg");
        System.out.println("URL POSTER = " + url);

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage()
                    .getScaledInstance(300, 420, Image.SCALE_SMOOTH);
            poster.setIcon(new ImageIcon(img));
            poster.setText("");
        } else {
            poster.setText("POSTER TIDAK TERBACA");
        }

        panel.add(poster, BorderLayout.CENTER);
        return panel;
    }
}

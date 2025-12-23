package main;

import gui.DashboardPanel;
import gui.FormTiketPanel;
import gui.ListTiketPanel;
import gui.RiwayatPanel;
import gui.SidebarPanel;
import model.Tiket;
import util.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BioskopApp extends JFrame {

    public static ArrayList<Tiket> data = FileHelper.load();

    public static ListTiketPanel listPanel;
    public static RiwayatPanel riwayatPanel;

    public static CardLayout card = new CardLayout();
    public static JPanel content = new JPanel(card);

    public BioskopApp() {
        setTitle("🎬 Sistem Pemesanan Tiket Bioskop");
        setSize(950, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DashboardPanel dashboard = new DashboardPanel();
        FormTiketPanel formPanel = new FormTiketPanel();
        listPanel = new ListTiketPanel();
        riwayatPanel = new RiwayatPanel();

        content.add(dashboard, "dashboard");
        content.add(formPanel, "form");
        content.add(listPanel, "list");
        content.add(riwayatPanel, "riwayat");

        add(new SidebarPanel(), BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        card.show(content, "dashboard");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BioskopApp::new);
    }
}

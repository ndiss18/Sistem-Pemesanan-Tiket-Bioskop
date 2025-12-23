package gui;

import main.BioskopApp;
import model.Tiket;
import util.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;


public class RiwayatPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public RiwayatPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.BG_MAIN);

        JLabel title = new JLabel("📜 Riwayat Pemesanan Tiket");
        title.setFont(Theme.TITLE);
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Nama", "Film", "Studio", "Jumlah", "Total"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ⬅️ KUNCI EDIT
            }
        };


        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(Theme.NORMAL);
        table.getTableHeader().setFont(Theme.NORMAL);
        table.setShowGrid(true);              // ⬅️ GARIS TABEL
        table.setGridColor(Color.GRAY);       // ⬅️ WARNA GARIS
        table.setSelectionBackground(Theme.ACCENT);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        add(scroll, BorderLayout.CENTER);
    }

    public void refresh() {
        model.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Tiket t : BioskopApp.data) {
            model.addRow(new Object[]{
                    t.getNama(),
                    t.getFilm(),
                    t.getStudio(),
                    t.getJumlah(),
                    "Rp " + t.getTotal(),
                    t.getTanggal().format(formatter)
            });
        }
    }
}

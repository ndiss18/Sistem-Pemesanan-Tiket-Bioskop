package gui;

import main.BioskopApp;
import model.Tiket;
import util.FileHelper;
import util.Theme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListTiketPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private static final int HARGA_TIKET = 35000;

    public ListTiketPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.BG_MAIN);

        model = new DefaultTableModel(
                new String[]{"Nama", "Film", "Jumlah", "Harga/Tiket", "Total Harga", "Tanggal"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabel tidak bisa diedit langsung
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(Theme.NORMAL);
        table.getTableHeader().setFont(Theme.NORMAL);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Theme.BG_MAIN);

        JButton btnEdit = new JButton("Edit Jumlah");
        JButton btnHapus = new JButton("Hapus");

        styleButton(btnEdit);
        styleButton(btnHapus);

        btnEdit.addActionListener(e -> editJumlah());
        btnHapus.addActionListener(e -> hapusData());

        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);

        add(btnPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    public void refreshTable() {
        model.setRowCount(0);
        for (Tiket t : BioskopApp.data) {
            int total = t.getJumlah() * HARGA_TIKET;
            model.addRow(new Object[]{
                    t.getNama(),
                    t.getFilm(),
                    t.getJumlah(),
                    "Rp " + HARGA_TIKET,
                    "Rp " + total,
                    t.getTanggal()
            });
        }
    }

    private void editJumlah() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "❗ Pilih data terlebih dahulu");
            return;
        }

        String input = JOptionPane.showInputDialog(this, "Masukkan jumlah baru:");
        if (input == null) return;

        try {
            int jumlahBaru = Integer.parseInt(input);
            if (jumlahBaru <= 0) {
                JOptionPane.showMessageDialog(this, "❌ Jumlah harus lebih dari 0");
                return;
            }

            Tiket tiket = BioskopApp.data.get(row);
            tiket.setJumlah(jumlahBaru);
            tiket.setTotal(jumlahBaru * 35000);

            FileHelper.save(BioskopApp.data);
            refreshTable();

            JOptionPane.showMessageDialog(this, "✅ Jumlah tiket berhasil diubah");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Jumlah harus angka");
        }
    }

    private void hapusData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "❗ Pilih data terlebih dahulu");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            BioskopApp.data.remove(row);
            FileHelper.save(BioskopApp.data);
            refreshTable();

            JOptionPane.showMessageDialog(this, "🗑️ Data berhasil dihapus");
        }
    }

    private void styleButton(JButton btn) {
        btn.setFont(Theme.NORMAL);
        btn.setBackground(Theme.ACCENT);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
    }
}

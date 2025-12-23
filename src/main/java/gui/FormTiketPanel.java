package gui;

import main.BioskopApp;
import model.Tiket;
import util.FileHelper;
import util.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class FormTiketPanel extends JPanel {

    private JTextField tfNama;
    private JTextField tfFilm;
    private JTextField tfJumlah;

    private static final int HARGA_TIKET = 35000;

    public FormTiketPanel() {
        setBackground(Theme.BG_MAIN);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Pesan Tiket Bioskop");
        title.setFont(Theme.TITLE);
        title.setForeground(Theme.ACCENT);

        tfNama = new JTextField(15);
        tfFilm = new JTextField(15);
        tfJumlah = new JTextField(15);

        styleField(tfNama);
        styleField(tfFilm);
        styleField(tfJumlah);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(Theme.ACCENT);
        btnSimpan.setForeground(Color.BLACK);
        btnSimpan.setFont(Theme.NORMAL);
        btnSimpan.setFocusPainted(false);

        btnSimpan.addActionListener(e -> simpanData());

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridy++;
        add(label("Nama Pemesan"), gbc);
        gbc.gridy++;
        add(tfNama, gbc);

        gbc.gridy++;
        add(label("Judul Film"), gbc);
        gbc.gridy++;
        add(tfFilm, gbc);

        gbc.gridy++;
        add(label("Jumlah Tiket"), gbc);
        gbc.gridy++;
        add(tfJumlah, gbc);

        gbc.gridy++;
        add(btnSimpan, gbc);
    }

    private void simpanData() {
        try {
            if (tfNama.getText().trim().isEmpty()
                    || tfFilm.getText().trim().isEmpty()
                    || tfJumlah.getText().trim().isEmpty()) {
                throw new Exception();
            }

            int jumlah = Integer.parseInt(tfJumlah.getText());
            int total = jumlah * HARGA_TIKET;

            Tiket tiket = new Tiket(
                    tfNama.getText(),
                    tfFilm.getText(),
                    "Studio 1",
                    jumlah,
                    total
            );

            BioskopApp.data.add(tiket);

            BioskopApp.data.sort(
                    Comparator.comparing(Tiket::getNama)
            );

            FileHelper.save(BioskopApp.data);

            BioskopApp.listPanel.refreshTable();

            BioskopApp.card.show(BioskopApp.content, "list");

            JOptionPane.showMessageDialog(this, "Tiket berhasil disimpan");

            tfNama.setText("");
            tfFilm.setText("");
            tfJumlah.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah tiket harus berupa angka");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi");
        }
    }

    private void styleField(JTextField tf) {
        tf.setFont(Theme.NORMAL);
        tf.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(Theme.NORMAL);
        lbl.setForeground(Theme.TEXT);
        return lbl;
    }
}

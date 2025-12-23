package util;

import model.Tiket;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHelper {

    private static final String FILE_PATH = "data/tiket.csv";

    public static ArrayList<Tiket> load() {
        ArrayList<Tiket> list = new ArrayList<>();

        File file = new File(FILE_PATH);

        // ⬅️ Kalau file belum ada, kembalikan list kosong
        if (!file.exists()) {
            System.out.println("File belum ada, data kosong");
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                String nama = d[0];
                String film = d[1];
                String studio = d[2];
                int jumlah = Integer.parseInt(d[3]);
                int total = Integer.parseInt(d[4]);
                LocalDate tanggal = LocalDate.parse(d[5]);

                Tiket t = new Tiket(nama, film, studio, jumlah, total);
                t.setTanggal(tanggal);

                list.add(t);
            }

            System.out.println("LOAD DATA BERHASIL: " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void save(ArrayList<Tiket> list) {
        try {
            File file = new File(FILE_PATH);

            // ⬅️ INI KUNCI UTAMA (BUAT FOLDER data/)
            file.getParentFile().mkdirs();

            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (Tiket t : list) {
                pw.println(
                        t.getNama() + "," +
                                t.getFilm() + "," +
                                t.getStudio() + "," +
                                t.getJumlah() + "," +
                                t.getTotal() + "," +
                                t.getTanggal()
                );
            }
            pw.close();

            System.out.println("SAVE DATA BERHASIL");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

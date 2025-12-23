package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Tiket implements Serializable {

    private String nama;
    private String film;
    private String studio;
    private int jumlah;
    private int total;
    private LocalDate tanggal;

    public Tiket(String nama, String film, String studio, int jumlah, int total) {
        this.nama = nama;
        this.film = film;
        this.studio = studio;
        this.jumlah = jumlah;
        this.total = total;
        this.tanggal = LocalDate.now(); // ⬅️ otomatis
    }

    public String getNama() { return nama; }
    public String getFilm() { return film; }
    public String getStudio() { return studio; }
    public int getJumlah() { return jumlah; }
    public int getTotal() { return total; }
    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setJumlah(int jumlahBaru) {
        this.jumlah = jumlahBaru;
    }

    public void setTotal(int total) {
        this.total = total;
    }



}

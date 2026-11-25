import java.time.LocalDate;

/**
 * Menghubungkan satu pasien dengan satu dokter dalam sebuah kunjungan.
 */
public class Kunjungan {
    private String kodeKunjungan;
    private Pasien pasien;
    private Dokter dokter;
    private LocalDate tanggal;
    private String keluhan;
    private double biaya;
    private String status;

    public Kunjungan() {
        this("K000", null, null, LocalDate.now(), "-", 0.0);
    }

    public Kunjungan(String kodeKunjungan, Pasien pasien, Dokter dokter,
                     LocalDate tanggal, String keluhan, double biaya) {
        this.kodeKunjungan = kodeKunjungan;
        this.pasien = pasien;
        this.dokter = dokter;
        this.tanggal = tanggal;
        this.keluhan = keluhan;
        setBiaya(biaya);
        this.status = "Terdaftar";
    }

    public double hitungTotalBiaya() {
        return biaya;
    }

    public void ubahStatus(String statusBaru) {
        if (statusBaru != null && !statusBaru.trim().isEmpty()) this.status = statusBaru;
    }

    public void tampilkanInfo() {
        String namaPasien = pasien == null ? "-" : pasien.getNama();
        String namaDokter = dokter == null ? "-" : dokter.getNama();
        System.out.printf("Kode: %s | Pasien: %s | Dokter: %s | Tanggal: %s | Keluhan: %s | Biaya: Rp%.0f | Status: %s%n",
                kodeKunjungan, namaPasien, namaDokter, tanggal, keluhan, biaya, status);
    }

    public String getKodeKunjungan() { return kodeKunjungan; }
    public Pasien getPasien() { return pasien; }
    public Dokter getDokter() { return dokter; }
    public LocalDate getTanggal() { return tanggal; }
    public String getKeluhan() { return keluhan; }
    public double getBiaya() { return biaya; }
    public String getStatus() { return status; }

    public void setKodeKunjungan(String kodeKunjungan) {
        if (kodeKunjungan != null && !kodeKunjungan.trim().isEmpty()) this.kodeKunjungan = kodeKunjungan;
    }
    public void setPasien(Pasien pasien) { this.pasien = pasien; }
    public void setDokter(Dokter dokter) { this.dokter = dokter; }
    public void setTanggal(LocalDate tanggal) { if (tanggal != null) this.tanggal = tanggal; }
    public void setKeluhan(String keluhan) { if (keluhan != null && !keluhan.trim().isEmpty()) this.keluhan = keluhan; }
    public void setBiaya(double biaya) {
        if (biaya >= 0) this.biaya = biaya;
        else throw new IllegalArgumentException("Biaya tidak boleh negatif.");
    }
}

import java.time.LocalDate;

/**
 * Menyimpan data dokter dan membatasi maksimal lima kunjungan per hari.
 */
public class Dokter {
    private String kodeDokter;
    private String nama;
    private String spesialisasi;
    private String nomorTelepon;
    private int jumlahKunjunganHariIni;
    private LocalDate tanggalHitung;

    public Dokter() {
        this("D000", "Belum diisi", "Umum", "-");
    }

    public Dokter(String kodeDokter, String nama, String spesialisasi, String nomorTelepon) {
        this.kodeDokter = kodeDokter;
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.nomorTelepon = nomorTelepon;
        this.jumlahKunjunganHariIni = 0;
        this.tanggalHitung = LocalDate.now();
    }

    private void perbaruiTanggal(LocalDate tanggal) {
        if (tanggalHitung == null || !tanggalHitung.equals(tanggal)) {
            tanggalHitung = tanggal;
            jumlahKunjunganHariIni = 0;
        }
    }

    public boolean bisaMenerimaKunjungan(LocalDate tanggal) {
        perbaruiTanggal(tanggal);
        return jumlahKunjunganHariIni < 5;
    }

    public void tambahKunjungan(LocalDate tanggal) {
        perbaruiTanggal(tanggal);
        if (!bisaMenerimaKunjungan(tanggal)) {
            throw new IllegalStateException("Dokter sudah mencapai 5 kunjungan pada hari ini.");
        }
        jumlahKunjunganHariIni++;
    }

    public void tampilkanInfo() {
        System.out.printf("Kode: %s | Nama: %s | Spesialisasi: %s | Telepon: %s | Kunjungan hari ini: %d/5%n",
                kodeDokter, nama, spesialisasi, nomorTelepon, jumlahKunjunganHariIni);
    }

    public String getKodeDokter() { return kodeDokter; }
    public String getNama() { return nama; }
    public String getSpesialisasi() { return spesialisasi; }
    public String getNomorTelepon() { return nomorTelepon; }
    public int getJumlahKunjunganHariIni() { return jumlahKunjunganHariIni; }

    public void setKodeDokter(String kodeDokter) {
        if (kodeDokter != null && !kodeDokter.trim().isEmpty()) this.kodeDokter = kodeDokter;
    }
    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) this.nama = nama;
    }
    public void setSpesialisasi(String spesialisasi) {
        if (spesialisasi != null && !spesialisasi.trim().isEmpty()) this.spesialisasi = spesialisasi;
    }
    public void setNomorTelepon(String nomorTelepon) { this.nomorTelepon = nomorTelepon; }
}

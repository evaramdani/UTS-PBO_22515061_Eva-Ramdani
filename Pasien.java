/**
 * Menyimpan data dan perilaku pasien klinik.
 */
public class Pasien {
    private String kodePasien;
    private String nama;
    private int umur;
    private String jenisKelamin;
    private String nomorTelepon;

    public Pasien() {
        this("P000", "Belum diisi", 0, "-", "-");
    }

    public Pasien(String kodePasien, String nama, int umur,
                  String jenisKelamin, String nomorTelepon) {
        this.kodePasien = kodePasien;
        this.nama = nama;
        setUmur(umur);
        this.jenisKelamin = jenisKelamin;
        this.nomorTelepon = nomorTelepon;
    }

    public boolean validasiUmur() {
        return umur >= 0 && umur <= 120;
    }

    public void ubahData(String nama, String jenisKelamin, String nomorTelepon) {
        if (nama != null && !nama.trim().isEmpty()) this.nama = nama;
        if (jenisKelamin != null && !jenisKelamin.trim().isEmpty()) this.jenisKelamin = jenisKelamin;
        if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) this.nomorTelepon = nomorTelepon;
    }

    public void tampilkanInfo() {
        System.out.printf("Kode: %s | Nama: %s | Umur: %d | JK: %s | Telepon: %s%n",
                kodePasien, nama, umur, jenisKelamin, nomorTelepon);
    }

    public String getKodePasien() { return kodePasien; }
    public String getNama() { return nama; }
    public int getUmur() { return umur; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getNomorTelepon() { return nomorTelepon; }

    public void setKodePasien(String kodePasien) {
        if (kodePasien != null && !kodePasien.trim().isEmpty()) this.kodePasien = kodePasien;
    }
    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) this.nama = nama;
    }
    public void setUmur(int umur) {
        if (umur >= 0 && umur <= 120) this.umur = umur;
        else throw new IllegalArgumentException("Umur pasien harus 0 sampai 120 tahun.");
    }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    public void setNomorTelepon(String nomorTelepon) { this.nomorTelepon = nomorTelepon; }
}

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Mengelola pasien, dokter, kunjungan, dan riwayat klinik.
 */
public class Klinik {
    private String namaKlinik;
    private String alamat;
    private ArrayList<Pasien> daftarPasien;
    private ArrayList<Dokter> daftarDokter;
    private ArrayList<Kunjungan> daftarKunjungan;
    private StackRiwayat riwayat;

    public Klinik() {
        this("Klinik Sehat", "Alamat belum diisi", 100);
    }

    public Klinik(String namaKlinik, String alamat, int kapasitasRiwayat) {
        this.namaKlinik = namaKlinik;
        this.alamat = alamat;
        this.daftarPasien = new ArrayList<>();
        this.daftarDokter = new ArrayList<>();
        this.daftarKunjungan = new ArrayList<>();
        this.riwayat = new StackRiwayat(kapasitasRiwayat);
    }

    public boolean tambahPasien(Pasien pasien) {
        if (pasien == null || cariPasien(pasien.getKodePasien()) != null) return false;
        daftarPasien.add(pasien);
        return true;
    }

    public boolean tambahDokter(Dokter dokter) {
        if (dokter == null || cariDokter(dokter.getKodeDokter()) != null) return false;
        daftarDokter.add(dokter);
        return true;
    }

    public Pasien cariPasien(String kunci) {
        for (Pasien p : daftarPasien) {
            if (p.getKodePasien().equalsIgnoreCase(kunci) ||
                p.getNama().equalsIgnoreCase(kunci)) return p;
        }
        return null;
    }

    public Dokter cariDokter(String kunci) {
        for (Dokter d : daftarDokter) {
            if (d.getKodeDokter().equalsIgnoreCase(kunci) ||
                d.getNama().equalsIgnoreCase(kunci)) return d;
        }
        return null;
    }

    public Kunjungan cariKunjungan(String kode) {
        for (Kunjungan k : daftarKunjungan) {
            if (k.getKodeKunjungan().equalsIgnoreCase(kode)) return k;
        }
        return null;
    }

    public boolean buatKunjungan(String kode, Pasien pasien, Dokter dokter,
                                 String keluhan, double biaya) {
        if (pasien == null || dokter == null || cariKunjungan(kode) != null) return false;
        LocalDate hariIni = LocalDate.now();
        if (!dokter.bisaMenerimaKunjungan(hariIni)) return false;
        if (riwayat.isFull()) return false;

        Kunjungan kunjungan = new Kunjungan(kode, pasien, dokter, hariIni, keluhan, biaya);
        dokter.tambahKunjungan(hariIni);
        daftarKunjungan.add(kunjungan);
        riwayat.push(kode + " - " + pasien.getNama() + " dilayani " + dokter.getNama());
        return true;
    }

    public void tampilkanPasien() {
        if (daftarPasien.isEmpty()) { System.out.println("Belum ada data pasien."); return; }
        for (Pasien p : daftarPasien) p.tampilkanInfo();
    }

    public void tampilkanDokter() {
        if (daftarDokter.isEmpty()) { System.out.println("Belum ada data dokter."); return; }
        for (Dokter d : daftarDokter) d.tampilkanInfo();
    }

    public void tampilkanKunjungan() {
        if (daftarKunjungan.isEmpty()) { System.out.println("Belum ada data kunjungan."); return; }
        for (Kunjungan k : daftarKunjungan) k.tampilkanInfo();
    }

    public void tampilkanLaporan() {
        double totalBiaya = 0;
        for (Kunjungan k : daftarKunjungan) totalBiaya += k.hitungTotalBiaya();
        System.out.println("\n===== LAPORAN KLINIK =====");
        System.out.println("Nama klinik       : " + namaKlinik);
        System.out.println("Alamat            : " + alamat);
        System.out.println("Jumlah pasien     : " + daftarPasien.size());
        System.out.println("Jumlah dokter     : " + daftarDokter.size());
        System.out.println("Jumlah kunjungan  : " + daftarKunjungan.size());
        System.out.printf("Total biaya       : Rp%.0f%n", totalBiaya);
        System.out.println("Riwayat tersimpan : " + riwayat.size());
    }

    public StackRiwayat getRiwayat() { return riwayat; }
    public int getJumlahPasien() { return daftarPasien.size(); }
    public int getJumlahDokter() { return daftarDokter.size(); }
    public int getJumlahKunjungan() { return daftarKunjungan.size(); }
}

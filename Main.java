import java.util.Scanner;

/**
 * Menu utama Sistem Manajemen Klinik Rawat Jalan.
 */
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Klinik klinik = new Klinik("Klinik Sehat", "Kendari", 100);

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1: tambahPasien(); break;
                case 2: tambahDokter(); break;
                case 3: klinik.tampilkanPasien(); break;
                case 4: klinik.tampilkanDokter(); break;
                case 5: buatKunjungan(); break;
                case 6: cariData(); break;
                case 7: klinik.tampilkanKunjungan(); break;
                case 8: menuStack(); break;
                case 9: klinik.tampilkanLaporan(); break;
                case 0: System.out.println("Terima kasih. Program selesai."); break;
                default: System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    private static void tampilkanMenu() {
        System.out.println("\n====================================");
        System.out.println("   SISTEM MANAJEMEN KLINIK RAWAT JALAN");
        System.out.println("   Eva Ramdani | 22515061");
        System.out.println("====================================");
        System.out.println("1. Tambah pasien");
        System.out.println("2. Tambah dokter");
        System.out.println("3. Tampilkan pasien");
        System.out.println("4. Tampilkan dokter");
        System.out.println("5. Buat kunjungan");
        System.out.println("6. Cari pasien/dokter");
        System.out.println("7. Tampilkan semua kunjungan");
        System.out.println("8. Operasi Stack riwayat");
        System.out.println("9. Laporan klinik");
        System.out.println("0. Keluar");
    }

    private static void tambahPasien() {
        System.out.println("\n--- Tambah Pasien ---");
        String kode = bacaTeks("Kode pasien: ");
        if (klinik.cariPasien(kode) != null) {
            System.out.println("Kode pasien sudah digunakan."); return;
        }
        String nama = bacaTeks("Nama: ");
        int umur = bacaInt("Umur (0-120): ");
        if (umur < 0 || umur > 120) {
            System.out.println("Umur pasien harus 0 sampai 120 tahun."); return;
        }
        String jk = bacaTeks("Jenis kelamin: ");
        String telepon = bacaTeks("Nomor telepon: ");
        boolean berhasil = klinik.tambahPasien(new Pasien(kode, nama, umur, jk, telepon));
        System.out.println(berhasil ? "Pasien berhasil ditambahkan." : "Pasien gagal ditambahkan.");
    }

    private static void tambahDokter() {
        System.out.println("\n--- Tambah Dokter ---");
        String kode = bacaTeks("Kode dokter: ");
        if (klinik.cariDokter(kode) != null) {
            System.out.println("Kode dokter sudah digunakan."); return;
        }
        String nama = bacaTeks("Nama: ");
        String spesialisasi = bacaTeks("Spesialisasi: ");
        String telepon = bacaTeks("Nomor telepon: ");
        boolean berhasil = klinik.tambahDokter(new Dokter(kode, nama, spesialisasi, telepon));
        System.out.println(berhasil ? "Dokter berhasil ditambahkan." : "Dokter gagal ditambahkan.");
    }

    private static void buatKunjungan() {
        System.out.println("\n--- Buat Kunjungan ---");
        String kode = bacaTeks("Kode kunjungan: ");
        String kodePasien = bacaTeks("Kode/nama pasien: ");
        Pasien pasien = klinik.cariPasien(kodePasien);
        String kodeDokter = bacaTeks("Kode/nama dokter: ");
        Dokter dokter = klinik.cariDokter(kodeDokter);
        if (pasien == null || dokter == null) {
            System.out.println("Pasien atau dokter tidak ditemukan."); return;
        }
        String keluhan = bacaTeks("Keluhan: ");
        double biaya = bacaDouble("Biaya kunjungan: ");
        if (biaya < 0) { System.out.println("Biaya tidak boleh negatif."); return; }
        boolean berhasil = klinik.buatKunjungan(kode, pasien, dokter, keluhan, biaya);
        if (berhasil) System.out.println("Kunjungan berhasil dibuat.");
        else System.out.println("Kunjungan gagal. Periksa kode, batas 5 kunjungan dokter/hari, atau kapasitas Stack.");
    }

    private static void cariData() {
        String kunci = bacaTeks("Masukkan kode/nama pasien atau dokter: ");
        Pasien pasien = klinik.cariPasien(kunci);
        Dokter dokter = klinik.cariDokter(kunci);
        if (pasien != null) pasien.tampilkanInfo();
        else if (dokter != null) dokter.tampilkanInfo();
        else System.out.println("Data tidak ditemukan.");
    }

    private static void menuStack() {
        int pilihan;
        do {
            System.out.println("\n--- MENU STACK RIWAYAT ---");
            System.out.println("1. Push (lihat riwayat terbaru yang tersimpan)");
            System.out.println("2. Pop (hapus riwayat terakhir)");
            System.out.println("3. Peek (lihat riwayat teratas)");
            System.out.println("4. Tampilkan isi Stack");
            System.out.println("5. Status Stack");
            System.out.println("0. Kembali");
            pilihan = bacaInt("Pilih: ");
            StackRiwayat stack = klinik.getRiwayat();
            switch (pilihan) {
                case 1:
                    System.out.println("Riwayat ditambahkan otomatis saat kunjungan berhasil dibuat.");
                    break;
                case 2:
                    String hasil = stack.pop();
                    System.out.println(hasil == null ? "Stack kosong." : "Riwayat dihapus: " + hasil);
                    break;
                case 3:
                    System.out.println(stack.peek() == null ? "Stack kosong." : "Teratas: " + stack.peek());
                    break;
                case 4: stack.tampilkan(); break;
                case 5:
                    System.out.println("Jumlah: " + stack.size() + " | Kosong: " + stack.isEmpty()
                            + " | Penuh: " + stack.isFull());
                    break;
                case 0: break;
                default: System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    private static String bacaTeks(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    private static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try { return Integer.parseInt(input.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Masukkan angka bulat yang benar."); }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            try { return Double.parseDouble(input.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Masukkan angka yang benar."); }
        }
    }
}

/**
 * ADT Stack berbasis array untuk menyimpan riwayat kunjungan (LIFO).
 */
public class StackRiwayat {
    private String[] data;
    private int top;
    private int kapasitas;

    public StackRiwayat() {
        this(100);
    }

    public StackRiwayat(int kapasitas) {
        if (kapasitas <= 0) throw new IllegalArgumentException("Kapasitas harus lebih dari 0.");
        this.kapasitas = kapasitas;
        this.data = new String[kapasitas];
        this.top = -1;
    }

    public boolean push(String riwayat) {
        if (isFull()) return false;
        if (riwayat == null || riwayat.trim().isEmpty()) return false;
        data[++top] = riwayat;
        return true;
    }

    public String pop() {
        if (isEmpty()) return null;
        String hasil = data[top];
        data[top--] = null;
        return hasil;
    }

    public String peek() {
        return isEmpty() ? null : data[top];
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == kapasitas - 1; }
    public int size() { return top + 1; }

    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Riwayat Stack masih kosong.");
            return;
        }
        System.out.println("--- Isi Stack (teratas ke terbawah) ---");
        for (int i = top; i >= 0; i--) {
            System.out.println((i + 1) + ". " + data[i]);
        }
    }
}

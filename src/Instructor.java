public class Instructor {
    private String name;
    private String department; // Bölümü (Örn: Bilgisayar Müh.)

    public Instructor(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    // Hoca bilgisini ekrana yazdırmak için:
    @Override
    public String toString() {
        return  name + " [" + department + "]";
    }
}
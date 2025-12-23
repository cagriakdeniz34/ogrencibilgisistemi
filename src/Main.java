public class Main {
    public static void main(String[] args) {
        Course ders1 = new Course("LBLM203", "Nesne Tabanli Programlama", 4);

        Instructor hoca1 = new Instructor("Tugberk Kocatekin", "Bilgisayar Bilimleri");

        ders1.setInstructor(hoca1);

        System.out.println(ders1.toString());

        Course ders2 = new Course("LBLM201", "Veri Yapilari", 4);

        Instructor hoca2 = new Instructor("Sibel Birtane Akar", "Bilgisayar Bilimleri");

        ders2.setInstructor(hoca2);

        System.out.println(ders2.toString());
    }
}
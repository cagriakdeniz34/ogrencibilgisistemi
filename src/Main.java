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


        System.out.println("--------------------------------");

        Student ogr1 = new Student(240309013, "Cagri Akdeniz");
        System.out.println(ogr1.toString() + " Harc Bedeli: " + ogr1.calculateTuition() + " TL");

        GraduateStudent ogr2 = new GraduateStudent(240309017, "Ertugrul Veli Akin", "Siber Guvenlik");
        System.out.println("Yuksek Lisans: " + ogr2.toString() + " Harc Bedeli: " + ogr2.calculateTuition() + " TL");
        System.out.println("Tez Konusu: " + ogr2.getThesis());


        System.out.println("\n--- DERS KAYIT TESTI ---");

        ogr1.registerCourse(ders1);
        ogr1.registerCourse(ders2);
        ogr2.registerCourse(ders2);

        System.out.println("\n--- DERS LISTELEME TESTI ---");

        ogr1.listCourses();

        ogr2.listCourses();
    }
}
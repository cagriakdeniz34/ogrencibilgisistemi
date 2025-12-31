import java.util.ArrayList;
import java.util.List;

public class Student implements Registrable {
    private int id;
    private String name;
    private List<Course> courses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double calculateTuition() {
        return 4000.0;
    }

    @Override
    public void registerCourse(Course course) {
        if (this.courses.contains(course)) {
            System.out.println("HATA: " + this.name + " zaten " + course.getName() + " dersine kayıtlı!");
        } else {
            this.courses.add(course);
            System.out.println(this.name + " isimli öğrenci " + course.getName() + " dersine başarıyla kayıt oldu.");
        }
    }

    // --- YENİ EKLENEN METOD (DERS SİLME) ---
    public boolean dropCourse(String courseCode) {
        // Listedeki dersleri tek tek kontrol ediyoruz
        for (Course course : this.courses) {
            // Eğer aranan kod (büyük/küçük harf farketmeksizin) eşleşirse
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                this.courses.remove(course); // Dersi listeden sil
                return true; // İşlem başarılı
            }
        }
        return false; // Ders bulunamadı
    }
    // ---------------------------------------

    @Override
    public String getRegistrationInfo() {
        return "Ogrenci Kayit Bilgisi: " + name + " (ID: " + id + ")";
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    public void listCourses() {
        if (this.courses.isEmpty()) {
            System.out.println(this.getName() + " henüz herhangi bir derse kayit olmamis.");
        } else {
            System.out.println("-------------------------------------");
            System.out.println(this.getName() + " isimli ogrencinin aldigi dersler:");
            for (Course course : this.courses) {
                // Listeleme yaparken kodunu da gösterelim ki silerken kolaylık olsun
                System.out.println(" - [" + course.getCode() + "] " + course.getName());
            }
            System.out.println("-------------------------------------");
        }
    }

    public java.util.List<Course> getCourses() {
        return courses;
    }
}
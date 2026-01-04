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


    public void registerCourse(Course course) {

        int toplamKredi = 0;
        for (Course c : this.courses) {
            toplamKredi += c.getCredit();
        }

        if (toplamKredi + course.getCredit() > 30) {
            System.out.println("UYARI: Kredi limiti aşıldı! " + course.getCode() + " dersi eklenmedi.");
            return;
        }


        for (Course c : this.courses) {
            if (c.getCode().equalsIgnoreCase(course.getCode())) {
                System.out.println("UYARI: " + course.getCode() + " kodlu ders zaten listenizde var! Tekrar eklenmedi.");
                return;
            }
        }


        this.courses.add(course);
        System.out.println(course.getCode() + " dersi başarıyla eklendi.");
    }


    public boolean dropCourse(String courseCode) {

        for (Course course : this.courses) {

            if (course.getCode().equalsIgnoreCase(courseCode)) {
                this.courses.remove(course);
                return true;
            }
        }
        return false;
    }



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
                System.out.println(" - [" + course.getCode() + "] " + course.getName());
            }
            System.out.println("-------------------------------------");
        }
    }

    public java.util.List<Course> getCourses() {
        return courses;
    }
}
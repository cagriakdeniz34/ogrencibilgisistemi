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
        this.courses.add(course);
        System.out.println(this.getName() + " isimli öğrenci " + course.getName() + " dersine basariyla kayit oldu.");
    }

    @Override
    public String getRegistrationInfo() {
        return "Öğrenci Kayit Bilgisi: " + name + " (ID: " + id + ")";
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
                System.out.println(" - " + course.getName());
            }
            System.out.println("-------------------------------------");
        }
    }
}
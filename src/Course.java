public class Course {

    private String code;
    private String name;
    private int credit;
    private Instructor instructor;

    public Course(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        if (instructor != null) {
            return code + " - " + name + " (" + credit + " Kredi) - Veren: " + instructor.getName();
        }
        else {
            return code + " - " + name + " (" + credit + " Kredi) - Hoca atanmadı";
        }
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}

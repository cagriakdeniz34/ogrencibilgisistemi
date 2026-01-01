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


    public void setInstructor(Instructor instructor) {

        this.instructor = instructor;
    }


    public String getName() {

        return name;
    }


    public String getCode() {
        return code;
    }


    @Override
    public String toString() {

        String hocaBilgisi = (instructor != null) ? " - Hoca: " + instructor.getName() : " - Hoca: Atanmadı";

        return "[" + code + "] " + name + " (" + credit + " Kredi)" + hocaBilgisi;
    }
}
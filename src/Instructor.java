public class Instructor {
    private String name;
    private String department;

    public Instructor(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return  name + " [" + department + "]";
    }
}
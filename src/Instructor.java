/**
 * Bir öğretim görevlisini (Hoca) temsil eden sınıf.
 * <p>
 * Eğitmenin ID'si, adı ve bağlı olduğu departman bilgisini tutar.
 * </p>
 *
 * @author Cagri
 * @version 1.0
 */
public class Instructor {

    /** Eğitmenin kurum sicil numarası veya ID'si */
    private int id;

    /** Eğitmenin Adı ve Soyadı */
    private String name;

    /** Eğitmenin bağlı olduğu bölüm (Örn: "Computer Science") */
    private String department;

    /**
     * Yeni bir eğitmen oluşturur.
     *
     * @param id         Eğitmen ID'si.
     * @param name       Eğitmenin tam adı.
     * @param department Bölüm adı.
     */
    public Instructor(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " (" + department + ")";
    }
}
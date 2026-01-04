/**
 * Bir üniversite dersini temsil eden sınıf.
 * <p>
 * Her dersin benzersiz bir kodu, adı, kredisi ve dersi veren bir
 * eğitmeni ({@link Instructor}) bulunur.
 * </p>
 *
 * @author Cagri
 * @version 1.0
 */
public class Course {

    /** Dersin kodu (Örn: "CS101") */
    private String code;

    /** Dersin tam adı (Örn: "Introduction to Java") */
    private String name;

    /** Dersin kredi değeri. Toplam kredi hesaplamasında kullanılır. */
    private int credit;

    /** Dersi veren öğretim görevlisi. Başlangıçta null olabilir. */
    private Instructor instructor;

    /**
     * Yeni bir ders oluşturur.
     * Eğitmen bilgisi bu aşamada atanmaz, sonradan {@link #setInstructor(Instructor)} ile eklenir.
     *
     * @param code   Dersin kodu (Benzersiz olmalıdır).
     * @param name   Dersin adı.
     * @param credit Dersin kredisi (Örn: 3, 4, 6).
     */
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

    /**
     * Dersin kredisini döndürür.
     * @return Kredi değeri.
     */
    public int getCredit() {
        return credit;
    }

    /**
     * Dersi veren eğitmeni döndürür.
     * @return Eğitmen nesnesi veya atanmamışsa null.
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Derse bir eğitmen atar.
     * @param instructor Atanacak eğitmen nesnesi.
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Dersin string temsilini döndürür.
     * Format: KOD - İSİM (KREDİ) | Hoca Adı
     */
    @Override
    public String toString() {
        String instructorName = (instructor != null) ? instructor.getName() : "N/A";
        return code + " - " + name + " (" + credit + " Credit) | Instr: " + instructorName;
    }
}
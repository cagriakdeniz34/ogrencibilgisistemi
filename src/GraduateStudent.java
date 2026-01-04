/**
 * Yüksek lisans (Graduate) öğrencisini temsil eden sınıf.
 * <p>
 * Standart {@link Student} sınıfından türetilmiştir (Inheritance).
 * Normal öğrenci özelliklerine ek olarak bir tez konusu barındırır ve
 * harç hesaplama mantığı lisans öğrencilerinden farklıdır.
 * </p>
 *
 * @author Cagri
 * @version 1.0
 * @see Student
 */
public class GraduateStudent extends Student {

    /** Öğrencinin üzerinde çalıştığı tez konusu */
    private String thesisTopic;

    /**
     * Yeni bir yüksek lisans öğrencisi oluşturur.
     * <p>
     * Üst sınıf olan {@link Student} yapıcısını (constructor) çağırarak
     * temel bilgileri set eder.
     * </p>
     *
     * @param id          Öğrenci numarası.
     * @param name        Öğrencinin adı soyadı.
     * @param thesisTopic Öğrencinin tez konusu (Örn: "Yapay Zeka").
     */
    public GraduateStudent(int id, String name, String thesisTopic) {
        // super() anahtar kelimesi, Student sınıfının constructor'ını çalıştırır.
        super(id, name);
        this.thesisTopic = thesisTopic;
    }

    /**
     * Öğrencinin tez konusunu döndürür.
     *
     * @return Tez konusu.
     */
    public String getThesisTopic() {
        return thesisTopic;
    }

    /**
     * Öğrencinin tez konusunu günceller.
     *
     * @param thesisTopic Yeni tez konusu.
     */
    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    /**
     * Yüksek lisans öğrencileri için harç miktarını hesaplar.
     * <p>
     * Standart öğrenciler 4000 TL öderken, yüksek lisans öğrencileri
     * laboratuvar ve araştırma masrafları nedeniyle daha yüksek (6000 TL) öder.
     * </p>
     *
     * @return Yüksek lisans harç ücreti (6000.0 TL).
     */
    @Override
    public double calculateTuition() {
        return 6000.0;
    }

    /**
     * Öğrencinin bilgilerini metin olarak döndürür.
     * Üst sınıfın toString metoduna ek olarak tez konusunu da gösterir.
     *
     * @return Formatlı öğrenci bilgisi.
     */
    @Override
    public String toString() {
        // super.toString() -> "Ali Veli (101)" kısmını getirir.
        return super.toString() + " [Yüksek Lisans - Tez: " + thesisTopic + "]";
    }
}
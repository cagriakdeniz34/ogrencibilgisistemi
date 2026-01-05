import java.util.ArrayList;
import java.util.List;

/**
 * Bir öğrenciyi temsil eden ve ders kayıt işlemlerini yöneten sınıf.
 * <p>
 * Bu sınıf, öğrencinin ders ekleme, silme ve listeleme gibi
 * temel kayıt işlemlerini (Registration) yürütür.
 * </p>
 *
 * @author Cagri
 * @version 1.1
 */
public class Student implements Registrable {

    /** Öğrencinin okul numarası */
    private int id;

    /** Öğrencinin tam adı */
    private String name;

    /** Öğrencinin kayıt olduğu derslerin tutulduğu liste */
    private List<Course> courses;

    /** Maksimum alınabilecek kredi limiti */
    private static final int MAX_CREDIT_LIMIT = 30;

    /** Kabul edilen maksimum ID değeri (Veritabanı veya mantıksal sınır) */
    private static final int MAX_VALID_ID = 999999999;

    /**
     * Yeni bir öğrenci nesnesi oluşturur.
     * Ders listesi otomatik olarak boş bir liste olarak başlatılır.
     * <p>
     * <b>Validasyon:</b> ID değeri negatif olamaz ve belirlenen üst sınırı aşamaz.
     * </p>
     *
     * @param id   Öğrenci numarası (Sadece sayı).
     * @param name Öğrencinin adı ve soyadı.
     * @throws IllegalArgumentException Eğer ID negatifse veya sınırları aşıyorsa fırlatılır.
     */
    public Student(int id, String name) {

        if (id < 0) {
            throw new IllegalArgumentException("Hata: Öğrenci ID negatif olamaz! ");
        }
        if (id > MAX_VALID_ID) {
            throw new IllegalArgumentException("Hata: Öğrenci ID belirlenen sınırı (" + MAX_VALID_ID + ") aşıyor!" );
        }


        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    /**
     * Öğrencinin adını döndürür.
     *
     * @return Öğrenci tam adı.
     */
    public String getName() {
        return name;
    }

    /**
     * Öğrencinin ödemesi gereken harç miktarını hesaplar.
     * Şu an için tüm öğrencilerde sabit bir değer döner.
     *
     * @return Harç miktarı (4000.0 TL).
     */
    public double calculateTuition() {
        return 4000.0;
    }

    /**
     * Öğrencinin listesine yeni bir ders ekler.
     * <p>
     * Bu metot ekleme yapmadan önce iki kritik kontrol gerçekleştirir:
     * 1. <b>Kredi Limiti Kontrolü:</b> Toplam kredi {@value #MAX_CREDIT_LIMIT}'u aşarsa ekleme yapılmaz.
     * 2. <b>Çift Kayıt Kontrolü:</b> Aynı ders (koduna göre) daha önce eklenmişse tekrar eklenmez.
     * </p>
     *
     * @param course Eklenecek olan {@link Course} nesnesi.
     */
    public void registerCourse(Course course) {
        int toplamKredi = 0;
        for (Course c : this.courses) {
            toplamKredi += c.getCredit(); // Not: Course sınıfında getCredits() veya getCredit() hangisi varsa onu kullan.
        }

        if (toplamKredi + course.getCredit() > MAX_CREDIT_LIMIT) {
            System.out.println("UYARI: Kredi limiti (" + MAX_CREDIT_LIMIT + ") aşıldı! " + course.getCode() + " dersi eklenmedi.");
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

    /**
     * Belirtilen ders koduna sahip dersi listeden siler.
     *
     * @param courseCode Silinecek dersin kodu (Büyük/küçük harf duyarsızdır. Örn: "cs101").
     * @return Silme işlemi başarılıysa {@code true}, ders bulunamazsa {@code false} döner.
     */
    public boolean dropCourse(String courseCode) {
        boolean removed = this.courses.removeIf(c -> c.getCode().equalsIgnoreCase(courseCode));

        if (removed) {
            System.out.println(courseCode + " dersi listeden silindi.");
        }
        return removed;
    }

    /**
     * Arayüzden (Interface) gelen kayıt bilgi metodu.
     *
     * @return Öğrenci adı ve ID içeren bilgi metni.
     */
    @Override
    public String getRegistrationInfo() {
        return "Ogrenci Kayit Bilgisi: " + name + " (ID: " + id + ")";
    }

    /**
     * Nesnenin String temsili.
     *
     * @return "Ad (ID)" formatında string.
     */
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    /**
     * Öğrencinin aldığı tüm dersleri ekrana düzenli bir formatta yazdırır.
     * Liste boşsa kullanıcıya bilgi verir.
     */
    public void listCourses() {
        if (this.courses.isEmpty()) {
            System.out.println(this.getName() + " henüz herhangi bir derse kayit olmamis.");
        } else {
            System.out.println("-------------------------------------");
            System.out.println(this.getName() + " isimli ogrencinin aldigi dersler:");
            for (Course course : this.courses) {
                System.out.println(" - " + course);
            }
            System.out.println("-------------------------------------");
        }
    }

    /**
     * Öğrencinin aldığı dersler listesini döndürür.
     * Test sınıflarında doğrulama (assertion) yapmak için kullanılır.
     *
     * @return Ders listesi.
     */
    public List<Course> getCourses() {
        return courses;
    }
}
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
 * @version 1.0
 */
public class Student implements Registrable {

    /** Öğrencinin okul numarası */
    private int id;

    /** Öğrencinin tam adı */
    private String name;

    /** Öğrencinin kayıt olduğu derslerin tutulduğu liste */
    private List<Course> courses;

    /**
     * Yeni bir öğrenci nesnesi oluşturur.
     * Ders listesi otomatik olarak boş bir liste olarak başlatılır.
     *
     * @param id   Öğrenci numarası (Sadece sayı).
     * @param name Öğrencinin adı ve soyadı.
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

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
     * 1. <b>Kredi Limiti Kontrolü:</b> Toplam kredi 20'yi aşarsa ekleme yapılmaz.
     * 2. <b>Çift Kayıt Kontrolü:</b> Aynı ders (koduna göre) daha önce eklenmişse tekrar eklenmez.
     * </p>
     *
     * @param course Eklenecek olan {@link Course} nesnesi.
     */
    public void registerCourse(Course course) {
        // 1. KREDİ LİMİTİ KONTROLÜ
        int toplamKredi = 0;
        for (Course c : this.courses) {
            toplamKredi += c.getCredit();
        }

        if (toplamKredi + course.getCredit() > 30) {
            System.out.println("UYARI: Kredi limiti aşıldı! " + course.getCode() + " dersi eklenmedi.");
            return;
        }

        // 2. ÇİFT DERS KONTROLÜ
        for (Course c : this.courses) {
            if (c.getCode().equalsIgnoreCase(course.getCode())) {
                System.out.println("UYARI: " + course.getCode() + " kodlu ders zaten listenizde var! Tekrar eklenmedi.");
                return;
            }
        }

        // 3. BAŞARILI EKLEME
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
        // removeIf metodu, koşulu sağlayan elemanı siler ve true döner
        boolean removed = this.courses.removeIf(c -> c.getCode().equalsIgnoreCase(courseCode));

        if (removed) {
            System.out.println(courseCode + " dersi listeden silindi.");
        }
        return removed;
    }

    @Override
    public String getRegistrationInfo() {
        return "Ogrenci Kayit Bilgisi: " + name + " (ID: " + id + ")";
    }

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
                // Dersin toString metodunu kullanır
                System.out.println(" - " + course);
            }
            System.out.println("-------------------------------------");
        }
    }

    public List<Course> getCourses() {
        return courses;
    }
}
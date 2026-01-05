import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Student sınıfı için birim testleri (Unit Tests).
 * Bu sınıf; ders ekleme, kredi kontrolü ve ID doğrulama senaryolarını test eder.
 */
class StudentTest {

    /**
     * Aynı dersin tekrar eklenemeyeceğini test eder.
     * <p>
     * Öğrenci aynı dersi (Course nesnesini) ikinci kez eklemeye çalıştığında,
     * listenin boyutunun artmaması ve dersin tekrar eklenmemesi gerekir.
     * </p>
     */
    @Test
    void testAyniDersTekrarEklenemez() {
        Student ogrenci = new Student(1, "Arda Abaci");
        Course ders = new Course("CS101", "Java", 6);

        ogrenci.registerCourse(ders);
        ogrenci.registerCourse(ders);

        assertEquals(1, ogrenci.getCourses().size(), "HATA: Aynı ders iki kere eklendi, mantık çalışmıyor!");
    }

    /**
     * Öğrenci ders kredi limit kontrolünü test eder.
     * <p>
     * Senaryo: Öğrencinin maksimum alabileceği kredi limiti 30 olarak belirlenmiştir.
     * Mevcut derslerin toplamı 26 iken, 5 kredilik yeni bir ders eklenmeye çalışılır.
     * Toplam 31 olacağı için (31 > 30) sistemin buna izin vermemesi gerekir.
     * </p>
     */
    @Test
    void testOgrenciKrediLimitKontrolu() {

        Student ogrenci = new Student(2, "Test Ogrencisi");


        ogrenci.registerCourse(new Course("CS101", "Java", 6));
        ogrenci.registerCourse(new Course("CS102", "Python", 6));
        ogrenci.registerCourse(new Course("MATH101", "Calculus", 6));
        ogrenci.registerCourse(new Course("PHYS101", "Physics", 8));

        Course agirDers = new Course("ART101", "Art History", 5);
        ogrenci.registerCourse(agirDers);


        int expectedSize = 4;
        assertEquals(expectedSize, ogrenci.getCourses().size(), "HATA: Kredi limiti aşılmasına rağmen ders eklendi!");


    }

    /**
     * Geçersiz (negatif) Öğrenci ID girişini test eder.
     * <p>
     * Öğrenci oluşturulurken negatif ID girilirse sistemin hata fırlatması beklenir.
     * </p>
     * @throws IllegalArgumentException Negatif ID girildiğinde
     */
    @Test
    void testGecersizNegatifId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student(-1, "Hatalı ID");
        }, "Negatif ID girildiğinde IllegalArgumentException fırlatılmalıdır.");
    }

    /**
     * Çok büyük (Sınır dışı) ID girişini test eder.
     * <p>
     * Veritabanı veya sistem sınırlarını aşan ID'leri test eder.
     * </p>
     */
    @Test
    void testCokBuyukId() {
        long cokBuyukId = 9999999999L;

        assertThrows(IllegalArgumentException.class, () -> {
            new Student((int) cokBuyukId, "Sınır Dışı ID");
        }, "Sınırları aşan ID girildiğinde hata fırlatılmalıdır.");
    }
}
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
 * Öğrenci Ders Kayıt Sistemi'nin ana çalıştırılabilir sınıfıdır (Entry Point).
 * <p>
 * Bu sınıf, kullanıcı ile etkileşime geçen Konsol Arayüzünü (CLI) yönetir.
 * Aşağıdaki temel işlevleri yerine getirir:
 * <ul>
 * <li>Kullanıcıdan öğrenci bilgisi alma (Giriş Ekranı).</li>
 * <li>Ana menü seçeneklerini listeleme.</li>
 * <li>Ders ekleme, silme ve listeleme komutlarını ilgili sınıflara yönlendirme.</li>
 * <li>CSV dosyalarından veri okuma ve yazma işlemleri.</li>
 * </ul>
 * </p>
 *
 * @author Cagri
 * @version 1.1
 */
public class Main {

    /**
     * Uygulamanın ana metodu (Main Method).
     * Java Sanal Makinesi (JVM) programı çalıştırmaya buradan başlar.
     *
     * @param args Komut satırı argümanları (Bu projede kullanılmamaktadır).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("--- ÖĞRENCİ KAYIT SİSTEMİ BAŞLATILIYOR ---");
        System.out.println("==========================================\n");

        String adSoyad;
        int id = 0;

        // 1. İSİM ALMA DÖNGÜSÜ
        while (true) {
            System.out.print("Öğrencinin Adı Soyadı: ");
            adSoyad = scanner.nextLine();

            // Regex: İçinde sayı var mı kontrolü
            if (adSoyad.matches(".*[0-9].*")) {
                System.out.println("HATA: İsim sayı içeremez! Tekrar deneyin.");
            } else if (adSoyad.trim().isEmpty()) {
                System.out.println("HATA: İsim boş bırakılamaz!");
            } else {
                break;
            }
        }

        // 2. ID ALMA DÖNGÜSÜ
        while (true) {
            try {
                System.out.print("Öğrenci ID: ");
                id = scanner.nextInt();
                scanner.nextLine(); // Buffer temizleme
                break;
            } catch (InputMismatchException e) {
                System.out.println("HATA: Lütfen ID kısmına sadece sayı giriniz!");
                scanner.nextLine();
            }
        }

        // Öğrenci nesnesini oluştur
        Student ogrenci = new Student(id, adSoyad);
        System.out.println("\nHoş geldin " + ogrenci.getName() + ". Giriş yapıldı.");

        boolean devamEtsinMi = true;

        // --- ANA MENÜ DÖNGÜSÜ ---
        while (devamEtsinMi) {
            System.out.println("\n-----------------------");
            System.out.println("       ANA MENÜ");
            System.out.println("-----------------------");
            System.out.println("1 - Yeni Ders Ekle (Elle)");
            System.out.println("2 - Aldığım Dersleri Listele");
            System.out.println("3 - Ders Sil");
            System.out.println("4 - Dosyadan Dersleri Yükle");
            System.out.println("5 - Listeyi Dosyaya Kaydet");
            System.out.println("0 - Sistemden Çıkış Yap");
            System.out.print("Seçiminiz: ");

            int secim = -1;

            try {
                secim = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Lütfen menüdeki sayılardan birini girin.");
                scanner.nextLine();
                continue;
            }

            if (secim == 1) {
                // Manuel Ders Ekleme
                System.out.print("Ders Kodu: ");
                String kod = scanner.nextLine();

                System.out.print("Ders Adı: ");
                String dersAdi = scanner.nextLine();

                int kredi = 0;
                while(true) {
                    try {
                        System.out.print("Kredi: ");
                        kredi = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("HATA: Kredi sadece sayı olmalıdır!");
                        scanner.nextLine();
                    }
                }

                Course yeniDers = new Course(kod, dersAdi, kredi);
                ogrenci.registerCourse(yeniDers);

            } else if (secim == 2) {
                // Listeleme
                ogrenci.listCourses();

            } else if (secim == 3) {
                // Silme
                System.out.print("Silinecek Dersin Kodu: ");
                String silinecekKod = scanner.nextLine();
                boolean sonuc = ogrenci.dropCourse(silinecekKod);
                if (!sonuc) {
                    System.out.println("HATA: Listenizde " + silinecekKod + " kodlu bir ders bulunamadı.");
                }

            } else if (secim == 4) {
                // Dosyadan Okuma
                String dosyaYolu = "courses.csv";
                String satir = "";

                // UTF-8 karakter desteği ile okuma
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(dosyaYolu), StandardCharsets.UTF_8))) {

                    int eklenenSayisi = 0;
                    while ((satir = br.readLine()) != null) {
                        String[] veri = satir.split(",");
                        if (veri.length == 4) {
                            String kod = veri[0].trim();
                            String ad = veri[1].trim();
                            int kredi = Integer.parseInt(veri[2].trim());
                            String hocaAdi = veri[3].trim();

                            Course yeniDers = new Course(kod, ad, kredi);
                            Instructor hoca = new Instructor(999, hocaAdi, "Genel");
                            yeniDers.setInstructor(hoca);

                            ogrenci.registerCourse(yeniDers);
                            eklenenSayisi++;
                        }
                    }
                    System.out.println("Dosyadan " + eklenenSayisi + " ders yüklendi.");

                } catch (IOException e) {
                    System.out.println("Dosya okuma hatası: " + dosyaYolu + " bulunamadı.");
                } catch (NumberFormatException e) {
                    System.out.println("Veri hatası: Kredi değeri sayı olmalı.");
                }

            } else if (secim == 5) {
                // Dosyaya Yazma
                String dosyaYolu = "courses.csv";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu))) {
                    for (Course ders : ogrenci.getCourses()) {
                        String hocaAdi = (ders.getInstructor() != null) ? ders.getInstructor().getName() : "Atanmadi";
                        String satir = ders.getCode() + "," + ders.getName() + "," + ders.getCredit() + "," + hocaAdi;
                        writer.write(satir);
                        writer.newLine();
                    }
                    System.out.println("Mevcut liste dosyaya kaydedildi.");
                } catch (IOException e) {
                    System.out.println("Dosya yazma hatası: " + e.getMessage());
                }

            } else if (secim == 0) {
                System.out.println("Sistem kapatılıyor. İyi çalışmalar!");
                devamEtsinMi = false;
            } else {
                System.out.println("Geçersiz seçim! Tekrar deneyin.");
            }
        }
        scanner.close();
    }
}
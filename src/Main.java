import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("--- ÖĞRENCİ KAYIT SİSTEMİ BAŞLATILIYOR ---");
        System.out.println("==========================================\n");

        String adSoyad;
        int id = 0;


        while (true) {
            try {
                System.out.print("Öğrencinin Adı Soyadı: ");
                adSoyad = scanner.nextLine();

                System.out.print("Öğrenci ID: ");
                id = scanner.nextInt();
                scanner.nextLine();

                break;
            } catch (InputMismatchException e) {
                System.out.println("HATA: Lütfen ID kısmına sadece sayı giriniz!");
                scanner.nextLine();
            }
        }

        Student ogrenci = new Student(id, adSoyad);
        System.out.println("\nHoş geldin " + ogrenci.getName() + ". Giriş yapıldı.");

        boolean devamEtsinMi = true;


        while (devamEtsinMi) {
            System.out.println("\n-----------------------");
            System.out.println("       ANA MENÜ");
            System.out.println("-----------------------");
            System.out.println("1 - Yeni Ders Ekle (Elle)");
            System.out.println("2 - Aldığım Dersleri Listele");
            System.out.println("3 - Ders Sil");
            System.out.println("4 - Dosyadan Dersleri Yükle");
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

                ogrenci.listCourses();

            } else if (secim == 3) {

                System.out.print("Silinecek Dersin Kodu: ");
                String silinecekKod = scanner.nextLine();

                boolean sonuc = ogrenci.dropCourse(silinecekKod);

                if (sonuc) {
                    System.out.println(silinecekKod + " kodlu ders başarıyla silindi.");
                } else {
                    System.out.println("HATA: Listenizde " + silinecekKod + " kodlu bir ders bulunamadı.");
                }

            } else if (secim == 4) {

                String dosyaYolu = "courses.csv";
                String satir = "";

                try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {

                    int eklenenSayisi = 0;
                    while ((satir = br.readLine()) != null) {

                        String[] veri = satir.split(",");


                        if (veri.length == 4) {
                            String kod = veri[0].trim();
                            String ad = veri[1].trim();
                            int kredi = Integer.parseInt(veri[2].trim());
                            String hocaAdi = veri[3].trim();


                            Course yeniDers = new Course(kod, ad, kredi);


                            Instructor hoca = new Instructor(999, hocaAdi, "Computer Science");


                            yeniDers.setInstructor(hoca);


                            ogrenci.registerCourse(yeniDers);
                            eklenenSayisi++;
                        }
                    }
                    System.out.println("Toplam " + eklenenSayisi + " ders ve hocası başarıyla yüklendi!");

                } catch (IOException e) {
                    System.out.println("Dosya okuma hatası: 'courses.csv' bulunamadı.");
                } catch (NumberFormatException e) {
                    System.out.println("Veri hatası: Kredi sayı olmalı.");
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
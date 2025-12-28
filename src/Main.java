import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("--- ÖĞRENCİ KAYIT SİSTEMİ BAŞLATILIYOR ---");
        System.out.println("==========================================\n");


        System.out.print("Öğrencinin Adı Soyadı: ");
        String adSoyad = scanner.nextLine();

        System.out.print("Öğrenci ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student ogrenci = new Student(id, adSoyad);
        System.out.println("\nHoş geldin " + ogrenci.getName() + ". İşlem seçiniz:");


        boolean devamEtsinMi = true;

        while (devamEtsinMi) {
            System.out.println("\n--- MENÜ ---");
            System.out.println("1 - Yeni Ders Ekle");
            System.out.println("2 - Dersleri Listele");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            if (secim == 1) {

                System.out.print("Ders Kodu (Örn: CS101): ");
                String kod = scanner.nextLine();

                System.out.print("Ders Adı: ");
                String dersAdi = scanner.nextLine();

                System.out.print("Kredi: ");
                int kredi = scanner.nextInt();
                scanner.nextLine();

                Course yeniDers = new Course(kod, dersAdi, kredi);
                ogrenci.registerCourse(yeniDers);

            } else if (secim == 2) {

                ogrenci.listCourses();

            } else if (secim == 0) {

                System.out.println("Sistemden çıkılıyor... İyi günler!");
                devamEtsinMi = false;

            } else {
                System.out.println("Hatalı seçim! Lütfen tekrar deneyin.");
            }
        }

        scanner.close();
    }
}
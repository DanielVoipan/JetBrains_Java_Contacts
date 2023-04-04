import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        while (scanner.hasNextInt()) {
            int nr = scanner.nextInt();
            System.out.println(LocalDate.ofYearDay(year, nr));
        }
    }
}
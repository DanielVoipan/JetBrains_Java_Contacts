import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int monthDay = scanner.nextInt();
        LocalDate date = LocalDate.ofYearDay(year, monthDay);
        if (date.getDayOfMonth() > date.plusDays(1).getDayOfMonth()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
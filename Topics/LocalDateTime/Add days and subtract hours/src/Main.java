import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        LocalDateTime dateTime = LocalDateTime.parse(line);
        int days = scanner.nextInt();
        int hours = scanner.nextInt();
        System.out.println(dateTime.plusDays(days).minusHours(hours));
    }
}
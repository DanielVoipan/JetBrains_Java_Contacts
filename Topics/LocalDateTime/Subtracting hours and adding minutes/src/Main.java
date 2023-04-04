import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(line1);
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        System.out.println(dateTime.minusHours(hours).plusMinutes(minutes));
    }
}
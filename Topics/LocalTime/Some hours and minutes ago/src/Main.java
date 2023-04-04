import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        LocalTime time = LocalTime.parse(line);
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        System.out.println(time.minusHours(hours).minusMinutes(minutes));
    }
}
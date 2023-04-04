import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalTime time1 = LocalTime.parse(scanner.next());
        LocalTime time2 = LocalTime.parse(scanner.next());
        if (time2.toSecondOfDay() > time1.toSecondOfDay()) {
            System.out.println(time2.toSecondOfDay() - time1.toSecondOfDay());
        } else {
            System.out.println(time1.toSecondOfDay() - time2.toSecondOfDay());
        }
    }
}
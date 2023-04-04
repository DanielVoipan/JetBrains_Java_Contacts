import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        String regex_first_last = "(^(" + part + "))|(" + part + ")(\\s|$)";
        String regex_middle = "(\\w+" + part + "\\w+)";
        Pattern pattern = Pattern.compile(regex_middle, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
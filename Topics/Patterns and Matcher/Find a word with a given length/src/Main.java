import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        String regex = "[A-Za-z]{" + size + "}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (size == 1) {
            System.out.println("NO");
            return;
        }
        if (matcher.find()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
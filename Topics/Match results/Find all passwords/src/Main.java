import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String regex = "(?i)password(\\s+|:(\\s+)?)[0-9A-Za-z]+(\\.)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        boolean found = false;
        while (matcher.find()) {
            System.out.println(filter(matcher.group()));
            found = true;
        }
        if (!found) {
            System.out.println("No passwords found.");
        }
    }

    static String filter(String str) {
        String[] m = str.split("(\\s|:)");
        String pass = m[m.length - 1];
        return pass.replaceAll("\\.", "");
    }
}
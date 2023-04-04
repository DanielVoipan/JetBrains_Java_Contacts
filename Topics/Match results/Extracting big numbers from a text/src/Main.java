import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stringWithNumbers = scanner.nextLine();
        String text = "(^|,|\\s|\\.)[0-9]{10,}(\\,|\\.|$|\\s)";
        Pattern pattern = Pattern.compile(text);
        Matcher matcher = pattern.matcher(stringWithNumbers);
        while (matcher.find()) {
            System.out.println(filter(matcher.group()) + ":" + filter(matcher.group()).length());
        }
    }
    
    static String filter(String str) {
        return str.replaceAll("\\.|\\,|\\s", "");
    }
}

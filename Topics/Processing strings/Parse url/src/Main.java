import java.util.Objects;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] part = line.split("\\?");
        String password = null;
        String value;
        for (String s : part[1].split("&")) {
            String[] values = s.split("=");
            if (values.length == 2) {
                value = values[1];
                if (values[0].equals("pass")) {
                    password = value;
                }
            } else {
                value = "not found";
            }
            System.out.println(values[0] + " : " + value);
        }
        if (!Objects.equals(password, null)) {
            System.out.println("password : " + password);
        }
    }
}
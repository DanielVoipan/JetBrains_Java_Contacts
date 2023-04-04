import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int level = 0;
        if (line.length() >= 12) {
            level++;
        }
        if (line.matches(".*[0-9].*")) {
            level++;
        }
        if (line.matches(".*[a-z].*")) {
            level++;
        }
        if (line.matches(".*[A-Z].*")) {
            level++;
        }
        if (level == 4) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str = scanner.next().toCharArray();
        int counter = 0;
        int end = str.length - 1;
        for (char c : str) {
            for (int j = end; j >= 0; j--) {
                if (c == str[j]) {
                    counter++;
                    end--;
                    break;
                } else {
                    end--;
                }
            }
        }
        if (counter == str.length) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

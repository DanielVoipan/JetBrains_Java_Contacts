import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String word = scanner.next();
        int counter = 0;
        int sFound = 0;
        int pos = 0;
        char[] array = line.toCharArray();
        char[] wArray = word.toCharArray();
        boolean found = false;
        List<Integer> lst = new ArrayList<>();
        for (char s : array) {
            for (char w : wArray) {
                if (s == w && !lst.contains(pos)) {
                    found = true;
                    lst.add(pos);
                    break;
                } else {
                    found = false;
                }
            }
            if (found) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == word.length()) {
                sFound++;
            }
            pos++;
        }
        System.out.println(sFound);
    }
}

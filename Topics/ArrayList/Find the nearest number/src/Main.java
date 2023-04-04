import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String elements = scanner.nextLine();
        int num = scanner.nextInt();
        getNumbers(elements, num);
    }

    static void getNumbers(String line, int num) {
        int[] lst = Arrays.stream(line.split(" ")).
                sorted(Comparator.reverseOrder()).
                mapToInt(Integer::parseInt).toArray();
        List<Integer> temp = new ArrayList<>();
        OptionalInt max = Arrays.stream(lst).max();
        int getMax = 0;
        if (max.isPresent()) {
            getMax = max.getAsInt();
        }
        if (max.isPresent()) {
            if (num > getMax) {
                int count = (int) Arrays.stream(line.split(" ")).
                        mapToInt(Integer::parseInt).
                        filter(m -> m == max.getAsInt()).count();
                String repeat = max.getAsInt() + " ";
                System.out.print(repeat.repeat(count));
                return;
            }
        }
        for (var v : lst) {
            int dif = Math.abs(num - v);
            temp.add(dif);
        }

        Optional<Integer> difMin = temp.stream()
                .min(Integer::compareTo);

        int counter = 0;
        StringBuilder str = new StringBuilder();
        for (var v : temp) {
            if (v == difMin.get()) {
                str.append(lst[counter]).append(" ");
            }
            counter++;
        }
        System.out.print(Stream.of(str)
                .map(StringBuilder::reverse)
                .collect(Collectors.joining(" ")).trim());
    }
}
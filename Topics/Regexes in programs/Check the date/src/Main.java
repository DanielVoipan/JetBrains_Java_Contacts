import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dateRegex = "([0-2][0-9]|(3)[0-1])[-|\\/|\\.](((0)[0-9])|((1)[0-2]))[-|\\/|\\.]\\d{4}|" +
                "\\d{4}[-|\\/|\\.](((0)[0-9])|((1)[0-2]))[-|\\/|\\.]([0-2][0-9]|(3)[0-1])";
        if (date.matches(dateRegex)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
package contacts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Person extends Base {
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthday;

    public Person(String name, String surname, String phoneNumber, LocalDate birthday, String gender) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Override
    ArrayList<String> getAll() {
        ArrayList<String> list = new ArrayList<>();
        list.add("person");
        list.add(name);
        list.add(surname);
        list.add(Objects.equals(birthday, null) ? "" : birthday.toString());
        list.add(gender);
        list.add(getPhoneNumber());
        list.add(getTimeCreated().toString());
        list.add(Objects.equals(getTimeLastEdit(), null) ? "" : getTimeLastEdit().toString());
        return list;
    }

    @Override
    void setField(String str, String value) {
        switch (str) {
            case "name" -> this.name = value;
            case "surname" -> this.surname = value;
            case "birthday" -> this.birthday = LocalDate.parse(value);
            case "gender" -> this.gender = value;
            case "phone" -> setPhoneNumber(value);
        }
        setTimeLastEdit();
    }

    @Override
    void showContact() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birth date: " + (Objects.equals(birthday, null) ? "[no data]" : birthday));
        System.out.println("Gender: " + (gender.equals("") ? "[no data]" : gender));
        System.out.println("Number: " + (getPhoneNumber().equals("") ? "[no data]" : getPhoneNumber()));
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + (Objects.equals(getTimeLastEdit(), null) ? "[no data]" : getTimeLastEdit()));
    }

    @Override
    void listContacts(int counter) {
        System.out.printf("%d. %s %s\n", counter, name, surname);
    }

    @Override
    void editMenu(int msg, String type) {
        if (msg == 0) {
            System.out.print("Select a field (name, surname, birth, gender, number): ");
        } else if (msg == 1) {
            switch (type) {
                case "name" -> System.out.print("Enter name: ");
                case "surname" -> System.out.print("Enter surname: ");
                case "number" -> System.out.print("Enter number: ");
                case "birth" -> System.out.print("Enter birth date: ");
                case "gender" -> System.out.print("Enter gender (M, F): ");
            }
        }
    }
}

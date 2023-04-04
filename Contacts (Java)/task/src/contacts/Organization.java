package contacts;

import java.util.ArrayList;
import java.util.Objects;

public class Organization extends Base {
    private String name;
    private String address;

    public Organization(String name, String address, String phoneNumber) {
        super(phoneNumber);
        this.name = name;
        this.address = address;
    }

    @Override
    ArrayList<String> getAll() {
        ArrayList<String> list = new ArrayList<>();
        list.add("organization");
        list.add(name);
        list.add(address);
        list.add(getPhoneNumber());
        list.add(getTimeCreated().toString());
        list.add(Objects.equals(getTimeLastEdit(), null) ? "" : getTimeLastEdit().toString());
        return list;
    }

    @Override
    void setField(String str, String value) {
        switch (str) {
            case "name" -> this.name = value;
            case "address" -> this.address = value;
            case "phone" -> setPhoneNumber(value);
        }
        setTimeLastEdit();
    }

    @Override
    void showContact() {
        System.out.println("Organization name: " + name);
        System.out.println("Address: " + (address.equals("") ? "[no data]" : address));
        System.out.println("Number: " + (getPhoneNumber().equals("") ? "[no data]" : getPhoneNumber()));
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + (Objects.equals(getTimeLastEdit(), null) ? "[no data]" : getTimeLastEdit()));
    }

    @Override
    void listContacts(int counter) {
        System.out.printf("%d. %s\n", counter, name);
    }

    @Override
    void editMenu(int msg, String type) {
        if (msg == 0) {
            System.out.print("Select a field (address, number): ");
        } else if (msg == 1) {
            switch (type) {
                case "address" -> System.out.print("Enter address: ");
                case "number" -> System.out.print("Enter number: ");
            }
        }
    }

}

package contacts;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static List<Base> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String lastMenu = null;
        String line;
        int currentId = 0;
        String phoneNumber;
        try {
            loadContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!exit) {
            if (Objects.equals(lastMenu, null)) {
                System.out.print("Enter action (add, list, search, count, exit): ");
                line = scanner.nextLine();
            } else {
                line = lastMenu;
            }
            switch (line) {
                case "add" -> {
                    System.out.print("Enter the type (person, organization): ");
                    String type = scanner.nextLine();
                    switch (type.toLowerCase()) {

                        case "person" -> {
                            System.out.print("Enter the name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter the surname: ");
                            String surname = scanner.nextLine();
                            System.out.print("Enter the birth date: ");
                            String readDate = scanner.nextLine();
                            LocalDate birthDate;
                            // validate birthDate
                            if (!validBirthDate(readDate)) {
                                System.out.println("Bad birth date!");
                                birthDate = null;
                            } else {
                                birthDate = LocalDate.parse(readDate);
                            }
                            System.out.print("Enter the gender (M, F): ");
                            String gender = scanner.nextLine();
                            // validate gender
                            if (!validGender(gender.toUpperCase())) {
                                System.out.println("Bad gender!");
                                gender = "";
                            }
                            System.out.print("Enter the number: ");
                            phoneNumber = scanner.nextLine();
                            // validate phoneNumber
                            if (!validatePhoneNumber(phoneNumber)) {
                                System.out.println("Wrong number format!");
                                phoneNumber = "";
                            }
                            list.add(new Person(name, surname, phoneNumber, birthDate, gender));
                            System.out.println("The record added.");
                        }

                        case "organization" -> {
                            System.out.print("Enter the organization name: ");
                            String orgName = scanner.nextLine();
                            System.out.print("Enter the address: ");
                            String address = scanner.nextLine();
                            System.out.print("Enter the number: ");
                            phoneNumber = scanner.nextLine();
                            // validate phoneNumber
                            if (!validatePhoneNumber(phoneNumber)) {
                                System.out.println("Wrong number format!");
                                phoneNumber = "";
                            }
                            list.add(new Organization(orgName, address, phoneNumber));
                            System.out.println("The record added.");
                        }
                    }
                    try {
                        saveContacts();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                case "edit" -> {
                    if (currentId != 0) {
                        String type;
                        Base c = setContactBasedOnId(currentId);
                        c.editMenu(0, null);
                        type = scanner.nextLine();
                        c.editMenu(1, type);
                        String value = scanner.nextLine();
                        editContact(currentId, type, value);
                        System.out.println("Saved");
                        c.showContact();
                        lastMenu = "record";
                        try {
                            saveContacts();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                case "search" -> {
                    System.out.print("Enter search query: ");
                    String searchC = scanner.nextLine();
                    ArrayList<Base> searchContactList = searchContact(searchC);
                    int counter = 1;
                    for (Base c : searchContactList) {
                        c.listContacts(counter);
                        counter++;
                    }
                    if (searchContactList.isEmpty()) {
                        System.out.println("Found 0 results.");
                        lastMenu = line;
                        break;
                    }
                    System.out.println();
                    System.out.printf("[%s] Enter action ([number], back, again): ", line);
                    String action = scanner.nextLine();
                    switch (action) {
                        case "again" -> lastMenu = line;
                        case "back" -> {
                            lastMenu = null;
                            currentId = 0;
                        }
                        default -> {
                            if (action.matches("[0-2]+")) {
                                Base c = setContactBasedOnId(Integer.parseInt(action));
                                c.showContact();
                                currentId = Integer.parseInt(action);
                            }
                            lastMenu = "record";
                        }
                    }
                }

                case "list" -> {
                    int counter = 1;
                    boolean found = false;
                    for (Base c : list) {
                        c.listContacts(counter);
                        counter++;
                        found = true;
                    }
                    if (!found) {
                        System.out.println("The Phone Book has no records.");
                        break;
                    }
                    System.out.println();
                    System.out.printf("[%s] Enter action ([number], back): ", line);
                    String action = scanner.nextLine();
                    if (action.equals("back")) {
                        lastMenu = null;
                    } else if (action.matches("[0-9]+")) {
                        Base c = setContactBasedOnId(Integer.parseInt(action));
                        c.showContact();
                        currentId = Integer.parseInt(action);
                        lastMenu = "record";
                    }
                }

                case "delete" -> {
                    if (currentId != 0) {
                        list.remove(currentId - 1);
                        System.out.println("The record removed!");
                        lastMenu = null;
                        currentId = 0;
                        try {
                            saveContacts();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                case "record" -> {
                    System.out.printf("[%s] Enter action (edit, delete, menu): ", line);
                    String action = scanner.nextLine();
                    switch (action) {
                        case "edit" -> lastMenu = "edit";
                        case "delete" -> lastMenu = "delete";
                        case "menu" -> {
                            lastMenu = null;
                            currentId = 0;
                        }
                    }
                }

                case "count" -> System.out.printf("The Phone Book has %d records.\n", list.size());
                case "exit" -> exit = true;
            }
            System.out.println();
        }
    }

    // validate phoneNumber
    static boolean validatePhoneNumber(String phoneNumber) {
        // not allow more than 1 pair of parentheses
        String regexDuplicateParanthese = "(^|\\s)?\\(([0-9]+)?\\)(\\s|$)?";
        Pattern pattern = Pattern.compile(regexDuplicateParanthese);
        Matcher matcher = pattern.matcher(phoneNumber);
        int contor = 0;
        while (matcher.find()) {
            ++contor;
        }
        if (contor > 1) {
            return false;
        }
        // check phoneNumber format.
        String regex = "^(\\+)?(([0-9]\\s)|\\([0-9]+\\))?(\\([0-9a-zA-Z]{2,}\\)|[0-9a-zA-Z]{2,})?" +
                "\\s?(\\([0-9a-zA-Z]{2,}\\)|[0-9a-zA-Z]{2,})?(-|\\s)?" +
                "(\\([0-9a-zA-Z]{2,}\\)|[0-9a-zA-Z]{2,})?(-|\\s)?" +
                "(\\([0-9a-zA-Z]{2,}\\)|[0-9a-zA-Z]{2,})?";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(phoneNumber);
        int numberOf = 0;
        int length = phoneNumber.split("\\s|-").length;
        while (matcher.find()) {
            numberOf = matcher.group().split("\\s|-").length;
        }
        return numberOf == length;
    }

    // check if valid gender
    static boolean validGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

    // check valid birthDate
    static boolean validBirthDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // return contact base object based on id from list
    public static Base setContactBasedOnId(int id) {
        int counter = 1;
        Base current = null;
        for (Base c : list) {
            if (counter == id) {
                current = c;
                break;
            }
            counter++;
        }
        return current;
    }

    // edit contact
    static void editContact(int id, String type, String value) {
        Base c = setContactBasedOnId(id);
        switch (type) {
            case "name", "surname" -> {
                c.setField(type, value);
            }
            case "number" -> {
                boolean validatePhoneNumber = validatePhoneNumber(value);
                if (!validatePhoneNumber) {
                    System.out.println("Wrong number format!");
                    value = "";
                }
                c.setField("phone", value);
            }
            case "gender" -> {
                if (!validGender(value.toUpperCase())) {
                    System.out.println("Bad gender!");
                    value = "";
                }
                c.setField("gender", value);
            }

            case "birth" -> {
                // validate birthDate
                if (!validBirthDate(value)) {
                    System.out.println("Bad birth date!");
                    c.setField("birthday", null);
                } else {
                    c.setField("birthday", value);
                }
            }
            case "address" -> {
                c.setField("address", value);
            }
        }
    }

    // search for Contacts, patterns accepted.
    static ArrayList<Base> searchContact(String str) {
        Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
        ArrayList<Base> contactList = new ArrayList<>();
        for (Base c : list) {
            ArrayList<String> all = c.getAll();
            for (int i = 1; i < all.size() - 1; i++) {
                Matcher matcher = pattern.matcher(all.get(i));
                if (matcher.matches()) {
                    contactList.add(c);
                } else if (matcher.find()) {
                    contactList.add(c);
                }
            }
        }
        return contactList;
    }

    // load contacts from file
    static void loadContacts() throws IOException, ClassNotFoundException {
        String filename = "contacts.data";
        Base[] contacts = (Base[]) SerializationUtils.deserialize(filename);
        list.addAll(Arrays.asList(contacts));
    }

    // write contacts to file
    static void saveContacts() throws IOException {
        String filename = "contacts.data";
        Base[] contacts = new Base[list.size()];
        int counter = 0;
        for (Base c : list) {
            contacts[counter] = c;
            counter++;
        }
        SerializationUtils.serialize(contacts, filename);
    }
}

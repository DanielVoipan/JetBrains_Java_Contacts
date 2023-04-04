package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

abstract public class Base implements Serializable {
    private String phoneNumber;

    private LocalDateTime timeCreated;

    private LocalDateTime timeLastEdit;

    public Base(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.timeCreated = currentDateTime();
    }

    // set phoneNumber
    protected void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected void setTimeLastEdit() {
        this.timeLastEdit = currentDateTime();
    }
    // getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }

    static LocalDateTime currentDateTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = date.format(format);
        return LocalDateTime.parse(formattedDateTime);
    }
    abstract ArrayList<String> getAll();
    abstract void setField(String str, String value);

    abstract void showContact();

    abstract void listContacts(int counter);

    abstract void editMenu(int msg, String type);
}

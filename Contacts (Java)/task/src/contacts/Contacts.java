package contacts;

abstract class Contacts {
    abstract void add(String... args);
}

abstract class P extends Contacts {

    @Override
    void add(String... args) {

    }
}
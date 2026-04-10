import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.ArrayStorage;

public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Client c1 = new Client();
        c1.setUuid("uuid1");
        Client c2 = new Client();
        c2.setUuid("uuid2");
        Client c3 = new Client();
        c3.setUuid("uuid3");
        Client c4 = new Client();

        ARRAY_STORAGE.save(c1);
        ARRAY_STORAGE.save(c2);
        ARRAY_STORAGE.save(c3);

        System.out.println("Get c1: " + ARRAY_STORAGE.get(c1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.update(c4);

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(c1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Client c : ARRAY_STORAGE.getAll()) {
            System.out.println(c);
        }
    }
}

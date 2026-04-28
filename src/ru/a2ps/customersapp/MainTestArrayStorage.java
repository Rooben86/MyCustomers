package ru.a2ps.customersapp;

import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.SortedArrayStorage;
import ru.a2ps.customersapp.storage.Storage;

import java.util.Arrays;

public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Client c1 = new Client("uuid1");
        final Client c2 = new Client("uuid2");
        final Client c3 = new Client("uuid4");
        final Client c4 = new Client("uuid3");

        ARRAY_STORAGE.save(c1);
        ARRAY_STORAGE.save(c2);
        ARRAY_STORAGE.save(c3);

        System.out.println("Get c1: " + ARRAY_STORAGE.get(c1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.update(c4);

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        System.out.println("index of c2 =" + Arrays.binarySearch(ARRAY_STORAGE.getAll(), 0, ARRAY_STORAGE.size(), c2));
        printAll();
        ARRAY_STORAGE.delete(c1.getUuid());
        printAll();
        ARRAY_STORAGE.save(c4);
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

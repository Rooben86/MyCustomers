package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

public class ArrayStorage {
    private final Client[] storage = new Client[1000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Client c) {
        storage[size] = c;
        size++;
    }

    public Client get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("no client with such uuid found");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println("no client with such uuid found");
    }

    public Client[] getAll() {
        Client[] clients = new Client[size];
        System.arraycopy(storage, 0, clients, 0, size);
        return clients;
    }

    public int size() {
        return size;
    }
}

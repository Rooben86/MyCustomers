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

    public void update(Client c) {
        int index = getIndex(c.getUuid());
        if (index == -1) {
            System.out.println("client " + c.getUuid() + "doesn't exist");
        } else {
            storage[index] = c;
        }
    }

    public void save(Client c) {
        if (getIndex(c.getUuid()) != -1) {
            System.out.println("client " + c.getUuid() + " already exists");
        } else if (size == storage.length - 1) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = c;
            size++;
        }
    }

    public Client get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("client " + uuid + " doesn't exist");
            return null;
        }
        return storage[index];
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("client " + uuid + " doesn't exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public Client[] getAll() {
        Client[] clients = new Client[size];
        System.arraycopy(storage, 0, clients, 0, size);
        return clients;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

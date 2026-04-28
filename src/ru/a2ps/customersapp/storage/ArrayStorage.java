package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

public class ArrayStorage extends AbstractArrayStorage {

    protected void insertElement(Client c, int index) {
            storage[size] = c;
    }

    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

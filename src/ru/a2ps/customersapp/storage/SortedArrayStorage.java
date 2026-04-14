package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void doSave(Client c, int index) {
        int insertionPoint = -index - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = c;
    }

    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Client searchKey = new Client();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
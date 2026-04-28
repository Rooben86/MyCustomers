package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void insertElement(Client c, int index) {
        int insertionPoint = -index - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = c;
    }

    protected void fillDeletedElement(int index) {
        int numsMoved = size - index - 1;
        if (numsMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numsMoved);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Client searchKey = new Client(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
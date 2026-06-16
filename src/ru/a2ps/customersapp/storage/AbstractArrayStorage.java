package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.exception.StorageException;
import ru.a2ps.customersapp.model.Client;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_LIMIT = 1000;
    protected final Client[] storage = new Client[STORAGE_LIMIT];
    protected static int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void doUpdate(Client c, Object index) {
        storage[(Integer) index] = c;
    }

    @Override
    public List<Client> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public void doSave(Client c, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", c.getUuid());
        } else {
            insertElement(c, (Integer) index);
            size++;
        }
    }

    @Override
    public void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public Client doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public int size() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Client c, int index);

    protected abstract Integer getSearchKey(String uuid);
}
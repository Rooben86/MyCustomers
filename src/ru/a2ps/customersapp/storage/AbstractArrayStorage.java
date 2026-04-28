package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.exception.ExiststorageException;
import ru.a2ps.customersapp.exception.NotExiststorageException;
import ru.a2ps.customersapp.exception.StorageException;
import ru.a2ps.customersapp.model.Client;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 1000;
    protected final Client[] storage = new Client[STORAGE_LIMIT];
    protected static int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Client c) {
        int index = getIndex(c.getUuid());
        if (index < 0) {
            throw new NotExiststorageException(c.getUuid());
        } else {
            storage[index] = c;
            System.out.println("Client " + c + " updated");
        }
    }

    public Client get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExiststorageException(uuid);
        }
        return storage[index];
    }

    public void save(Client c) {
        int index = getIndex(c.getUuid());
        if (index >= 0) {
            throw new ExiststorageException(c.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", c.getUuid());
        } else {
            insertElement(c, index);
            System.out.println("Client " + c + " saved");
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExiststorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            System.out.println("client " + uuid + " deleted");
            size--;
        }
    }

    public Client[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return  size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Client c, int index);

    protected abstract int getIndex(String uuid);
}
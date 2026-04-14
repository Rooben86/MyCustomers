package ru.a2ps.customersapp.storage;

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
            System.out.println("client " + c.getUuid() + "doesn't exist");
        } else {
            storage[index] = c;
            System.out.println("Client " + c + " updated");
        }
    }

    public Client get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("client " + uuid + " doesn't exist");
            return null;
        }
        return storage[index];
    }

    public void save(Client c) {
        int index = getIndex(c.getUuid());
        if (index >= 0) {
            System.out.println("client " + c.getUuid() + " already exists");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            doSave(c, index);
            System.out.println("Client " + c + " saved");
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("client " + uuid + " doesn't exist");
        } else {
            doDelete(index);
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

    protected abstract void doDelete(int index);

    protected abstract void doSave(Client c, int index);

    protected abstract int getIndex(String uuid);
}
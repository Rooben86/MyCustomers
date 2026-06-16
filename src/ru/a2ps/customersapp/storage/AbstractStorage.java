package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.exception.ExiststorageException;
import ru.a2ps.customersapp.exception.NotExiststorageException;
import ru.a2ps.customersapp.model.Client;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract List<Client> doCopyAll();

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Client c, Object searchKey);

    protected abstract Client doGet(Object searchKey);

    protected abstract void doSave(Client c, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    public void update(Client c) {
        Object searchKey = mayNotExist(c.getUuid());
        doUpdate(c, searchKey);
        System.out.println("Client " + c + " updated");
    }

    public void save(Client c) {
        Object searchKey = mayExist(c.getUuid());
        doSave(c, searchKey);
        System.out.println("Client " + c + " saved");
    }

    public void delete(String uuid) {
        Object searchKey = mayNotExist(uuid);
        doDelete(searchKey);
        System.out.println("client " + uuid + " deleted");
    }

    public Client get(String uuid) {
        Object searchKey = mayNotExist(uuid);
        return doGet(searchKey);
    }

    private Object mayNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExiststorageException(uuid);
        }
        return searchKey;
    }

    private Object mayExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExiststorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Client> getAllSorted() {
        List<Client> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
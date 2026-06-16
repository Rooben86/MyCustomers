package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapClientStorage extends AbstractStorage {

    private final Map<String, Client> map = new HashMap<>();

    @Override
    protected Client getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Client c, Object client) {
        map.put(c.getUuid(), c);
    }

    @Override
    protected boolean isExist(Object client) {
        return client != null;
    }

    @Override
    protected void doSave(Client c, Object client) {
        map.put(c.getUuid(), c);
    }

    @Override
    protected Client doGet(Object client) {
        return (Client) client;
    }

    @Override
    protected void doDelete(Object client) {
        map.remove(((Client) client).getUuid());

    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Client> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}

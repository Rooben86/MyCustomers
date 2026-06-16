package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Client> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Client c, Object uuid) {
        map.put((String) uuid, c);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    protected void doSave(Client c, Object uuid) {
        map.put((String) uuid, c);
    }

    @Override
    protected Client doGet(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    protected void doDelete(Object uuid) {
        map.remove((String) uuid);

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

package ru.a2ps.customersapp.list;

import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Client> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Client c, Object index) {
        list.set((Integer) index, c);
    }

    @Override
    protected void doSave(Client c, Object index) {
        list.add(c);
    }

    @Override
    protected Client doGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void doDelete(Object index) {
        list.remove(((Integer) index).intValue());
    }

    @Override
    public void clear() {
        list.clear();
    }

    public void doUpdate(int index, Client c) {
            list.set(index, c);
    }

    @Override
    public List<Client> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }


}

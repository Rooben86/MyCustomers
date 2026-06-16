package ru.a2ps.customersapp.storage;

import ru.a2ps.customersapp.model.Client;

import java.util.List;

public interface Storage {

    void clear();

    void update(Client c);

    void save(Client c);

    Client get(String uuid);

    void delete(String uuid);

    List<Client> getAllSorted();

    int size();
}
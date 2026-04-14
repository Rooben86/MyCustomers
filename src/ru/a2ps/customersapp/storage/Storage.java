package ru.a2ps.customersapp.storage;
import ru.a2ps.customersapp.model.Client;

public interface Storage {

    void clear();

    void update(Client c);

    void save(Client c);

    Client get(String uuid);

    void delete(String uuid);

    Client[] getAll();

    int size();
}
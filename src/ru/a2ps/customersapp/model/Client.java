package ru.a2ps.customersapp.model;

import java.util.Objects;
import java.util.UUID;

public class Client implements Comparable<Client> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    public Client(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Client(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Client_" + uuid + " (" + fullName + ")";
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;
        return uuid.equals(client.uuid) && fullName.equals(client.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Client o) {
        int nameCompare = fullName.compareTo(o.fullName);
        return nameCompare != 0 ? nameCompare : uuid.compareTo(o.uuid);
    }
}

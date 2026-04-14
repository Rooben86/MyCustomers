package ru.a2ps.customersapp.model;

public class Client implements Comparable<Client> {

    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;
        return uuid.equals(client.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Client o) {
        return uuid.compareTo(o.uuid);
    }
}

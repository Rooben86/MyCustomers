package ru.a2ps.customersapp;

import ru.a2ps.customersapp.model.Client;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Client CLIENT_1 = new Client(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static final Client CLIENT_2 = new Client(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static final Client CLIENT_3 = new Client(UUID_3, "Name3");
    private static final String UUID_4 = "uuid4";
    private static final Client CLIENT_4 = new Client(UUID_4, "Name4");

    static void main() {
        Collection<Client> coll = new ArrayList<>();
        coll.add(CLIENT_1);
        coll.add(CLIENT_2);
        coll.add(CLIENT_3);

        for (Client c : coll) {
            System.out.println(c);
            if (c.getUuid().equals(UUID_1)) {
//                coll.remove(c);
            }
        }
        Iterator<Client> iterator = coll.iterator();
        while (iterator.hasNext()) {
            Client c = iterator.next();
            System.out.println(c);
            if (c.getUuid().equals(UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println("collection toString: " + coll.toString());

        Map<String, Client> map = new HashMap<>();
        map.put(UUID_1, CLIENT_1);
        map.put(UUID_2, CLIENT_2);
        map.put(UUID_3, CLIENT_3);
//wrong
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }
//correct
        for (Map.Entry<String, Client> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        List<Client> clients = Arrays.asList(CLIENT_1, CLIENT_2, CLIENT_3);
        clients.remove(1);
        System.out.println(clients);
    }
}

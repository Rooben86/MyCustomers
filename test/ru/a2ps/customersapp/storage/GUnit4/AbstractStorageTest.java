package ru.a2ps.customersapp.storage.GUnit4;

import org.junit.Before;
import org.junit.Test;
import ru.a2ps.customersapp.exception.ExiststorageException;
import ru.a2ps.customersapp.exception.NotExiststorageException;
import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.Storage;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Client CLIENT_1 = new Client(UUID_1, "Name1");
    private static final Client CLIENT_2 = new Client(UUID_2, "Name2");
    private static final Client CLIENT_3 = new Client(UUID_3, "Name3");
    private static final Client CLIENT_4 = new Client(UUID_4, "Name4");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(CLIENT_1);
        storage.save(CLIENT_2);
        storage.save(CLIENT_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Client client = new Client(UUID_1, "New name");
        storage.update(client);
        assertSame(client, storage.get(UUID_1));
    }

    @Test(expected = NotExiststorageException.class)
    public void updateNonExistent() throws Exception {
        storage.update(CLIENT_4);
    }

    @Test
    public void getAllSorted() {
        List<Client> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(CLIENT_1, CLIENT_2, CLIENT_3));
    }

    @Test
    public void save() throws Exception {
        storage.save(CLIENT_4);
        assertSize(4);
        assertGet(CLIENT_4);
    }

    @Test (expected = ExiststorageException.class)
    public void saveExistent() throws Exception {
        storage.save(CLIENT_2);
    }

    @Test (expected = NotExiststorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExiststorageException.class)
    public void deleteNonexistent() {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(CLIENT_1);
        assertGet(CLIENT_2);
        assertGet(CLIENT_3);
    }

    @Test(expected = NotExiststorageException.class)
    public void getNonExistent() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Client client) {
        assertEquals(client, storage.get(client.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
package ru.a2ps.customersapp.storage.GUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.a2ps.customersapp.exception.ExiststorageException;
import ru.a2ps.customersapp.exception.NotExiststorageException;
import ru.a2ps.customersapp.exception.StorageException;
import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.Storage;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    protected static final int STORAGE_LIMIT = 1000;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Client(UUID_1));
        storage.save(new Client(UUID_2));
        storage.save(new Client(UUID_3));
    }

    //TODO write tests for all methods, special cases (exist, notExist) and overflow)
    @Test
    public void clear() {
        storage.clear();
        for (Client c : storage.getAll()) {
            Assert.assertNull(c);
        }
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Client c = new Client(UUID_2);
        storage.update(c);
        Assert.assertEquals("uuid2", c.toString());
    }

    @Test(expected = NotExiststorageException.class)
    public void updateNonexistent() {
        storage.update(new Client("uuid4"));
    }

    @Test
    public void get() {
        Client c = new Client("uuid1");
        Assert.assertEquals(c, storage.get("uuid1"));
    }

    @Test(expected = NotExiststorageException.class)
    public void getUnexistent() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Client c = new Client("uuid4");
        storage.save(c);
        Assert.assertSame(c, storage.get("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExiststorageException.class)
    public void saveExistent() {
        storage.save(new Client(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void StorageOverflow() {
        for (int i = 3; i < STORAGE_LIMIT; i++) {
            storage.save(new Client("uuid" + i));
        }
        storage.save(new Client("overflow-client"));
    }


    @Test
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExiststorageException.class)
    public void deleteNonexistent() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Client[] array = storage.getAll();
        Assert.assertEquals(3, array.length);
        Assert.assertSame("uuid1", array[0].getUuid());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}


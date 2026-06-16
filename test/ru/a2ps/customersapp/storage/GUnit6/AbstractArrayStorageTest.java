package ru.a2ps.customersapp.storage.GUnit6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.a2ps.customersapp.exception.ExiststorageException;
import ru.a2ps.customersapp.exception.NotExiststorageException;
import ru.a2ps.customersapp.exception.StorageException;
import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.Storage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    protected static final int STORAGE_LIMIT = 1000;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Client(UUID_1, "Name1"));
        storage.save(new Client(UUID_2, "Name2"));
        storage.save(new Client(UUID_3, "Name3"));
    }

    @Test
    public void clear() {
        storage.clear();
        for (Client c : storage.getAllSorted()) {
            assertNull(c);
        }
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Client c = new Client(UUID_2, "New name");
        storage.update(c);
        assertEquals("uuid2", c.toString());
    }

    @Test
    public void updateNonexistent() {
        assertThrows(NotExiststorageException.class, () -> {
            storage.update(new Client("uuid4", "New name"));
        });
    }

    @Test
    public void get() {
        Client c = new Client("uuid1", "New name");
        assertEquals(c, storage.get("uuid1"));
    }

    @Test
    public void getUnexistent() {
        assertThrows(NotExiststorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    public void save() {
        Client c = new Client("uuid4", "New name");
        storage.save(c);
        assertSame(c, storage.get("uuid4"));
        assertEquals(4, storage.size());
    }

    @Test
    public void saveExistent() {
        assertThrows(ExiststorageException.class, () -> {
            storage.save(new Client(UUID_1, "New name"));
        });
    }

    @Test
    public void StorageOverflow() {
        assertThrows(StorageException.class, () -> {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                storage.save(new Client("uuid" + i, "New name"));
            }
            storage.save(new Client("overflow-client", "New name"));
        });
    }


    @Test
    public void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
    }

    @Test
    public void deleteNonexistent() {
        assertThrows(NotExiststorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    public void getAll() {
        List<Client> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertSame("uuid1", list.get(0).getUuid());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}


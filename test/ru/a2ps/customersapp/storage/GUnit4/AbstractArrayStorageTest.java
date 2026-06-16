package ru.a2ps.customersapp.storage.GUnit4;

import org.junit.Assert;
import org.junit.Test;
import ru.a2ps.customersapp.exception.StorageException;
import ru.a2ps.customersapp.model.Client;
import ru.a2ps.customersapp.storage.AbstractArrayStorage;
import ru.a2ps.customersapp.storage.Storage;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void StorageOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Client("uuid" + i, "New name"));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Client("overflown-client", "New name"));
    }
}
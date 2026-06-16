package ru.a2ps.customersapp.storage.GUnit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.a2ps.customersapp.storage.MapClientStorage;

@RunWith(Suite.class)
@Suite.SuiteClasses (
        {
                ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapUuidStorageTest.class,
                MapClientStorage.class
        })
public class TotalStorageTest {
}

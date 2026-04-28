package ru.a2ps.customersapp.exception;

public class ExiststorageException extends StorageException {
    public ExiststorageException(String uuid) {
        super("client " + uuid + " already exists", uuid);
    }
}

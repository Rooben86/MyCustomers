package ru.a2ps.customersapp.exception;

public class NotExiststorageException extends StorageException {
    public NotExiststorageException(String uuid) {
        super("client " + uuid + " doesn't exist", uuid);
    }
}

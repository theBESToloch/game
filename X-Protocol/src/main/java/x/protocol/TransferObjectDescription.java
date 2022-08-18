package x.protocol;

import java.util.UUID;

/**
 * Класс для описания структуры передаваемого объекта.
 */
public class TransferObjectDescription {

    private final UUID id;

    private final byte[] structure;

    private TransferObjectDescription(UUID id, byte[] structure) {
        this.id = id;
        this.structure = structure;
    }

    public UUID getId() {
        return id;
    }

    public byte[] getStructure() {
        return structure;
    }

    public static TransferObjectDescription compile(Class<?> objectClass) {

        byte[] structure = compileStructure(objectClass);

        return new TransferObjectDescription(UUID.randomUUID(), structure);
    }

    private static byte[] compileStructure(Class<?> objectClass) {
        objectClass.getDeclaredFields();
    }
}

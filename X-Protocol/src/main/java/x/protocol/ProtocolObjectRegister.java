package x.protocol;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;

public class ProtocolObjectRegister {

    Map<UUID, TransferObjectDescription> registerObject;

    public ProtocolObjectRegister() {
        registerObject = new TreeMap<>();
    }

    public void registerTransferObject(Class<?> o) {
        Objects.requireNonNull(o);
        TransferObjectDescription objectDescription = TransferObjectDescription.compile(o);
        registerObject.put(objectDescription.getId(), objectDescription);
    }
}

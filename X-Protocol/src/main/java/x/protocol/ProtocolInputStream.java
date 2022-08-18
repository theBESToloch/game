package x.protocol;

public interface ProtocolInputStream {

    /**
     * Метод чтения объекта из потока байт.
     *
     * @return Object
     */
    Object readObject();

    /**
     * Method for register data object, that can transfer between client and server in the future
     *
     * @param o - transfer object
     */
    void registerObject(Object o);
}

package x.protocol;

public class StructureCodes {

    /**
     * Null object reference.
     */
    static final byte TC_NULL =         (byte)0x70;

    /**
     * Reference to an object already written into the stream.
     */
    static final byte TC_REFERENCE =    (byte)0x71;

    /**
     * new Class Descriptor.
     */
    static final byte TC_CLASSDESC =    (byte)0x72;

    /**
     * new Object.
     */
    static final byte TC_OBJECT =       (byte)0x73;

    /**
     * new String.
     */
    static final byte TC_STRING =       (byte)0x74;

    /**
     * new Array.
     */
    static final byte TC_ARRAY =        (byte)0x75;

}

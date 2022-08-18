package x.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketProtocolInputStream implements ProtocolInputStream {

    private ProtocolObjectRegister descriptionRegister;
    private InputStream in;

    public SocketProtocolInputStream(Socket socket) throws IOException {
        this.in = socket.getInputStream();
    }

    @Override
    public Object readObject() {
        return null;
    }

    @Override
    public void registerObject(Object o) {
        descriptionRegister.registerTransferObject(o.getClass());
    }
}

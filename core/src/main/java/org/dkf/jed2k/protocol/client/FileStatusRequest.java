package org.dkf.jed2k.protocol.client;

import org.dkf.jed2k.exception.JED2KException;
import org.dkf.jed2k.protocol.Dispatchable;
import org.dkf.jed2k.protocol.Dispatcher;
import org.dkf.jed2k.protocol.Hash;

public class FileStatusRequest extends Hash implements Dispatchable {

    public FileStatusRequest(Hash hash) {
        super(hash);
    }

    public FileStatusRequest() {
        super();
    }

    @Override
    public void dispatch(Dispatcher dispatcher) throws JED2KException {
        dispatcher.onClientFileStatusRequest(this);
    }

    @Override
    public String toString() {
        return String.format("FileStatusRequest %s", super.toString());
    }
}

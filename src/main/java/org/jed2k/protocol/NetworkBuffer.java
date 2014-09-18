package org.jed2k.protocol;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.jed2k.number.UByte;
import org.jed2k.number.UInteger;
import org.jed2k.number.ULong;
import org.jed2k.number.UShort;

public class NetworkBuffer extends Buffer{
    private ByteBuffer originator;
    public NetworkBuffer(ByteBuffer originator){
        this.originator = originator;
        this.originator.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public Buffer put(UByte v) {
        return null;
    }

    @Override
    public Buffer get(UByte v) {
        return null;
    }

    @Override
    public Buffer put(UShort v) {
        originator.putShort(v.shortValue());
        return this;
    }

    @Override
    public Buffer get(UShort v) {
        v.assign(originator.getShort());
        return this;
    }

    @Override
    public Buffer put(UInteger v) {
        originator.putInt(v.intValue());
        return this;
    }

    @Override
    public Buffer get(UInteger v) {
        v.assign(originator.getInt());
        return this;
    }

    @Override
    public Buffer put(ULong v) {
        originator.putLong(v.longValue());
        return this;
    }

    @Override
    public Buffer get(ULong v) {
        v.assign(originator.getLong());
        return this;
    }

    @Override
    public Buffer get(UInt8 v) {
      byte b[] = { 0 };
      originator.get(b, 0, 1);
      v.assign(b[0]);
      return this;
    }

    @Override
    public Buffer put(UInt8 v) {
      originator.put(new byte[] { v.byteValue() }, 0, 1);
      return this;
    }

    @Override
    public Buffer get(UInt16 v) {
        v.assign(originator.getShort());
        return this;
    }

    @Override
    public Buffer put(UInt16 v) {
        originator.putShort(v.shortValue());
        return this;
    }

    @Override
    public Buffer get(UInt32 v) {
        v.assign(originator.getInt());
        return this;
    }

    @Override
    public Buffer put(UInt32 v) {
        originator.putInt(v.intValue());
        return this;
    }
}
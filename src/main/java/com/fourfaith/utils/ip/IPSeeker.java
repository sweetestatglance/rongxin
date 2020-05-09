//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class IPSeeker {
    final ByteBuffer buffer;
    final Helper h;
    final int offsetBegin;
    final int offsetEnd;

    public IPSeeker(Path path) throws IOException {
        if (Files.exists(path, new LinkOption[0])) {
            this.buffer = ByteBuffer.wrap(Files.readAllBytes(path));
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
            this.offsetBegin = this.buffer.getInt(0);
            this.offsetEnd = this.buffer.getInt(4);
            if (this.offsetBegin != -1 && this.offsetEnd != -1) {
                this.h = new Helper(this);
            } else {
                throw new IllegalArgumentException("File Format Error");
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public IPLocation getLocation(byte ip1, byte ip2, byte ip3, byte ip4) {
        return this.getLocation(new byte[]{ip1, ip2, ip3, ip4});
    }

    protected final IPLocation getLocation(byte[] ip) {
        return this.h.getLocation(this.h.locateOffset(ip));
    }

    protected final IPLocation getLocation2(byte[] ip) {
        return this.getLocation(ip);
    }

    public IPLocation getLocation(Inet4Address address) {
        return this.getLocation(address.getAddress());
    }
}

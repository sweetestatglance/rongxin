//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Map;

public class IPSeekerSimpleCache extends IPSeeker {
    private final Map<byte[], IPLocation> cache = new Hashtable();

    public IPSeekerSimpleCache(Path path) throws IOException {
        super(path);
    }

    public synchronized IPLocation getLocation(byte ip1, byte ip2, byte ip3, byte ip4) {
        byte[] ip = new byte[]{ip1, ip2, ip3, ip4};
        return this.cache.containsKey(ip) ? (IPLocation)this.cache.get(ip) : (IPLocation)this.cache.put(ip, super.getLocation(ip));
    }
}

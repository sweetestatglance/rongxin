//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Helper {
    static final int RecordLength = 7;
    static final byte RedirectMode1 = 1;
    static final byte RedirectMode2 = 2;
    private final ByteBuffer buffer;
    private final IPSeeker seeker;

    static int calcMiddleOffset(int begin, int end) {
        int records = (end - begin) / 7 >> 1;
        return begin + (records == 0 ? 1 : records) * 7;
    }

    static int compare(byte[] ip, byte[] begin) {
        for(int i = 0; i < 4; ++i) {
            int x = ip[i];
            int y = begin[i];
            if ((x & 255) > (y & 255)) {
                return 1;
            }

            if ((x ^ y) != 0) {
                return -1;
            }
        }

        return 0;
    }

    Helper(IPSeeker seeker) {
        this.buffer = seeker.buffer;
        this.seeker = seeker;
    }

    IPLocation getLocation(int offset) {
        if (offset == -1) {
            return IPLocation.Unknown;
        } else {
            this.buffer.position(offset + 4);
            switch(this.buffer.get()) {
                case 1:
                    this.buffer.position(offset = this.readInt3());
                    String country;
                    switch(this.buffer.get()) {
                        case 2:
                            country = this.readString(this.readInt3());
                            this.buffer.position(offset + 4);
                            break;
                        default:
                            country = this.readString(offset);
                    }

                    return IPLocation.of(country, this.readArea(this.buffer.position()));
                case 2:
                    return IPLocation.of(this.readString(this.readInt3()), this.readArea(offset + 8));
                default:
                    return IPLocation.of(this.readString(this.buffer.position() - 1), this.readArea(this.buffer.position()));
            }
        }
    }

    int locateOffset(byte[] address) {
        switch(compare(address, this.readIP(this.seeker.offsetBegin))) {
            case -1:
                return -1;
            case 0:
                return this.seeker.offsetBegin;
            default:
                int middleOffset = 0;
                int begin = this.seeker.offsetBegin;
                int end = this.seeker.offsetEnd;

                while(begin < end) {
                    switch(compare(address, this.readIP(middleOffset = calcMiddleOffset(begin, end)))) {
                        case -1:
                            if (middleOffset == end) {
                                end -= 7;
                                middleOffset = end;
                            } else {
                                end = middleOffset;
                            }
                            break;
                        case 0:
                            return this.readInt3(middleOffset + 4);
                        case 1:
                            begin = middleOffset;
                    }
                }

                middleOffset = this.readInt3(middleOffset + 4);
                switch(compare(address, this.readIP(middleOffset))) {
                    case -1:
                    case 0:
                        return middleOffset;
                    default:
                        return -1;
                }
        }
    }

    private String readArea(int offset) {
        this.buffer.position(offset);
        switch(this.buffer.get()) {
            case 1:
            case 2:
                offset = this.readInt3(offset + 1);
                return offset != 0 ? this.readString(offset) : IPLocation.Unknown.getArea();
            default:
                return this.readString(offset);
        }
    }

    private int readInt3() {
        return this.buffer.getInt() & 16777215;
    }

    int readInt3(int offset) {
        this.buffer.position(offset);
        return this.buffer.getInt() & 16777215;
    }

    byte[] readIP(int offset) {
        this.buffer.position(offset);
        return IP4Util.toBytes(this.buffer.getInt());
    }

    private String readString(int offset) {
        this.buffer.position(offset);
        byte[] buf = new byte[255];
        offset = -1;

        do {
            ++offset;
        } while((buf[offset] = this.buffer.get()) != 0);

        return offset != 0 ? new String(buf, 0, offset, Charset.forName("GBK")) : null;
    }
}

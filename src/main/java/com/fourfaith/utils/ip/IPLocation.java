//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

public final class IPLocation {
    public static final IPLocation Unknown = new IPLocation("未知国家", "未知地区");
    private final String area;
    private final String country;

    static IPLocation of(String country, String area) {
        if (country == null || country.isEmpty()) {
            country = Unknown.country;
        }

        if (area == null || area.isEmpty()) {
            area = Unknown.area;
        }

        return new IPLocation(country, area);
    }

    private IPLocation(String country, String area) {
        this.country = country;
        this.area = area.equals("CZ88.NET") ? "局域网" : area;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            IPLocation other = (IPLocation)obj;
            if (this.area == null) {
                if (other.area != null) {
                    return false;
                }
            } else if (!this.area.equals(other.area)) {
                return false;
            }

            if (this.country == null) {
                if (other.country != null) {
                    return false;
                }
            } else if (!this.country.equals(other.country)) {
                return false;
            }

            return true;
        }
    }

    public String getArea() {
        return this.area;
    }

    public String getCountry() {
        return this.country;
    }

    public int hashCode() {
        int prime = 1;
        int result = 1;
        result = 31 * result + this.area.hashCode();
        result = 31 * result + this.country.hashCode();
        return result;
    }

    public String toString() {
        return this.area + ' ' + this.country;
    }
}

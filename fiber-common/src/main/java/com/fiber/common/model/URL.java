package com.fiber.common.model;


import java.io.Serializable;

/**
 * @author panyox
 */
public class URL implements Serializable {

    private static final long serialVersionUID = -1985165475234910535L;

    protected String protocol;

    // by default, host to registry
    protected String host;

    // by default, port to registry
    protected int port;

    private transient String address;

    protected URL() {
        this.protocol = null;
        this.host = null;
        this.port = 0;
        this.address = null;
    }

    public URL(String host, int port) {
        this.protocol = null;
        this.host = host;
        this.port = port;
        this.address = getAddress(this.host, this.port);
    }

    public URL(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.address = getAddress(this.host, this.port);
    }

    public String getBackupAddress() {
        return getBackupAddress(0);
    }

    public String getBackupAddress(int defaultPort) {
        StringBuilder address = new StringBuilder(appendDefaultPort(getAddress(), defaultPort));
        return address.toString();
    }

    static String appendDefaultPort(String address, int defaultPort) {
        if (address != null && address.length() > 0 && defaultPort > 0) {
            int i = address.indexOf(':');
            if (i < 0) {
                return address + ":" + defaultPort;
            } else if (Integer.parseInt(address.substring(i + 1)) == 0) {
                return address.substring(0, i + 1) + defaultPort;
            }
        }
        return address;
    }

    public String getAddress() {
        if (address == null) {
            address = getAddress(host, port);
        }
        return address;
    }

    private static String getAddress(String host, int port) {
        return port <= 0 ? host : host + ':' + port;
    }
}

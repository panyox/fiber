package com.fiber.common.utils;

import com.fiber.common.constants.Constants;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author panyox
 */
public class StringUtils {

    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String getIP() {
        String hostIp = null;
        try {
            Enumeration<?> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface network = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration<?> addresses = network.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) addresses.nextElement();
                    String hostAddress = inetAddress.getHostAddress();
                    if (hostAddress.contains(".") && !inetAddress.isLoopbackAddress()) {
                        hostIp = hostAddress;
                        break;
                    }
                }
            }
            if (hostIp == null) {
                hostIp = InetAddress.getLocalHost().getHostAddress();
            }
        } catch (Exception ignore) {
            hostIp = "127.0.0.1";
        }
        return hostIp;
    }

    public static String clearPath(String str) {
        if (isEmpty(str)) {
            return str;
        }
        str = str.trim();
        if (str.startsWith(Constants.SLASH)) {
            str = str.substring(1);
        }
        if (str.endsWith(Constants.SLASH)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}

package com.revisoes.classesespecializadas.networking.networkinterface;

import static java.lang.System.out;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * The following example program lists all the network interfaces
 * and their addresses on a machine:
 */
public class ListNets {
    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());

        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress: %s\n", inetAddress);
        }

        out.printf("\n");
    }
}
// The following is sample output from the example program:

// Display name: TCP Loopback interface
// Name: lo
// InetAddress: /127.0.0.1

// Display name: Wireless Network Connection
// Name: eth0
// InetAddress: /192.0.2.0
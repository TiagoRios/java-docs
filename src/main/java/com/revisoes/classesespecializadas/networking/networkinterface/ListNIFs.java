package com.revisoes.classesespecializadas.networking.networkinterface;

import static java.lang.System.out;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * The following example program lists the name of all the network interfaces
 * and subinterfaces (if any exist) on a machine.
 */
public class ListNIFs {
  public static void main(String args[]) throws SocketException {
    Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

    for (NetworkInterface netIf : Collections.list(nets)) {
      out.printf("Display name: %s\n", netIf.getDisplayName());
      out.printf("Name: %s\n", netIf.getName());
      displaySubInterfaces(netIf);
      out.printf("\n");
    }
  }

  static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
    Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();

    for (NetworkInterface subIf : Collections.list(subIfs)) {
      out.printf("\tSub Interface Display name: %s\n", subIf.getDisplayName());
      out.printf("\tSub Interface Name: %s\n", subIf.getName());
    }
  }
}

// The following is sample output from the example program:

// Display name: bge0
// Name: bge0
// Sub Interface Display name: bge0:3
// Sub Interface Name: bge0:3
// Sub Interface Display name: bge0:2
// Sub Interface Name: bge0:2
// Sub Interface Display name: bge0:1
// Sub Interface Name: bge0:1

// Display name: lo0
// Name: lo0
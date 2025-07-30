package com.revisoes.classesespecializadas.networking.datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

// joinGroup(initeAdress) está depreciado. Código alterado.
public class MulticastClient {

  public static void main(String[] args) throws IOException {

    int port = 4446;
    MulticastSocket multicastSocket = new MulticastSocket(port);

    InetAddress address = InetAddress.getByName("230.0.0.1");
    InetSocketAddress socketAddress = new InetSocketAddress(address, port); // 230.0.0.1:4446
    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);

    multicastSocket.joinGroup(socketAddress, networkInterface);

    DatagramPacket packet;

    // get a few quotes
    for (int i = 0; i < 5; i++) {

      byte[] buf = new byte[256];
      packet = new DatagramPacket(buf, buf.length);
      multicastSocket.receive(packet);

      String received = new String(packet.getData(), 0, packet.getLength());
      System.out.println("Quote of the Moment: " + received);
    }

    multicastSocket.leaveGroup(socketAddress, networkInterface);
    multicastSocket.close();
  }

}
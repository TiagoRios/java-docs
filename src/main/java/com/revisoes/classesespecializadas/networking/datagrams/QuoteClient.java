package com.revisoes.classesespecializadas.networking.datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteClient {
  public static void main(String[] args) throws IOException {

    // if (args.length != 1) {
    // System.out.println("Usage: java QuoteClient <hostname>");
    // return;
    // }

    // para definir o pacote e seu tamanho
    byte[] buf = new byte[256];

    // get a datagram socket
    DatagramSocket datagramSocket = new DatagramSocket();

    // criação do pacote que será enviado
    InetAddress address = InetAddress.getByName("localhost");
    DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, 4445);

    // send request
    datagramSocket.send(datagramPacket);

    // get response
    datagramPacket = new DatagramPacket(buf, buf.length);
    datagramSocket.receive(datagramPacket);

    // display response
    String received = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
    System.out.println("Quote of the Moment: " + received);

    datagramSocket.close();
  }
}
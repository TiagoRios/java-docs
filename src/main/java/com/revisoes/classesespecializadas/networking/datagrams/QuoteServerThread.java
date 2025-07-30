package com.revisoes.classesespecializadas.networking.datagrams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class QuoteServerThread extends Thread {

  protected DatagramPacket datagramPacket = null;
  protected DatagramSocket datagramSocket = null;
  protected BufferedReader in = null;
  protected boolean moreQuotes = true;

  public QuoteServerThread() throws IOException {
    this("QuoteServerThread");
  }

  public QuoteServerThread(String name) throws IOException {
    super(name);
    datagramSocket = new DatagramSocket(4445);

    try {
      String path = "src/main/java/com/revisoes/classesespecializadas/networking/datagrams/";

      in = new BufferedReader(
          new FileReader(path + "one-liners.txt"));

    } catch (FileNotFoundException e) {
      System.err.println("Could not open quote file. Serving time instead.");
      System.out.println("Msg do sistema: " + e.getMessage());
    }
  }

  public void run() {

    while (moreQuotes) {
      try {
        byte[] buf = new byte[256];

        // receive request
        datagramPacket = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(datagramPacket);

        // figure out response
        String dString = null;

        if (in == null) {
          dString = new Date().toString();
        } else {
          dString = getNextQuote();
        }

        buf = dString.getBytes();

        // send the response to the client at "address" and "port"
        datagramPacket = new DatagramPacket(
            buf,
            buf.length,
            datagramPacket.getAddress(),
            datagramPacket.getPort());

        datagramSocket.send(datagramPacket);

      } catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }

    datagramSocket.close();
  }

  protected String getNextQuote() {
    String returnValue = null;

    try {
      if ((returnValue = in.readLine()) == null) { // Ler e já faz a verificação.
        in.close();
        moreQuotes = false;
        returnValue = "No more quotes. Goodbye.";
      }
    } catch (IOException e) {
      returnValue = "IOException occurred in server.";
    }

    return returnValue;
  }
}
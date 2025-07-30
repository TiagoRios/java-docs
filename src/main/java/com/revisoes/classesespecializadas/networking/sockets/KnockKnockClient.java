package com.revisoes.classesespecializadas.networking.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class KnockKnockClient {
  public static void main(String[] args) throws IOException {

    // if (args.length != 2) {
    // System.err.println(
    // "Usage: java EchoClient <host name> <port number>");
    // System.exit(1);
    // }

    String hostName = "localhost";
    int portNumber = 5555;

    // try with resource
    try (
        Socket kkSocket = new Socket(hostName, portNumber); // conecta-se com o serverSocket
        PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(kkSocket.getInputStream()));) {

      // Pega a entrada do usuário
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String fromUser;

      while ((fromServer = in.readLine()) != null) { // lê o fluxo de entrada lá do servidor
        System.out.println("Server: " + fromServer);

        if (fromServer.equals("Bye.")) {
          break;
        }

        fromUser = stdIn.readLine();

        if (fromUser != null) {
          System.out.println("Client: " + fromUser);
          out.println(fromUser); // coloca o que o usuário digitou no fluxo de saída do socket
        }
      }

    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1);

    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " +
          hostName);
      System.exit(1);
    }
  }
}
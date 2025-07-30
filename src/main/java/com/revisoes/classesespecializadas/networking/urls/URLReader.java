package com.revisoes.classesespecializadas.networking.urls;

import java.net.*;
import java.io.*;

public class URLReader {
  public static void main(String[] args) throws Exception {

    URL oracle = new URL("https://www.oracle.com/");
    BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      System.out.println(inputLine);
    }

    if ((in.readLine()) == null) {
      System.out.println("Objeto é nulo");
    }

    in.close();
  }
}
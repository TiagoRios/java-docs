package com.revisoes.basico.datahora.clock;

import java.time.Clock;
import java.time.LocalDateTime;

public class clockExample {
  public static void main(String[] args) {
    LocalDateTime l = LocalDateTime.now(Clock.systemUTC());
    System.out.println(l);
  }
}

package com.revisoes.basico.datahora.periodDuration;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PeriodDurationExamples {

  public static void main(String[] args) {
    new PeriodDurationExamples().runExample();
  }

  private void runExample() {
    Instant start = Instant.now();
    System.out.printf("duration: %s%n", start);

    Instant later = start.plus(Duration.ofSeconds(60));

    System.out.printf("duration: %s%n", later);

    Long gap = ChronoUnit.MILLIS.between(start, later);
    System.out.println("gap: " + gap);
  }
}

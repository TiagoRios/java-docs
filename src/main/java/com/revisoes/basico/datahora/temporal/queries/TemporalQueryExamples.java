package com.revisoes.basico.datahora.temporal.queries;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

public class TemporalQueryExamples {
  public static void main(String[] args) {
    TemporalQuery<TemporalUnit> query = TemporalQueries.precision();

    TemporalQueries.chronology();

    System.out.printf("LocalDate precision is %s%n",
        LocalDate.now().query(query));

    System.out.printf("LocalDateTime precision is %s%n",
        LocalDateTime.now().query(query));

    System.out.printf("Year precision is %s%n",
        Year.now().query(query));

    System.out.printf("YearMonth precision is %s%n",
        YearMonth.now().query(query));

    System.out.printf("Instant precision is %s%n",
        Instant.now().query(query));
  }
}

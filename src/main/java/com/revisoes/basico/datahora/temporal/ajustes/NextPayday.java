package com.revisoes.basico.datahora.temporal.ajustes;

/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * This example takes a month and day from the command line and,
 * assuming the current year, prints the next payday.
 * This example uses the PaydayAdjuster class.
 */
public class NextPayday {

  public static void main(String[] args) {
    LocalDate date = null;
    DateTimeFormatter format;
    Month month = null;
    String out = null;

    if (args.length < 2) {
      System.out.printf("Usage: NextPayday <month> <day>%n");
      throw new IllegalArgumentException();
    }

    // String inputMonth = "june";
    // String imputDay = "16";
    String inputMonth = args[0];
    String imputDay = args[1];

    try {
      month = Month.valueOf(inputMonth.toUpperCase());

    } catch (IllegalArgumentException exc) {
      System.out.printf("%s is not a valid month.%n", inputMonth);
      throw exc; // Rethrow the exception.
    }

    int day = Integer.parseInt(imputDay);

    try {
      date = Year.now().atMonth(month).atDay(day);

    } catch (DateTimeException exc) {
      System.out.printf("%s %s is not a valid date.%n", month, day);
      throw exc; // Rethrow the exception.
    }

    // LocalDate nextPayday = date.with(new PaydayAdjuster());
    /*
    Transformei em uma função lambda por que tava com problemas para compilar
    NextPayday.java:81: error: cannot find symbol
    LocalDate nextPayday = date.with(new PaydayAdjuster());
                                         ^
    symbol:   class PaydayAdjuster
    location: class NextPayday
    1 error
    error: compilation failed
    */
    LocalDate nextPayday = date.with((input) -> {
      LocalDate datee = LocalDate.from(input);
      int dayy;

      if (datee.getDayOfMonth() < 15) {
        dayy = 15;
      } else {
        dayy = datee.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
      }

      datee = datee.withDayOfMonth(dayy);

      if (datee.getDayOfWeek() == DayOfWeek.SATURDAY ||
          datee.getDayOfWeek() == DayOfWeek.SUNDAY) {
        datee = datee.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
      }

      return input.with(datee);
    });

    try {
      format = DateTimeFormatter.ofPattern("yyyy MMM d");
      out = date.format(format);
      System.out.printf("Given the date:  %s%n", out);

      out = nextPayday.format(format);
      System.out.printf("the next payday: %s%n", out);

    } catch (DateTimeException exc) {
      System.out.printf("%s can't be formatted!%n", out);
      throw exc;
    }

  }
}
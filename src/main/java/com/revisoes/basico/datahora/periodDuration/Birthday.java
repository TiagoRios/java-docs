package com.revisoes.basico.datahora.periodDuration;

/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * - Neither the name of Oracle or the names of its
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/*
 * Calculate the span of time from today until your birthday, assuming your
 * birthday occured on January 1st. The calculation is done using both
 * months and days (using Period) and days only (using ChronoUnit.between).
 */
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Birthday {

  private void proximoAniversario(int dia, int mes, int ano) {

    LocalDate today = LocalDate.now();
    LocalDate birthday = LocalDate.of(ano, mes, dia);

    LocalDate nextBDay = birthday.withYear(today.getYear());

    // If your birthday has occurred this year already, add 1 to the year.
    if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
      nextBDay = nextBDay.plusYears(1);
    }

    Period periodoProximoAniversario = Period.between(today, nextBDay);
    long diasProximoAniversario = ChronoUnit.DAYS.between(today, nextBDay);

    System.out.println("There are " + periodoProximoAniversario.getMonths() + " months, and " +
        periodoProximoAniversario.getDays() + " days until your next birthday. (" +
        diasProximoAniversario + " total)");
  }

  // The following code reports how old you are, assuming that you were born on
  // January 1, 1960. The Period class is used to determine the time in years,
  // months, and days. The same period, in total days, is determined by using the
  // ChronoUnit.between method and is displayed in parentheses:
  private void minhaIdade(int dia, int mes, int ano) {

    LocalDate today = LocalDate.now();
    LocalDate birthday = LocalDate.of(ano, mes, dia);

    Period idade = Period.between(birthday, today);

    long diasVividos = ChronoUnit.DAYS.between(birthday, today);

    String frase = "You are %s years, %s months, and %s days old. (%s days total)";
    System.out.printf(frase, idade.getYears(), idade.getMonths(), idade.getDays(), diasVividos);
  }

  public static void main(String[] args) {
    int dia = 1;
    int mes = 1;
    int ano = 1960;
    Birthday birthday = new Birthday();

    birthday.minhaIdade(dia, mes, ano);
    birthday.proximoAniversario(dia, mes, ano);
  }
}
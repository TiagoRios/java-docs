package com.revisoes.basico.datahora.temporal.queries;

import java.time.DateTimeException;
import java.time.LocalDate;
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
import java.time.Month;
import java.time.Year;

public class CheckDate {

    public static void main(String[] args) {
        Month month = null;
        LocalDate date = null;

        // if (args.length < 2) {
        // System.out.printf("Usage: CheckDate <month> <day>%n");
        // throw new IllegalArgumentException();
        // }

        String inputMonth = "April";
        String inputDay = "5";

        try {
            month = Month.valueOf(inputMonth.toUpperCase());
        } catch (IllegalArgumentException exc) {
            System.out.printf("%s is not a valid month.%n", inputMonth);
            throw exc; // Rethrow the exception.
        }

        int day = Integer.parseInt(inputDay);

        try {
            date = LocalDate.of(Year.now().getValue(), month, day);
        } catch (DateTimeException exc) {
            System.out.printf("%s %s is not a valid date.%n", month, day);
            throw exc; // Rethrow the exception.
        }

        // Invoking the query without using a lambda expression.
        Boolean isFamilyVacation = date.query(new FamilyVacations());

        // Invoking the query without using a lambda expression.
        // Copiado de FamilyVactions.java
        // Boolean isFamilyVacation = date.query((TemporalAccessor datee) -> {
        // int monthh = datee.get(ChronoField.MONTH_OF_YEAR);
        // int dayy = datee.get(ChronoField.DAY_OF_MONTH);

        // // Disneyland over Spring Break
        // if ((monthh == Month.APRIL.getValue()) && ((dayy >= 3) && (dayy <= 8)))
        // return Boolean.TRUE;

        // // Smith family reunion on Lake Saugatuck
        // if ((monthh == Month.AUGUST.getValue()) && ((dayy >= 8) && (dayy <= 14)))
        // return Boolean.TRUE;

        // return Boolean.FALSE;
        // });

        // Invoking the query using a lambda expression. Referência de método
        Boolean isFamilyBirthday = date.query(FamilyBirthdays::isFamilyBirthday);

        if (isFamilyVacation.booleanValue() || isFamilyBirthday.booleanValue())
            System.out.printf("%s is an important date!%n", date);
        else
            System.out.printf("%s is not an important date.%n", date);
    }
}
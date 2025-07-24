package com.revisoes.basico.datahora.timezoneOffset;

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
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TimeZoneId {

  public static void main(String[] args) {
    TimeZoneId timeZoneId = new TimeZoneId();

    timeZoneId.listarFusoHorariosHorasQuebradas();

    timeZoneId.escreverTodosFusoHorariosNoArquivo();
  }

  private final String LOCAL_SALVAMENTO_ARQUIVO = "src/main/java/com/revisoes/basico/datahora";
  private final String NOME_ARQUIVO = "timeZones";

  // caminho relativo
  private final Path CAMINHO_COM_NOME_ARQUIVO = Paths.get(LOCAL_SALVAMENTO_ARQUIVO, NOME_ARQUIVO);

  // String diretorioProjeto = System.getProperty("user.dir");

  /*
   * verify time zones that do not have a whole hour offset
   * to standard out.
   */
  private boolean isHoraCompleta(ZoneOffset zoneOffset) {
    int secondsOfHour = zoneOffset.getTotalSeconds() % (60 * 60);
    return secondsOfHour != 0;
  }

  private List<String> sortedZoneList() {

    // Get the set of all time zone IDs.
    Set<String> allZones = ZoneId.getAvailableZoneIds();

    // Create a List using the set of zones and sort it.
    List<String> zoneList = new ArrayList<String>(allZones);

    Collections.sort(zoneList);

    return zoneList;
  }

  private void listarFusoHorariosHorasQuebradas() {

    for (String zona : sortedZoneList()) {
      ZoneId zoneId = ZoneId.of(zona);
      ZonedDateTime dataHoraComFusoHorario = LocalDateTime.now().atZone(zoneId);
      ZoneOffset zoneOffset = dataHoraComFusoHorario.getOffset();

      String resultadoFormatado = String.format("%35s %10s", zoneId, zoneOffset);

      if (isHoraCompleta(zoneOffset)) {
        System.out.println(resultadoFormatado);
      }
    }
  }

  private void escreverTodosFusoHorariosNoArquivo() {

    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(CAMINHO_COM_NOME_ARQUIVO,
        StandardCharsets.US_ASCII)) {

      for (String zona : this.sortedZoneList()) {
        ZoneId zoneId = ZoneId.of(zona);
        ZonedDateTime dataHoraComFusoHorario = LocalDateTime.now().atZone(zoneId);
        ZoneOffset zoneOffset = dataHoraComFusoHorario.getOffset();

        String resultadoFormatado = String.format("%35s %10s%n", zoneId, zoneOffset);

        // Write all time zones to the file.
        bufferedWriter.write(resultadoFormatado);
      }

    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }
}

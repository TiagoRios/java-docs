package com.revisoes.basico.aprendendo.anotacoes;

import java.lang.annotation.Documented;

@Documented
public @interface Autores {
    Autor[] value();
}

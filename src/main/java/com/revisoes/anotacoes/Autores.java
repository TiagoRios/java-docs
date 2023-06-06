package com.revisoes.anotacoes;

import java.lang.annotation.Documented;

@Documented
public @interface Autores {
    Autor[] value();
}

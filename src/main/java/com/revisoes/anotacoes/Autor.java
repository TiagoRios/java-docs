package com.revisoes.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;

@Documented
@Repeatable(Autores.class)
public @interface Autor {
    String value();
    String email() default "";
}

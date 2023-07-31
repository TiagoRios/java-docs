package com.revisoes.basico.aprendendo.genericos;

/**
 * Interface somente para marcar as restriçoes.
 * 
 * <h2>Restrições aos Genéricos</h2>
 * Para usar os genéricos Java de forma eficaz, você deve considerar as
 * seguintes restrições:
 * <ol>
 * <li>Não é possível instanciar tipos genéricos com tipos primitivos
 * <li>Não é possível criar instâncias de parâmetros de tipo
 * <li>Não é possível declarar campos estáticos cujos tipos são parâmetros de
 * tipo
 * <li>Não é possível usar Casts ou instanceof com tipos parametrizados
 * <li>Não é possível criar matrizes de tipos parametrizados
 * <li>Não é possível criar, capturar ou lançar objetos de tipos parametrizados
 * <li>Não é possível sobrecarregar um método em que os tipos de parâmetros
 * formais
 * de cada sobrecarga são apagados para o mesmo tipo bruto
 * </ol>
 */
public interface RestricoesGenerics {
    public static void imprimirRestricoes() {
        System.out.printf("%n%nRestrições em genéricos:%n%n%s %n%s %n%s %n%s %n%s %n%s %n%s%n%n",
                "* Não é possível instanciar tipos genéricos com tipos primitivos",
                "* Não é possível criar instâncias de parâmetros de tipo",
                "* Não é possível declarar campos estáticos cujos tipos são parâmetros de tipo",
                "* Não é possível usar Casts ou instanceof com tipos parametrizados",
                "* Não é possível criar matrizes de tipos parametrizados",
                "* Não é possível criar, capturar ou lançar objetos de tipos parametrizados",
                "* Não é possível sobrecarregar um método em que os tipos de parâmetros formais de cada sobrecarga são apagados para o mesmo tipo bruto");
    }
}

package com.revisoes.basico.classesessenciais.io.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.revisoes.basico.classesessenciais.io.MyPath;

public class UsoPaths {
    public static void main(String[] args) throws IOException {
        Path pathIO = Paths.get(MyPath.IO_PATH);
        // Paths.get() é um atalho para o código abaixo.
        FileSystems.getDefault().getPath("meu/caminho");

        /*
         * ============================================================
         * -------------- obter informações do caminho
         * ============================================================
         */

        // None of these methods requires that the file corresponding
        // to the Path exists.
        Path path = Paths.get("C:\\home\\joe\\foo");

        System.out.format("toString: %s%n", path.toString()); // c\home\joe\foo
        System.out.format("getFileName: %s%n", path.getFileName()); // foo
        System.out.format("getName(0): %s%n", path.getName(0)); // home
        System.out.format("getNameCount: %d%n", path.getNameCount()); // 3
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2)); // home\joe
        System.out.format("getParent: %s%n", path.getParent()); // c\home\joe
        System.out.format("getRoot: %s%n", path.getRoot()); // c:\

        /*
         * ============================================================
         * ----------------- Convertendo um caminho
         * ============================================================
         */

        System.out.format("%s%n%n", pathIO.toUri()); // file:///c:/+caminho até o arquivo.
        System.out.format("%s%n%n", pathIO.toAbsolutePath()); // c:/+caminho até o arquivo.
        System.out.format("%s%n%n", pathIO.toRealPath()); // c:/+caminho até o arquivo.

        /*
         * ============================================================
         * ----------------- Unindo dois caminhos
         * ============================================================
         */

        System.out.format("%s%n%n", pathIO.resolve(MyPath.XANADU));
        // results is src/main/java/com/revisoes/io/xanadu.txt

        /*
         * ============================================================
         * ---------- Criando um caminho entre dois caminhos
         * ============================================================
         */

        Path p1 = Paths.get("joe");
        Path p2 = Paths.get("sally");

        System.out.format("%s%n%n", p1.relativize(p2)); // Result is ../sally
        System.out.format("%s%n%n", p2.relativize(p1)); // Result is ../joe

        // Consider a slightly more complicated example:

        // os dois caminhos compartilham o mesmo nó. "home"
        // o caminho relativo não pode ser construido se apenas um deles incluir
        // o elemento raiz.
        // mesmo que os dois compartilharem o nó, a capacidade de construir um caminho
        // relativo dependerá do sistema.
        Path p = Paths.get("home");
        Path p3 = Paths.get("home/sally/bar");

        System.out.format("%s%n%n", p.relativize(p3)); // Result is sally/bar
        System.out.format("%s%n%n", p3.relativize(p)); // Result is ../..

        /*
         * ============================================================
         * ----------------- Comparando dois caminhos
         * ============================================================
         */

        System.out.format("%s%n%n", pathIO.startsWith("src")); // true
        System.out.format("%s%n%n", pathIO.endsWith("io")); // true
        System.out.format("%s%n%n", pathIO.equals(p3)); // false

    }
}

package com.revisoes.io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.revisoes.io.MyPath;

/**
 * Todos os métodos da classe {@link Files} são estáticos.
 * 
 * Métodos intuitivos, são fáceis de entender.
 */
public class UsoFiles {

    private static final String NOME_NOVO_DIRETORIO = "meu-diretorio/";
    private static final String NOME_ARQUIVO_COPIADO = "xanadu_copy.txt";

    static Path sourcePath = Paths.get(MyPath.IO_PATH, MyPath.XANADU);
    static Path targetPath = Paths.get(MyPath.IO_PATH, NOME_ARQUIVO_COPIADO);
    static Path pathNovoDiretorio = Paths.get(MyPath.IO_PATH, NOME_NOVO_DIRETORIO);

    /**
     * faz verificações se o caminho é um diretório ou é um arquivo.
     * @param path caminho que será analisado.
     */
    public void imprimeSeEhArquivoOuDiretorio(Path path) {
        if (Files.isRegularFile(path))
            System.out.println("Eh um arquivo regular chamado: " + path.getFileName());
        else
            System.out.println("Eh um Diretorio!");
    }

    /**
     * Cria novo diretório caso ele não exista.
     * 
     * @param pathDiretorio
     * @throws IOException se diretório já existe.
     */
    public void criaDiretorioOuArquivo(Path pathDiretorio) throws IOException {
        if (Files.notExists(pathDiretorio)) {
            Files.createDirectory(pathDiretorio);
        }
    }

    /**
     * Copia um arquivo para um arquivo alvo.
     * 
     * @param sourcePath
     * @param targetPath
     * @param nomeNovoArquivo
     * @return O arquivo com o cominho que foi criado.
     * @throws IOException
     */
    public Path copiarDiretorioOuArquivo(Path sourcePath, Path targetPath) throws IOException {
        criaDiretorioOuArquivo(targetPath);
        // Diretórios podem ser copiados, mas os arquivos dentro dela não são.
        Path path = Files.copy(sourcePath, targetPath,
                StandardCopyOption.REPLACE_EXISTING, LinkOption.NOFOLLOW_LINKS);

        System.out.format("Copiado com sucesso!%n");
        return path;
    }

    /**
     * Move um arquivo ou renomeia o arquivo.
     * 
     * @param sourcePath caminho do arquivo para mover
     * @param targetPath destino para mover
     * @return O caminho do arquivo que movido.
     * @throws IOException
     */
    public Path moverDiretorioOuArquivo(Path sourcePath, Path targetPath) throws IOException {
        criaDiretorioOuArquivo(targetPath);
        Path path = Files.move(sourcePath, Paths.get(targetPath.toString() + "/" + sourcePath.getFileName().toString()),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.format("Movido com sucesso!%n");
        return path;
    }

    /**
     * Excluir um arquivo ou diretório.
     * 
     * @param path caminho do diretório ou arquivo para excluir.
     * @return true se for excluído ou false se não existir ou não puder ser
     *         excluído.
     * @throws IOException
     */
    public boolean excluirDiretorioOuArquivo(Path path) throws IOException {
        // Files.delete(targetPath); // throws NoSuchFileException
        boolean excluido = Files.deleteIfExists(path); // No throws. silencioso.
        System.out.format("excluído com sucesso!%n");
        return excluido;
    }

    public static void main(String[] args) throws IOException {
        UsoFiles usoFiles = new UsoFiles();

        // verificar
        usoFiles.imprimeSeEhArquivoOuDiretorio(sourcePath);

        // Copiar
        Path arquivoCopiado = usoFiles.copiarDiretorioOuArquivo(UsoFiles.sourcePath, targetPath);

        // Mover
        Path arquivoMovido = usoFiles.moverDiretorioOuArquivo(arquivoCopiado, pathNovoDiretorio);

        // Excluir
        usoFiles.excluirDiretorioOuArquivo(arquivoMovido);
        usoFiles.excluirDiretorioOuArquivo(UsoFiles.pathNovoDiretorio); // arquivos devem ser excluídos antes.
    }
}
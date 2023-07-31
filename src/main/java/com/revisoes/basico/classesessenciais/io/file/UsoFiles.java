package com.revisoes.basico.classesessenciais.io.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import com.revisoes.basico.classesessenciais.io.MyPath;

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

    /*
     * ============================================================
     * ------- Operações Verificar, copiar, mover e excluir
     * ============================================================
     */

    /**
     * faz verificações se o caminho é um diretório ou é um arquivo.
     * 
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

    /*
     * ============================================================
     * -------- Gerenciando Metadados Files.readAttributes()
     * ============================================================
     */

    /**
     * Imprime algumas informações do arquivos ussando operações em massa.
     * 
     * Utiliza BasicFileAttributes.
     * 
     * @param sourcePath
     * @throws IOException
     * 
     * @see {@link Files#readAttributes(Path, String, LinkOption...)}
     */
    public void imprimeInformacoesArquivo_basicFileAttributes(Path sourcePath) throws IOException {
        // Files.readAttributes() lê todos os atributos básicos usando operação em
        // massa.
        BasicFileAttributes basicFileAttributes = Files.readAttributes(sourcePath, BasicFileAttributes.class);

        System.out.format("%n----- BasicFileAttributes %n");
        System.out.format("isDirectory: %s%n", basicFileAttributes.isDirectory());
        System.out.format("creationTime: %s%n", basicFileAttributes.creationTime().toString());
        System.out.format("isRegularFile: %s%n%n", basicFileAttributes.isRegularFile());
    }

    /**
     * Imprime algumas informações do arquivos ussando operações em massa.
     * 
     * Utiliza BasicFileAttributes.
     * 
     * @param sourcePath
     * @throws IOException
     *
     * @see {@link Files#readAttributes(Path, String, LinkOption...)
     */
    public void imprimeInformacoesArquivo_dosFileAttributes(Path sourcePath) throws IOException {
        DosFileAttributes dosFileAttributes = Files.readAttributes(sourcePath, DosFileAttributes.class);

        System.out.format("%n----- DosFileAttributes %n");
        System.out.format("isDirectory: %s%n", dosFileAttributes.isDirectory());
        System.out.format("creationTime: %s%n", dosFileAttributes.creationTime().toString());
        System.out.format("isRegularFile: %s%n%n", dosFileAttributes.isRegularFile());
    }

    /*
     * ============================================================
     * ---- Gerenciando Metadados UserDefinedFileAttributeView
     * ============================================================
     */

    /**
     * Definir atributos do arquivo.
     * 
     * @param path          Arquivo para ser definido atributos.
     * @param nomeAtributo  chave do atributo.
     * @param valorAtributo valor do atributo.
     * @throws IOException
     */
    public void definirAtribuitosNoArquivo(Path path, String nomeAtributo, String valorAtributo) throws IOException {
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(path,
                UserDefinedFileAttributeView.class);

        userView.write(nomeAtributo, Charset.defaultCharset().encode(valorAtributo));
    }

    /**
     * Ler atributos que foram definidos no arquivo pelo usuário.
     * 
     * @param path
     * @param nomeAtributo
     * @throws IOException
     */
    public void lerAtribuitoDefinidoNoArquivo(Path path, String nomeAtributo) throws IOException {
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(path,
                UserDefinedFileAttributeView.class);

        ByteBuffer buf = ByteBuffer.allocate(userView.size(nomeAtributo));

        userView.read(nomeAtributo, buf);
        buf.flip();
        String value = Charset.defaultCharset().decode(buf).toString();

        System.out.format("Valor do atributo definido pelo usuário: %s%n", value);
    }

    /*
     * ============================================================
     * -------------- Listar informações do disco.
     * ============================================================
     */

    /**
     * Lista as unidades do disco rígido.
     * 
     * @param sourcePath
     * @throws IOException
     */
    public void listarInformacoesDisco(Path sourcePath) throws IOException {
        FileStore fileStore = Files.getFileStore(sourcePath); // C:
        System.out.println("Armazenamento Total: " + fileStore.getTotalSpace() / 1024 / 1024 / 1024 + " GB");

        // Lista todas as unidades do computador. "C:", "D:"
        FileSystems.getDefault().getFileStores().forEach(System.out::println);
    }

    /*
     * ============================================================
     * ----------------------- Ler arquivo
     * ============================================================
     */

    /**
     * Imprimir conteúdo dentro de um arquivo.
     * 
     * @param sourcePath
     * @throws IOException
     */
    public void imprimeConteudoDeDentroArquivo(Path sourcePath) throws IOException {
        // usado para arquivos de texto.
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(sourcePath, charset)) {
            String line = null;
            System.out.println();
            while ((line = reader.readLine()) != null) {
                System.out.println("# " + line);
            }
            System.out.println();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static void main(String[] args) throws IOException {
        UsoFiles usoFiles = new UsoFiles();

        /*
         * ============================================================
         * ------- Operações Verificar, copiar, mover e excluir
         * ============================================================
         */

        // verificar
        usoFiles.imprimeSeEhArquivoOuDiretorio(sourcePath);

        // Copiar
        Path arquivoCopiado = usoFiles.copiarDiretorioOuArquivo(UsoFiles.sourcePath, targetPath);

        // Mover
        Path arquivoMovido = usoFiles.moverDiretorioOuArquivo(arquivoCopiado, pathNovoDiretorio);

        // Excluir
        usoFiles.excluirDiretorioOuArquivo(arquivoMovido);
        usoFiles.excluirDiretorioOuArquivo(UsoFiles.pathNovoDiretorio); // arquivos devem ser excluídos antes.

        /*
         * ============================================================
         * -------- Gerenciando Metadados Files.readAttributes()
         * ============================================================
         */

        // Verificar operações em massa Files.readAttributes()
        usoFiles.imprimeInformacoesArquivo_basicFileAttributes(sourcePath);
        usoFiles.imprimeInformacoesArquivo_dosFileAttributes(sourcePath);

        /*
         * ============================================================
         * ---- Gerenciando Metadados UserDefinedFileAttributeView
         * ============================================================
         */

        final String CHAVE = "user.mimetype";
        final String VALOR = "text/html";

        final String OUTRA_CHAVE = "outraChave";
        final String OUTRO_VALOR = "outro/valor";

        usoFiles.definirAtribuitosNoArquivo(sourcePath, CHAVE, VALOR);
        usoFiles.definirAtribuitosNoArquivo(sourcePath, OUTRA_CHAVE, OUTRO_VALOR);

        usoFiles.lerAtribuitoDefinidoNoArquivo(sourcePath, CHAVE);
        usoFiles.lerAtribuitoDefinidoNoArquivo(sourcePath, OUTRA_CHAVE);

        /*
         * ============================================================
         * -------------- Listar informações do disco.
         * ============================================================
         */

        usoFiles.listarInformacoesDisco(arquivoMovido);

        /*
         * ============================================================
         * ----------------------- Ler arquivo
         * ============================================================
         */

        usoFiles.imprimeConteudoDeDentroArquivo(arquivoMovido);

    }
}
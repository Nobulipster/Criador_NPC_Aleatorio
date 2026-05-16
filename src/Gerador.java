import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Gerador {
    public static void main(String[] args) throws Exception {
        List<String> dadosAparencia = carregarArquivo("/tabelas/aparencia.txt");
        List<String> dadosHabAlta = carregarArquivo("/tabelas/habilidades/habilidadeAlta.txt");

        if (dadosAparencia == null ||
            dadosHabAlta == null)  {
                System.out.println("Houve erro de leitura, programa encerrado....");
                return;
        }

        Tabela tabelaAparencia = new Tabela(dadosAparencia);
        Tabela tabelaHabAlta = new Tabela(dadosHabAlta);

        System.out.println(tabelaAparencia.rolar());
        System.out.println(tabelaHabAlta.rolar());

    }

    public static List<String> carregarArquivo(String caminhoDoArquivo) {
        InputStream fluxoDeDados = Gerador.class.getResourceAsStream(caminhoDoArquivo);

        if (fluxoDeDados == null) {
            System.out.println("Erro: Arquivo não encontrado -> " + caminhoDoArquivo);
            return null;
        }

        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(fluxoDeDados, StandardCharsets.UTF_8))) {
            return leitor.lines().collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gerador {
    public static NPC criarNpcAleatorio(String nome) {
        List<String> dadosAparencia = carregarArquivo("/tabelas/aparencia.txt");
        List<String> dadosOrigem = carregarArquivo("/tabelas/origens/origem.txt");
        List<String> dadosHabAlta = carregarArquivo("/tabelas/habilidades/habilidadeAlta.txt");
        List<String> dadosHabBaixa = carregarArquivo("/tabelas/habilidades/habilidadeBaixa.txt");
        List<String> dadosDom = carregarArquivo("/tabelas/dom.txt");
        List<String> dadosManeirismo = carregarArquivo("/tabelas/maneirismo.txt");
        List<String> dadosTracos = carregarArquivo("/tabelas/tracoInterecao.txt");
        List<String> dadosAlinhamento = carregarArquivo("/tabelas/ideais/alinhamento.txt");
        List<String> dadosVinculos = carregarArquivo("/tabelas/vinculos.txt");
        List<String> dadosSegredos = carregarArquivo("/tabelas/segredos.txt");

        if (dadosAparencia == null || dadosHabAlta == null || dadosHabBaixa == null ||
            dadosDom == null || dadosManeirismo == null || dadosTracos == null ||
            dadosAlinhamento == null || dadosVinculos == null || dadosSegredos == null) {
            System.out.println("Houve erro de leitura em uma das tabelas base. Cancelando...");
            return null;
        }

        String aparencia = new Tabela(dadosAparencia).rolar();
        String origem = new Tabela(dadosOrigem).rolar();
        String habAlta = new Tabela(dadosHabAlta).rolar();
        String habBaixa = new Tabela(dadosHabBaixa).rolar();
        String dom = new Tabela(dadosDom).rolar();
        String maneirismo = new Tabela(dadosManeirismo).rolar();
        String tracos = new Tabela(dadosTracos).rolar();
        String vinculo = new Tabela(dadosVinculos).rolar();
        String segredo = new Tabela(dadosSegredos).rolar();

        String alinhamentoSorteado = new Tabela(dadosAlinhamento).rolar();
        List<String> tabelasElegiveis = new ArrayList<>();
        tabelasElegiveis.add("/tabelas/ideais/outrosIdeais.txt"); 

        if (alinhamentoSorteado.contains("Leal")) tabelasElegiveis.add("/tabelas/ideais/idealLeal.txt");
        if (alinhamentoSorteado.contains("Caótico")) tabelasElegiveis.add("/tabelas/ideais/idealCaotico.txt");
        if (alinhamentoSorteado.contains("Bom")) tabelasElegiveis.add("/tabelas/ideais/idealBom.txt");
        if (alinhamentoSorteado.contains("Mau")) tabelasElegiveis.add("/tabelas/ideais/idealMau.txt");
        if (alinhamentoSorteado.equals("Neutro")) tabelasElegiveis.add("/tabelas/ideais/idealNeutro.txt");

        Tabela seletorDeTabela = new Tabela(tabelasElegiveis);
        String caminhoTabelaEscolhida = seletorDeTabela.rolar();

        List<String> dadosIdealEscolhido = carregarArquivo(caminhoTabelaEscolhida);
        String idealSorteado = (dadosIdealEscolhido != null) ? new Tabela(dadosIdealEscolhido).rolar() : "Desconhecido";
        String idealCompleto = alinhamentoSorteado + " (" + idealSorteado + ")";

        return new NPC(nome, origem, aparencia, habAlta, habBaixa, dom, maneirismo, tracos, idealCompleto, vinculo, segredo);
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

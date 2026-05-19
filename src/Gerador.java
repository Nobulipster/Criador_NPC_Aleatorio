import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

        Random rand = new Random();

        String aparencia = new Tabela(dadosAparencia).rolar();
        String origem = new Tabela(dadosOrigem).rolar();

        // MELHORIA 2: Garantir que Habilidade Alta e Baixa não sejam o mesmo atributo
        Tabela tabelaAlta = new Tabela(dadosHabAlta);
        Tabela tabelaBaixa = new Tabela(dadosHabBaixa);
        int idxAlta = rand.nextInt(tabelaAlta.getTamanho());
        int idxBaixa = rand.nextInt(tabelaBaixa.getTamanho());
        while (idxAlta == idxBaixa) {
            idxBaixa = rand.nextInt(tabelaBaixa.getTamanho());
        }
        String habAlta = tabelaAlta.obterItem(idxAlta);
        String habBaixa = tabelaBaixa.obterItem(idxBaixa);

        String dom = new Tabela(dadosDom).rolar();
        String maneirismo = new Tabela(dadosManeirismo).rolar();
        String tracos = new Tabela(dadosTracos).rolar();
        String segredo = new Tabela(dadosSegredos).rolar();

        // MELHORIA 1: Tratar a Tabela de Vínculo (Tratar a linha 10 / índice 9)
        Tabela tabelaVinculos = new Tabela(dadosVinculos);
        String vinculo;
        int idxVinculo = rand.nextInt(tabelaVinculos.getTamanho());
        
        if (idxVinculo == 9) {
            int v1 = rand.nextInt(9);
            int v2 = rand.nextInt(9);
            while (v1 == v2) {
                v2 = rand.nextInt(9);
            }
            vinculo = "Duplo Vínculo: [" + tabelaVinculos.obterItem(v1) + "] E [" + tabelaVinculos.obterItem(v2) + "]";
        } else {
            vinculo = tabelaVinculos.obterItem(idxVinculo);
        }

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
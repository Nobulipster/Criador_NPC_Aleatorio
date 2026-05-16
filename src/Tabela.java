import java.util.List;
import java.util.Random;

public class Tabela {
    private List<String> opcoes;
    private Random gerador;

    public Tabela(List<String> opcoes) {
        this.opcoes = opcoes;
        this.gerador = new Random();
    }

    public String rolar() {
        int indiceAleatorio = gerador.nextInt(opcoes.size());
        return opcoes.get(indiceAleatorio);
    }
}

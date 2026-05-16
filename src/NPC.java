public class NPC {
    private String nome;
    private String origem;
    private String aparencia;
    private String habilidadeAlta;
    private String habilidadeBaixa;
    private String dom;
    private String maneirismo;
    private String tracosInteracao;
    private String ideal;
    private String vinculo;
    private String segredo;

    public NPC(String nome, String origem, String aparencia, String habilidadeAlta, String habilidadeBaixa, String dom, String maneirismo, String tracosInteracao, String ideal, String vinculo, String segredo) {
        this.nome = nome;
        this.origem = origem;
        this.aparencia = aparencia;
        this.habilidadeAlta = habilidadeAlta;
        this.habilidadeBaixa = habilidadeBaixa;
        this.dom = dom;
        this.maneirismo = maneirismo;
        this.tracosInteracao = tracosInteracao;
        this.ideal = ideal;
        this.vinculo = vinculo;
        this.segredo = segredo;
    }

    public String getNome() {
        return nome;
    }

    public String getOrigem() {
        return origem;
    }

    public String getAparencia() {
        return aparencia;
    }

    public String getHabilidadeAlta() {
        return habilidadeAlta;
    }
    
    public String getHabilidadeBaixa() {
        return habilidadeBaixa;
    }

    public String getDom() {
        return dom;
    }

    public String getManeirismo() {
        return maneirismo;
    }

    public String getTracosInteracao() {
        return tracosInteracao;
    }

    public String getIdeal() {
        return ideal;
    }

    public String getVinculo() {
        return vinculo;
    }

    public String getSegredo() {
        return segredo;
    }
}

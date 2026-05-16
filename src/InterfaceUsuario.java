import java.util.Scanner;

public class InterfaceUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("      GERADOR ALEATÓRIO DE NPC           ");
        System.out.println("=========================================");
        
        System.out.print("Digite o nome do NPC: ");
        String nome = scanner.nextLine();

        System.out.println("\nGerando características...");
        NPC npcGerado = Gerador.criarNpcAleatorio(nome);

        if (npcGerado != null) {
            exibirFichaNpc(npcGerado);
        }

        scanner.close();
    }

    public static void exibirFichaNpc(NPC npc) {
        System.out.println("\n==================================================");
        System.out.println("FICHA DE PERSONAGEM (NPC)");
        System.out.println("==================================================");
        System.out.printf("Nome: %s\n", npc.getNome());
        System.out.println("--------------------------------------------------");
        System.out.printf("Aparência:          %s\n", npc.getAparencia());
        System.out.printf("Origem:             %s\n", npc.getOrigem());
        System.out.printf("Habilidade Alta:    %s\n", npc.getHabilidadeAlta());
        System.out.printf("Habilidade Baixa:   %s\n", npc.getHabilidadeBaixa());
        System.out.printf("Talento/Dom:        %s\n", npc.getDom());
        System.out.printf("Maneirismo:         %s\n", npc.getManeirismo());
        System.out.printf("Traço de Interação: %s\n", npc.getTracosInteracao());
        System.out.printf("Ideal/Alinhamento:  %s\n", npc.getIdeal());
        System.out.printf("Vínculo:            %s\n", npc.getVinculo());
        System.out.printf("Segredo/Defeito:    %s\n", npc.getSegredo());
        System.out.println("==================================================");
    }
}
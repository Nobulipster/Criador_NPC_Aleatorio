import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InterfaceUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("      GERADOR ALEATÓRIO DE NPC           ");
        System.out.println("=========================================");
        
        System.out.print("Digite o nome do NPC: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            nome = "NPC_Sem_Nome";
        }

        System.out.println("\nGerando características...");
        NPC npcGerado = Gerador.criarNpcAleatorio(nome);

        if (npcGerado != null) {
            exibirFichaNpc(npcGerado);
            salvarFichaEmArquivo(npcGerado);
        }

        scanner.close();
    }

    public static void exibirFichaNpc(NPC npc) {
        System.out.println("\n==================================================");
        System.out.println("FICHA DE PERSONAGEM (NPC) - IMPRESSÃO CONSOLE");
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

    // MELHORIA 4: Gravar a ficha gerada num arquivo de texto externo (.txt)
    public static void salvarFichaEmArquivo(NPC npc) {
        String nomeArquivo = "historico_npcs.txt";

        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(nomeArquivo, true);
             java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter escritor = new BufferedWriter(osw)) {
            
            escritor.write("==================================================\n");
            escritor.write("             FICHA DO PERSONAGEM (NPC)            \n");
            escritor.write("==================================================\n");
            escritor.write("Nome: " + npc.getNome() + "\n");
            escritor.write("Origem: " + npc.getOrigem() + "\n");
            escritor.write("--------------------------------------------------\n");
            escritor.write("Aparência:          " + npc.getAparencia() + "\n");
            escritor.write("Habilidade Alta:    " + npc.getHabilidadeAlta() + "\n");
            escritor.write("Habilidade Baixa:   " + npc.getHabilidadeBaixa() + "\n");
            escritor.write("Talento/Dom:        " + npc.getDom() + "\n");
            escritor.write("Maneirismo:         " + npc.getManeirismo() + "\n");
            escritor.write("Traço de Interação: " + npc.getTracosInteracao() + "\n");
            escritor.write("Ideal/Alinhamento:  " + npc.getIdeal() + "\n");
            escritor.write("Vínculo:            " + npc.getVinculo() + "\n");
            escritor.write("Segredo/Defeito:    " + npc.getSegredo() + "\n");
            escritor.write("==================================================\n\n");
            
            System.out.println("\nFicha adicionada com sucesso ao histórico: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao tentar salvar o ficheiro da ficha: " + e.getMessage());
        }
    }
}
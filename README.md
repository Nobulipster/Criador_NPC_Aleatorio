## 📜 Projeto de Criação Aletaória de NPCs de RPG

O objetivo deste projeto é aprimorar o aprendizado em Java através da automação da criação de Non-Player Characters (NPCs) para a Campanha de RPG chamada `Yankee Life`. O software se baseia no sistema de jogo chamado `D&D 5e.` e utiliza as regras do `Guia do Mestre` para otimizar uma tarefa que, manualmente, demanda muito tempo com a verificação de mais de 8 tabelas descritivas.

### 📝 Customização e Expansão de Tabelas

Uma das principais vantagens da arquitetura deste sistema é a separação total entre a lógica de programação (Java) e os dados textuais. Isso significa que **todas as tabelas de sorteio podem ser livremente alteradas, expandidas ou modificadas sem a necessidade de alterar ou recompilar o código-fonte**.

#### Como Modificar os Dados:
1. Navegue até a pasta de recursos do projeto: `src/tabelas/`.
2. Abra o arquivo `.txt` correspondente à característica que deseja alterar (ex: `origem.txt` ou `aparencia.txt`).
3. **Para editar ou remover:** Altere o texto diretamente na linha correspondente.
4. **Para expandir:** Adicione novas opções simplesmente criando novas linhas no final do arquivo. O motor do gerador recalculará o tamanho da tabela e a probabilidade do dado automaticamente na próxima execução.

*Nota: Mantenha sempre o padrão de uma opção por linha dentro dos arquivos de texto para garantir a leitura correta pelo sistema.*
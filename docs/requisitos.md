# Requisitos Funcionais

- RF01: O sistema deve permitir ao usuário iniciar uma nova partida a partir da tela inicial.
- RF02: O sistema deve inicializar uma partida criando um tabuleiro 4×4 com dois blocos posicionados aleatoriamente.
- RF03: O sistema deve permitir que o jogador movimente os blocos para cima, baixo, esquerda e direita.
- RF04: O sistema deve unir automaticamente dois blocos de mesmo valor quando ocorrer uma colisão após um movimento válido.
- RF05: O sistema deve adicionar automaticamente um novo bloco ao tabuleiro após cada movimento válido realizado pelo jogador.
- RF06: O sistema deve atualizar a pontuação sempre que dois blocos forem unidos.
- RF07: O sistema deve permitir ao jogador desfazer o último movimento realizado.
- RF08: O sistema deve verificar continuamente se o jogador atingiu um bloco de valor 2048.
- RF09: O sistema deve exibir a tela de vitória quando o jogador alcançar um bloco de valor 2048.
- RF10: O sistema deve verificar quando não existirem mais movimentos possíveis.
- RF11: O sistema deve exibir a tela de derrota quando não houver mais movimentos possíveis.
- RF12: O sistema deve permitir ao jogador iniciar uma nova partida após uma vitória ou derrota.
- RF13: O sistema deve permitir ao jogador retornar à tela inicial ao finalizar uma partida.


# Requisitos Não Funcionais

- RNF01: O aplicativo deve ser desenvolvido utilizando a linguagem Kotlin.
- RNF02: A interface gráfica deve ser implementada utilizando Jetpack Compose.
- RNF03: O aplicativo deve responder às ações do usuário de forma fluida durante a partida.
- RNF04: O gerenciamento de estado deve manter a interface sincronizada com os dados do jogo.
- RNF05: O código deve ser organizado em módulos com responsabilidades bem definidas, facilitando sua manutenção.
- RNF06: O aplicativo deve funcionar integralmente sem necessidade de conexão com a Internet.
- RNF07: A interface deve apresentar os elementos do jogo de forma clara e consistente em diferentes tamanhos de tela.


# Regras de Negócio

- RN01: O tabuleiro do jogo deve possuir dimensão fixa de 4 linhas por 4 colunas.
- RN02: Apenas blocos com o mesmo valor podem ser unidos.
- RN03: A união de dois blocos deve gerar um novo bloco com valor correspondente ao dobro dos blocos originais.
- RN04: Um bloco pode participar de apenas uma fusão por movimento.
- RN05: Um novo bloco somente deve ser gerado quando o movimento realizado alterar o estado do tabuleiro.
- RN06: A pontuação do jogador deve ser incrementada pelo valor do bloco criado após cada fusão.
- RN07: A partida deve ser considerada vencida quando for criado um bloco de valor 2048.
- RN08: A partida deve ser considerada perdida quando não existirem espaços livres e nenhum movimento resultar em fusão de blocos.
- RN09: A funcionalidade de desfazer deve restaurar o estado imediatamente anterior da partida, incluindo tabuleiro e pontuação.

# Estrutura do projeto

Definição inicial da estrutura/arquitetura, com classes, funções, camadas etc, que acho que serão necessários com o conhecimento que tenho antes de iniciar o projeto, pode mudar futuramente.

## Camadas

Vou dividir a estrutura do projeto em 4 camadas, que serão: model, engine, viewModel e UI.

### Model

Responsável por representar os dados do jogo, não tem regras de negócio ou a lógica do jogo.

- Board: representa o estado atual do tabuleiro
- GameSnapshot: representa o estado do jogo, guardando o estado do tabuleiro, a pontuação e o status do jogo
- Direction: baixo, cima, esquerda ou direita
- GameStatus (NOT_STARTED, PLAYING, WON, LOST)

### Engine

Responsável pela lógica do jogo:

- newGame(): iniciar novo jogo
- move(direction): recebe uma direção para mover os blocos, provavelmente será a função principal
- merge(): une e soma dois blocos quando acontecer uma colisão
- slide(): apenas desliza os blocos para uma direção
- spawnTile(): adiciona um novo bloco (em uma posição vazia) após um movimento válido 
- checkVictory(): verifica se tem algum bloco com pontuação igual a 2048
- checkGameOver(): verifica se não há mais movimentos possíveis (se não houverem espaços vazios e nenhuma possibilidade de fusão)

Por que o move é a função principal?
- move() será o "orquestrador" principal de todas as movimentações do jogo, funções como slide, merge, spawnTile, e as checagens, são apenas funções menores que fazem parte de um "move". 
- Ao fazer um movimento, move pode chamar slide para mover os blocos, e então chamar o merge para fundir os blocos, e então checa se houve vitória e derrota e depois adiciona um novo bloco com spawnTile.
- A ideia é não colocar a direção como um parametro recebido por slide e merge, em vez disso, o move() adapta o tabuleiro para que todas as operações aconteçam como se fossem para a esquerda. Então se o jogador fizer um movimento pra direita, o move() inverte as linhas (ou alguma função de inverter), chama o slide() e o merge() e desinverte a linha. Assim eu evito fazer a lógica de movimento várias vezes.


### ViewModel

Responsável por fazer a comunicação entre a interface e a Engine, além de armazenar o estado atual que será observado pela UI.

- gameState: guarda o GameSnapshot atual da partida, permitindo que a interface saiba como o jogo está

- newGame(): solicita à Engine a criação de uma nova partida e atualiza o estado do jogo

- move(direction): recebe a direção escolhida pelo jogador, solicita à Engine que execute o movimento e atualiza o estado retornado

- undo(): solicita o retorno ao estado anterior da partida e atualiza o estado observado pela UI

### UI

Responsável por apresentar o jogo ao jogador e receber suas interações. Não contém regras de negócio nem lógica de movimentação, apenas exibe o estado recebido da ViewModel.

- HomeScreen: tela inicial, onde o jogador pode iniciar uma nova partida

- GameScreen: tela principal do jogo, responsável por exibir o tabuleiro, a pontuação e os controles da partida

- VictoryScreen: exibida quando o jogador alcançar 2048, mostrando a vitória e opções para iniciar uma nova partida ou voltar ao início

- GameOverScreen: exibida quando não houver mais movimentos possíveis, mostrando o fim da partida e opções para iniciar uma nova partida ou voltar ao início

- BoardView: representa visualmente o tabuleiro 4x4 e seus blocos

- TileView: representa visualmente um bloco individual, recebendo seu valor e definindo sua aparência de acordo com esse valor

- ScoreView: exibe a pontuação atual da partida



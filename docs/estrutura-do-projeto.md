# Estrutura do projeto

O projeto é um aplicativo Android organizado em quatro partes principais: modelos, motor do jogo, gerenciamento de estado e interface.

```text
app/src/main/
├── java/ads/mov/game2048/
│   ├── MainActivity.kt
│   ├── engine/
│   │   └── GameEngine.kt
│   ├── models/
│   │   ├── Board.kt
│   │   ├── Direction.kt
│   │   ├── GameSnapshot.kt
│   │   └── GameStatus.kt
│   ├── ui/
│   │   ├── screens/
│   │   └── theme/
│   └── viewmodel/
│       └── ViewModel.kt
└── res/
    ├── drawable/
    ├── font/
    ├── mipmap-*/
    └── values/
```

## Modelos

As classes de `models` representam os dados do jogo:

- `Board`: armazena as células do tabuleiro 4×4 e informa as posições vazias.
- `Direction`: define as quatro direções possíveis de movimento.
- `GameSnapshot`: reúne o tabuleiro, a pontuação e o estado atual da partida.
- `GameStatus`: define os estados `NOT_STARTED`, `PLAYING`, `WON` e `LOST`.

## Motor do jogo

O `GameEngine` contém as regras do 2048. Ele inicia partidas, cria blocos, movimenta e combina as peças, atualiza a pontuação e verifica as condições de vitória ou derrota.

## Gerenciamento de estado

O `GameViewModel` faz a ligação entre o motor e a interface. Ele mantém o estado atual da partida, inicia um novo jogo e processa os movimentos do jogador. Um novo bloco é criado somente quando o tabuleiro muda após um movimento válido.

## Interface

A interface foi construída com Jetpack Compose:

- `MainActivity`: alterna entre o menu e a partida.
- `MainMenuScreen`: apresenta a tela inicial e o botão para jogar.
- `GameScreen`: recebe os gestos do jogador e exibe o tabuleiro e a pontuação.
- `GameBoard` e `Tile`: desenham o tabuleiro e seus blocos.
- `EndGameOverlay`: apresenta o resultado da partida e as opções de jogar novamente ou voltar ao menu.
- `ui/theme`: define o tema visual do aplicativo.

Os arquivos de `res` armazenam imagens, ícones, fontes, textos e outros recursos usados pela interface.


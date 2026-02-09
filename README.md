# 🃏 Liar's Bar - Java CLI

> Uma implementação em Java da lógica de cartas e "Russian Roulette" inspirada no jogo **Liar's Bar**.

Este projeto recria a tensão e a mecânica de blefe do jogo *Liar's Bar* (modo de cartas) utilizando uma interface de linha de comando (CLI). O foco principal é a simulação da lógica de jogo, a interação com bots e a mecânica progressiva de eliminação.

## 📋 Sobre o Projeto

O jogo consiste em uma mesa com 4 jogadores (1 humano e 3 bots). O objetivo é se livrar das cartas jogando-as na mesa, respeitando o tipo de carta da rodada (Ás, Rainha ou Rei). Se você não tiver a carta necessária, deve mentir (blefar).

Os adversários podem contestar sua jogada. Quem perder a contestação (o mentiroso descoberto ou o acusador errado) deve puxar o gatilho de um revólver. A cada tiro "seco", a chance de morrer na próxima vez aumenta.

## 🚀 Funcionalidades

* **Sistema de Rodadas:** A mesa define aleatoriamente o tipo de carta válida da vez (Ace's Table, Queen's Table ou King's Table).
* **Mecânica de Blefe:** Jogue cartas verdadeiras ou minta para esvaziar sua mão.
* **Jokers (Curingas):** A carta 'J' (Joker) vale como qualquer outra e nunca é considerada mentira.
* **Inteligência Artificial (Bots):** Adversários que tomam decisões de jogar e contestar baseadas em probabilidades (RNG).
* **Russian Roulette:** Sistema progressivo de chance de morte. Começa em 1/6 e a probabilidade aumenta a cada vez que o gatilho é puxado (1/5, 1/4...).
* **Interface Colorida:** Uso de códigos ANSI para destacar eventos importantes no terminal (Vermelho para erros/contestação falsa, Verde para acertos/vitória).

## 🛠️ Estrutura do Código

O projeto é dividido em três classes principais:

1.  **`Main.java`**: Ponto de entrada da aplicação. Inicia a instância da mesa.
2.  **`Mesa.java`**: O "motor" do jogo.
    * Controla o baralho e define o tipo da rodada.
    * Gerencia o loop de turnos e verifica se os jogadores estão vivos.
    * Coordena as contestações entre os jogadores.
3.  **`Jogador.java`**: Representa os participantes.
    * Gerencia a mão de cartas (A, Q, K, J).
    * Contém a lógica de decisão do Humano (via Scanner) e dos Bots (Aleatório).
    * Calcula a lógica de vida e morte (`testMorte`).

## 💻 Como Executar

### Pré-requisitos
* Java Development Kit (JDK) instalado.
* **Nota:** O projeto utiliza o método `void main()` (JEP 445), portanto recomenda-se o **Java 21** ou superior.

### Passo a passo

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/seu-usuario/liars-bar-java.git](https://github.com/seu-usuario/liars-bar-java.git)
    ```
2.  Navegue até a pasta do projeto:
    ```bash
    cd liars-bar-java
    ```
3.  **Para rodar diretamente (Java 21+):**
    ```bash
    java --enable-preview --source 21 Main.java
    ```
4.  **Ou compile e execute da forma tradicional:**
    Compile todos os arquivos:
    ```bash
    javac *.java
    ```
    Execute:
    ```bash
    java Main
    ```

## 🎮 Como Jogar

1.  **Início:** O jogo começa e você é identificado como **Jogador 1**.
2.  **Sua Vez:**
    * O console mostrará o tipo da mesa (ex: `[King's Table]`).
    * Suas cartas serão listadas. Digite a **quantidade** de cartas que deseja jogar.
    * Em seguida, digite o **índice** (número) de cada carta que deseja descartar.
    * *Dica:* Se a carta escolhida não for igual à da mesa e não for um Joker, você está **mentindo**.
3.  **Contestação:**
    * Quando um Bot jogar, o jogo perguntará: `Deseja contestar seu adversário? (s/n)`.
    * Se você disser 's' e ele estiver mentindo, ele puxa o gatilho. Se ele disse a verdade, você puxa o gatilho.
4.  **Fim de Jogo:**
    * Vence quem for o último sobrevivente na mesa.

## 📷 Exemplo de Saída

```text
[Ace's Table]
Suas Cartas: q k a j a 
Quantas cartas você quer jogar?
> 2
Escolha suas cartas! (Escolha de 1 a 5)
> 3
Escolha suas cartas! (Escolha de 1 a 4)
> 4

O Jogador 2 contestou!
O Jogador 1 sobreviveu... (Chances restantes: 5)
```
## 🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests para melhorar a inteligência dos bots, adicionar novas mecânicas ou refatorar o código.

## 📝 Licença
Este projeto está sobre a licença MIT.

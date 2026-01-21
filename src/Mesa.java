import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mesa {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private char rodada;
    private boolean lying;
    private final List<Jogador> jogador = new ArrayList<>();
    private int jogador_mrt = 0;
    private int loop = 0;
    int jogador_ini = 0;
    private int ultimo_jgd;
    private boolean nov_loop = false;
    private final int[] deck = new int[4];

    public Mesa() {
        for (int z = 1; z < 5; z++){
            String nomejogador = "Jogador " + z;
            jogador.add(new Jogador(nomejogador));
        }
    }

    public void Comecar(){
        do {
            deckReset();

            for (int i = 0; i < jogador.size(); i++){
                if (!jogador.get(i).getVivo()){
                    jogador.remove(i);
                    jogador_mrt++;
                }else {
                    jogador.get(i).Nova_rodada(this);
                }
            }
            if (jogador.size() == 1){
                break;
            }

            System.out.println(ANSI_YELLOW + "\nComeçando a rodada!" + ANSI_RESET);
            for (Jogador j : jogador) {
                if (j.getNome().equals("Jogador 1")) {
                    System.out.print(j.getNome() + " Chances: ");
                }else {
                    System.out.print("\n" + j.getNome() + " Chances: ");
                }
                for (int a = 0; a < j.chances; a++){
                    System.out.print("♥");
                }
                for (int b = 0; b < 6 - j.chances; b++){
                    System.out.print("-");
                }
            }

            int x = (int)(1 + Math.random() * 3);
            switch (x) {
                case 1:
                    rodada = 'a';
                    System.out.println("\n[Ace's Table]");
                    break;
                case 2:
                    rodada = 'q';
                    System.out.println("\n[Queen's Table]");
                    break;
                case 3:
                    rodada = 'k';
                    System.out.println("\n[King's Table]");
                    break;
            }

            for (int z = jogador_ini; z < 1000; z++){
                if (z > (3 - jogador_mrt)){
                    z = 0;
                }
                int hascartas = 0;
                for (Jogador j : jogador){
                    if (j.getCartas() == 0){
                        hascartas++;
                    }
                }
                if (hascartas == jogador.size()){
                    for (Jogador j : jogador){
                        j.AcataContestacao();
                    }
                    break;
                }
                if (jogador.get(z).getCartas() == 0){
                    break;
                }
                if (jogador.size() == 1){
                    break;
                }
                if (z != jogador_ini && nov_loop) {
                    this.lying = jogador.get(ultimo_jgd).getLying();
                    int m = (int) (1 + Math.random() * 10);
                    if (jogador.get(z).getNome().equals("Jogador 1")){
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Deseja contestar seu adversário? (s/n)");
                        char decisao =  sc.next().charAt(0);
                        if (decisao == 's'){
                            int n = jogador.get(z).Contestacao(this);
                            if (n == 1){
                                jogador.get(ultimo_jgd).AcataContestacao();
                            }
                            break;
                        }
                    }else if (m <= 5) {
                        int resposta = jogador.get(z).Contestacao(this);
                        if (resposta == 0) {
                            break;
                        } else if (resposta == 1) {
                            if (z == 0) {
                                jogador.getLast().AcataContestacao();
                            } else {
                                jogador.get(z - 1).AcataContestacao();
                            }
                            break;
                        }
                    }
                }
                jogador.get(z).Jogar(this);
                ultimo_jgd = z;
                nov_loop = true;
            }

            loop++;
            jogador_ini = loop % 4;
            nov_loop = false;
            if (jogador_ini > jogador.size()){
                jogador_ini -= jogador_mrt;
            }

        }while (jogador.size() > 1);

        if (!jogador.getFirst().getVivo()) {
            jogador.removeFirst();
        }
        if (!jogador.isEmpty()){
            System.out.println(ANSI_GREEN + jogador.getFirst().getNome() + " venceu!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Todos perderam!" + ANSI_RESET);
        }
    }

    private void deckReset(){
        this.deck[0] = 6;
        this.deck[1] = 6;
        this.deck[2] = 6;
        this.deck[3] = 2;
    }

    protected int getDeck(char carta){
        if (carta == 'a'){
            return this.deck[0];
        }else if (carta == 'q'){
            return this.deck[1];
        }else if (carta == 'k'){
            return this.deck[2];
        }else if (carta == 'j'){
            return this.deck[3];
        }else {
            return -1;
        }
    }

    protected void setDeck(char carta){
        if (carta == 'a'){
            this.deck[0] -= 1;
        }else if (carta == 'q'){
            this.deck[1] -= 1;
        }else if (carta == 'k'){
            this.deck[2] -= 1;
        }else if (carta == 'j'){
            this.deck[3] -= 1;
        }
    }

    public char getRodada() {
        return this.rodada;
    }

    public boolean getMentindo() {
        return lying;
    }
}

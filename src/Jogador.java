import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private final String nome;
    private boolean vivo;
    private final ArrayList<Character> cartas = new ArrayList<>();
    private boolean lying = false;
    int chances = 6;

    public Jogador(String nome) {
        vivo = true;
        this.nome = nome;
    }

    public void Nova_rodada(){
        cartas.clear();
        for (int x = 1; x < 6; x++) {
            int z = (int) (1 + Math.random() * 4);
            switch (z) {
                case 1:
                    cartas.add('a');
                    break;
                case 2:
                    cartas.add('q');
                    break;
                case 3:
                    cartas.add('k');
                    break;
                case 4:
                    cartas.add('j');
                    break;
            }
        }
    }

    public void Jogar(Mesa mesa) {
        char rodada = mesa.getRodada();
        lying = false;
        int qnt_cartas;
        if (nome.equals("Jogador 1")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Suas Cartas:");
            for (Character carta : cartas) {
                System.out.print(carta + " ");
            }
            System.out.println("\nQuantas cartas você quer jogar?");
            qnt_cartas = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < qnt_cartas; i++) {
                if (i == 0){
                    System.out.println("Escolha suas cartas! (Escolha de 1 a " + cartas.size() + ")");
                }else {
                    System.out.println("\nEscolha suas cartas! (Escolha de 1 a " + cartas.size() + ")");
                }
                int carta_escolhida = sc.nextInt();
                sc.nextLine();
                if (cartas.get(carta_escolhida - 1) != rodada && cartas.get(carta_escolhida - 1) != 'j') {
                    lying = true;
                }
                cartas.remove(carta_escolhida - 1);
                if(i != qnt_cartas - 1){
                    for (Character carta : cartas) {
                        System.out.print(carta + " ");
                    }
                }

            }
        } else {
            qnt_cartas = (int) (1 + (Math.random() * (cartas.size())));
            System.out.println(nome + " joga " + qnt_cartas + " " + rodada);
            for (int i = 0; i < qnt_cartas; i++) {
                int carta_escolhida = (int) (Math.random() * cartas.size());
                if (cartas.get(carta_escolhida) != rodada) {
                    lying = true;
                }
                cartas.remove(carta_escolhida);
            }
        }
    }

    public int Contestacao(Mesa mesa) {

            if (!mesa.getMentindo()) {
                System.out.println(ANSI_RED + "O " + nome + " contestou em falso!" + ANSI_RESET);
                testMorte();
                return 0;
            }else{
                System.out.println(ANSI_GREEN + "O " + nome + " contestou!" + ANSI_RESET);
                return 1;
            }
    }

    private void testMorte(){
        int morte = (int)(1 + Math.random() * chances);
        if (morte == chances) {
            System.out.println("O " + nome + " morreu!");
            vivo = false;
        }else {
            chances -= 1;
        }
    }

    public void AcataContestacao() {
        testMorte();
    }

    public boolean getVivo() {
        return vivo;
    }

    public boolean getLying() {
        return lying;
    }

    public int getCartas() {
        return cartas.size();
    }

    public String getNome() {
        return nome;
    }
}

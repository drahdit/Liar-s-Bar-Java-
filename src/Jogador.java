import java.util.ArrayList;

public class Jogador {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private String nome;
    private boolean vivo;
    private ArrayList<Character> cartas = new ArrayList<Character>();
    private boolean lying = false;
    private char rodada;
    int chances = 6;
    private boolean contestacao;

    public Jogador(String nome) {
        vivo = true;
        this.nome = nome;
    }

    public void Nova_rodada(){
        for (int x = 1; x < 6; x++) {
            int z = (int) (1 + Math.random() * 3);
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

    public void Jogar(Mesa mesa){
        this.rodada = mesa.getRodada();
        lying = false;
        int qnt_cartas = (int)(1 + Math.random() * 3);
        System.out.print(nome + " joga " +  qnt_cartas + " " + rodada);
        for (int i = 1; i <= qnt_cartas; i++) {
            int carta_escolhida = (int)(Math.random() * cartas.size());
            if (cartas.get(1) != rodada) {
                lying = true;
            }
            cartas.remove(carta_escolhida);
        }
        System.out.println(lying);

    }
    public void nomes(){
        System.out.println("Nome do Jogador: " + nome);
    }

    public boolean getVivo() {
        return vivo;
    }

    public boolean getLying() {
        return lying;
    }

    public int Contestacao(Mesa mesa) {
        int x = (int) (1 + Math.random() * 10);
        if (x <= 5) {
            if (!mesa.getMentindo()) {
                System.out.println(ANSI_RED + "O " + nome + " contestou em falso!" + ANSI_RESET);
                int morte = (int)(1 + Math.random() * chances);
                if (morte == chances) {
                    System.out.println("O " + nome + " morreu!");
                    vivo = false;
                }else {
                    chances -= 1;
                    System.out.println("Chances restantes: " + chances);
                }
                return 0;
            }else{
                System.out.println(ANSI_GREEN + "O " + nome + " contestou!" + ANSI_RESET);
                return 1;
            }
        }else{
            return 2;
        }
    }

    public void AcataContestacao() {
        int morte = (int)(1 + Math.random() * chances);
        if (morte == chances) {
            System.out.println("O " + nome + " morreu!");
            vivo = false;
        }else {
            chances -= 1;
            System.out.println("Chances restantes: " + chances);
        }

    }
}

import java.util.ArrayList;

public class Jogador {
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
        System.out.println(nome + " joga " +  qnt_cartas + " " + rodada);
        for (int i = 1; i <= qnt_cartas; i++) {
            int carta_escolhida = (int)(Math.random() * cartas.size());
            if (cartas.get(1) != rodada) {
                lying = true;
            }
            cartas.remove(carta_escolhida);
        }

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

    public int Contestacao() {
        int x = (int) (1 + Math.random() * 10);
        if (x <= 5) {
            if (!lying) {
                System.out.println("O " + nome + " contestou em falso!");
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
                System.out.println("O " + nome + " contestou!");
                return 1;
            }
        }else{
            return 2;
        }
    }

    public void AcataContestacao() {
        int morte = (int)(1 + Math.random() * chances);
        if (morte == chances) {
            vivo = false;
        }else {
            chances -= 1;
        }

    }
}

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private boolean vivo;
    private ArrayList<Character> cartas = new ArrayList<Character>();
    private boolean lying = false;
    private char rodada;

    public Jogador(String nome, Mesa mesa) {
        vivo = true;
        this.rodada = mesa.getRodada();
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

    public void Jogar(){
        lying = false;
        int qnt_cartas = (int)(1 + Math.random() * 3);
        System.out.println("Jogador " + nome + " joga " +  qnt_cartas + rodada);
        for (int i = 1; i <= qnt_cartas; i++) {
            int carta_escolhida = (int)(Math.random() * cartas.size());
            if (cartas.get(1) != rodada) {
                lying = true;
            }
            cartas.remove(carta_escolhida);
        }
        if (lying) {
            System.out.println("Jogador está mentindo!");
        }
    }
    public void nomes(){
        System.out.println("Nome do Jogador: " + nome);
    }

}

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private String nome;
    private char rodada;
    private List<Jogador> jogador = new ArrayList<>();

    public Mesa(String nome) {
        this.nome = nome;
        int x = (int)(1 + Math.random() * 3);
        switch (x) {
            case 1:
                rodada = 'a';
                break;
            case 2:
                rodada = 'q';
                break;
            case 3:
                rodada = 'k';
                break;
        }
        for (int z = 1; z < 5; z++){
            String nomejogador = "jogador " + z;
            jogador.add(new Jogador(nomejogador, this));
        }
        (jogador.get(1)).nomes();
        (jogador.get(1)).Nova_rodada();
        (jogador.get(1)).Jogar();
    }

    public char getRodada() {
        return this.rodada;
    }

}

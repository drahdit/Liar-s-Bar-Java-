import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private String nome;
    private char rodada;
    private boolean lying;
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
            String nomejogador = "Jogador " + z;
            jogador.add(new Jogador(nomejogador));
        }

    }

    public void Comecar(){
        do {
            System.out.println("Comecando a rodada!");
            for (int i = 0; i < jogador.size(); i++){
                if (jogador.get(i).getVivo() == false){
                    jogador.remove(i);
                }else {
                    jogador.get(i).Nova_rodada();
                }
            }
            for (int z = 0; z < jogador.size(); z++){
                if (z != 0){
                    if (jogador.get(z).Contestacao() == 0){
                        break;
                    } else if (jogador.get(z).Contestacao() == 1) {
                        jogador.get(z - 1).AcataContestacao();
                        break;
                    }
                }
                jogador.get(z).Jogar(this);
                jogador.get(z).getLying();
            }
        }while (jogador.get(0).getVivo() == true && jogador.get(jogador.size() - 1).getVivo() == true);

    }

    public char getRodada() {
        return this.rodada;
    }

    public void Mentindo() {
        if (jogador.get(0).getVivo() == true) {

        }
    }

}

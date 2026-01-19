import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private String nome;
    private char rodada;
    private boolean lying;
    private List<Jogador> jogador = new ArrayList<>();
    private int jogador_mrt = 0;

    public Mesa(String nome) {
        this.nome = nome;

        for (int z = 1; z < 5; z++){
            String nomejogador = "Jogador " + z;
            jogador.add(new Jogador(nomejogador));
        }

    }

    public void Comecar(){
        do {
            boolean contestacao_atv = false;
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
            System.out.println("Comecando a rodada!");

            for (int i = 0; i < jogador.size(); i++){
                if (jogador.get(i).getVivo() == false){
                    jogador.remove(i);
                    jogador_mrt++;
                }else {
                    jogador.get(i).Nova_rodada();
                }
            }
            if (jogador.size() == 1){
                break;
            }
            System.out.println("size: " + jogador.size());
            contestacao_atv = false;

            for (int z = 0; z < 1000 || contestacao_atv == false; z++){
                if (z >= (3 - jogador_mrt)){
                    z = 0;
                }
                System.out.println("z = " + z);
                if (z != 0){
                    this.lying = jogador.get(z - 1).getLying();

                    int resposta = jogador.get(z).Contestacao(this);
                    if (resposta == 0){
                        contestacao_atv = true;
                        break;
                    } else if (resposta == 1) {
                        jogador.get(z - 1).AcataContestacao();
                        contestacao_atv = true;
                        break;
                    }
                }
                jogador.get(z).Jogar(this);

            }
        }while (jogador_mrt != 3);
        System.out.println(jogador.get(0).getNome() + "venceu!");

    }

    public char getRodada() {
        return this.rodada;
    }

    public boolean getMentindo() {
        return lying;
    }

}

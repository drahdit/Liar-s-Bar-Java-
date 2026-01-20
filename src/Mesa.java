import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private String nome;
    private char rodada;
    private boolean lying;
    private List<Jogador> jogador = new ArrayList<>();
    private int jogador_mrt = 0;
    private int loop = 0;
    int jogador_ini = loop % 4;
    private int ultimo_jgd;
    private boolean nov_loop = false;


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

            contestacao_atv = false;

            for (int z = jogador_ini; z < 1000 || contestacao_atv == false; z++){
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

                    int resposta = jogador.get(z).Contestacao(this);
                    if (resposta == 0) {
                        contestacao_atv = true;
                        break;
                    } else if (resposta == 1) {
                        if (z == 0) {
                            jogador.get(jogador.size() - 1).AcataContestacao();
                        } else {
                            jogador.get(z - 1).AcataContestacao();
                        }
                        contestacao_atv = true;
                        break;
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
        if (jogador.get(0).getVivo() == false) {
            jogador.remove(0);
        }
        if (!jogador.isEmpty()){
            System.out.println(jogador.get(0).getNome() + " venceu!");
        } else {
            System.out.println("Todos perderam!");
        }

    }

    public char getRodada() {
        return this.rodada;
    }

    public boolean getMentindo() {
        return lying;
    }

}

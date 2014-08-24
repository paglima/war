package War;

import java.util.ArrayList;

public class War {

    private final Tabuleiro tabuleiro;
    private final ArrayList<Exercito> exercitos;
    private final ArrayList<Jogador> jogadores;

    public War() {
        tabuleiro = new Tabuleiro();
        exercitos = new ArrayList<>();
        jogadores = new ArrayList<>();
    }
    
    @Override
    public String toString(){
        System.out.print(tabuleiro);
        return "";
    }

}

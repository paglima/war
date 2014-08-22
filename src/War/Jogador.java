package War;

import java.util.ArrayList;

class Jogador {
    
    private String nome;
    private Exercito exercito;
    private boolean jogadorHumano;
    private CartaObjetivo cartaObjetivo;
    private final ArrayList<Carta> cartas;
    
    public Jogador(String nome, boolean jogadorHumano){
        this.nome = nome;
        exercito = null;
        this.jogadorHumano = jogadorHumano;
        cartaObjetivo = null;
        cartas = new ArrayList<>();
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the exercito
     */
    public Exercito getExercito() {
        return exercito;
    }

    /**
     * @param exercito the exercito to set
     */
    public void setExercito(Exercito exercito) {
        this.exercito = exercito;
    }

    /**
     * @return the jogadorHumano
     */
    public boolean isJogadorHumano() {
        return jogadorHumano;
    }

    /**
     * @param jogadorHumano the jogadorHumano to set
     */
    public void setJogadorHumano(boolean jogadorHumano) {
        this.jogadorHumano = jogadorHumano;
    }

    /**
     * @return the cartaObjetivo
     */
    public CartaObjetivo getCartaObjetivo() {
        return cartaObjetivo;
    }

    /**
     * @param cartaObjetivo the cartaObjetivo to set
     */
    public void setCartaObjetivo(CartaObjetivo cartaObjetivo) {
        this.cartaObjetivo = cartaObjetivo;
    }

    /**
     * @return the cartas
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * @param cartas the cartas to set
     */
    public void addCarta(Carta carta) {
        cartas.add(carta);
    }
    
}

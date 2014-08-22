package War;

import java.util.ArrayList;

class Territorio {
    
    private String nome;
    private Continente continente;
    private final ArrayList<Territorio> vizinhos;
    
    private Exercito exercito;
    private int quantidadeExercito;
    
    public Territorio(String nome){
        this.nome = nome;
        continente = null;
        vizinhos = new ArrayList<>();
        
        exercito = null;
        quantidadeExercito = 0;
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
     * @return the continente
     */
    public Continente getContinente() {
        return continente;
    }

    /**
     * @param continente the continente to set
     */
    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    /**
     * @return the vizinhos
     */
    public ArrayList<Territorio> getVizinhos() {
        return vizinhos;
    }
    
    public void addVizinho(Territorio territorio){
        vizinhos.add(territorio);
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
     * @return the quantidadeExercito
     */
    public int getQuantidadeExercito() {
        return quantidadeExercito;
    }

    /**
     * @param quantidadeExercito the quantidadeExercito to set
     */
    public void setQuantidadeExercito(int quantidadeExercito) {
        this.quantidadeExercito = quantidadeExercito;
    }
    
}

package War;

import java.util.ArrayList;

class Continente {
    
    private String nome;
    private final ArrayList<Territorio> territorios;
    
    Continente(String nome){
        this.nome = nome;
        territorios = new ArrayList<>();
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
     * @return the territorios
     */
    public ArrayList<Territorio> getTerritorios() {
        return territorios;
    }

    /**
     * @param territorios the territorios to set
     */
    public void addTerritorio(Territorio territorio) {
        territorios.add(territorio);
    }
    
}

package War;

import java.util.ArrayList;

class Tabuleiro {

    private final ArrayList<Continente> continentes;
    private final ArrayList<Territorio> territorios;

    Tabuleiro() {
        continentes = new ArrayList<>();
        territorios = new ArrayList<>();
        construirMapa();
    }

    private void construirMapa() {
        Continente americaDoSul = new Continente("América do Sul");
        Continente americaDoNorte = new Continente("América do Norte");
        Continente europa = new Continente("Europa");
        Continente africa = new Continente("África");
        Continente asia = new Continente("Ásia");
        Continente oceania = new Continente("Oceania");
        
        continentes.add(americaDoSul);
        continentes.add(americaDoNorte);
        continentes.add(europa);
        continentes.add(africa);
        continentes.add(asia);
        continentes.add(oceania);
        
        addTerritorio("Brasil", americaDoSul);
        addTerritorio("Colômbia", americaDoSul);
        addTerritorio("Chile", americaDoSul);
        addTerritorio("Argentina", americaDoSul);
        
        addTerritorio("México", americaDoNorte);
        addTerritorio("Califórnia", americaDoNorte);
        addTerritorio("Nova York", americaDoNorte);
        addTerritorio("Vancouver", americaDoNorte);
        addTerritorio("Ottawa", americaDoNorte);
        addTerritorio("Labrador", americaDoNorte);
        addTerritorio("Alaska", americaDoNorte);
        addTerritorio("Mackenzie", americaDoNorte);
        addTerritorio("Groelândia", americaDoNorte);
        
        addTerritorio("Groelândia", africa);
        addTerritorio("Groelândia", africa);
        addTerritorio("Groelândia", africa);
        addTerritorio("Groelândia", africa);
        addTerritorio("Groelândia", africa);
        addTerritorio("Groelândia", africa);
    }
    
    @Override
    public String toString(){
        for(Continente continente:continentes)
            System.out.println(continente.getNome());
        
        return "";
    }
    
    private void addTerritorio(String nome, Continente continente){
        Territorio territorio = new Territorio(nome, continente);
        territorios.add(territorio);
        continente.addTerritorio(territorio);
    }

}

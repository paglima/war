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
        Continente continente;

        continente = criaContinente("América do Sul");
        addTerritorio("Brasil", continente);
        addTerritorio("Colômbia", continente);
        addTerritorio("Chile", continente);
        addTerritorio("Argentina", continente);

        continente = criaContinente("América do Norte");
        addTerritorio("México", continente);
        addTerritorio("Califórnia", continente);
        addTerritorio("Nova York", continente);
        addTerritorio("Vancouver", continente);
        addTerritorio("Ottawa", continente);
        addTerritorio("Labrador", continente);
        addTerritorio("Alaska", continente);
        addTerritorio("Mackenzie", continente);
        addTerritorio("Groelândia", continente);

        continente = criaContinente("África");
        addTerritorio("Argelia-Nigéria", continente);
        addTerritorio("Egito", continente);
        addTerritorio("Sudão", continente);
        addTerritorio("Congo", continente);
        addTerritorio("África do Sul", continente);
        addTerritorio("Madasgacar", continente);

        continente = criaContinente("Europa");
        addTerritorio("Islândia", continente);
        addTerritorio("Inglaterra", continente);
        addTerritorio("Portugal", continente);
        addTerritorio("Suécia", continente);
        addTerritorio("Alemanha", continente);
        addTerritorio("Polônia", continente);
        addTerritorio("Moscou", continente);

        continente = criaContinente("Ásia");
        addTerritorio("Osmk", continente);
        addTerritorio("Aral", continente);
        addTerritorio("Oriente Médio", continente);
        addTerritorio("Dudinka", continente);
        addTerritorio("Sibéria", continente);
        addTerritorio("Tchita", continente);
        addTerritorio("Mongólia", continente);
        addTerritorio("China", continente);
        addTerritorio("Índia", continente);
        addTerritorio("Vladivostok", continente);
        addTerritorio("Japão", continente);
        addTerritorio("Vietnã", continente);

        continente = criaContinente("Oceania");
        addTerritorio("Sumatra", continente);
        addTerritorio("Borneo", continente);
        addTerritorio("Nova Guiné", continente);
        addTerritorio("Austrália", continente);
        
        addVizinhos("Brasil", "Colômbia");
        addVizinhos("Brasil", "Chile");
        addVizinhos("Brasil", "Argentina");
        addVizinhos("Brasil", "Argelia-Nigéria");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        addVizinhos("", "");
        
        listaVizinhos("Brasil");
        System.out.println("\n\n");

    }

    @Override
    public String toString() {
        for (Continente continente : continentes) {
            System.out.println(continente.getNome());
            for (Territorio territorio : continente.getTerritorios()) {
                System.out.println(" " + territorio.getNome());
            }
            System.out.println("");
        }

        return "";
    }
    
    private void listaVizinhos(String nome){
        Territorio territorio = procuraTerritorio(nome);
        for (Territorio territorio1 : territorio.getVizinhos()) {
            System.out.println(territorio1.getNome());
        }
    }

    private Continente criaContinente(String nome) {
        Continente continente = new Continente(nome);
        continentes.add(continente);

        return continente;
    }

    private void addTerritorio(String nome, Continente continente) {
        Territorio territorio = new Territorio(nome, continente);
        territorios.add(territorio);
        continente.addTerritorio(territorio);
    }

    private void addVizinhos(String vizinho1, String vizinho2) {
        Territorio territorio1 = procuraTerritorio(vizinho1);
        Territorio territorio2 = procuraTerritorio(vizinho2);
        if(territorio1 == null || territorio2 == null)
            return;
        territorio1.addVizinho(territorio2);
        territorio2.addVizinho(territorio1);
    }

    private Territorio procuraTerritorio(String nome) {
        for (Territorio territorio : territorios) {
            if(territorio.getNome().equalsIgnoreCase(nome))
                return territorio;
        }
        return null;
    }

}

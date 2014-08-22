package War;

import java.util.ArrayList;

class CartaCoringa implements Carta{
    
    private final ArrayList<Simbolo> simbolos;
    
    public CartaCoringa(){
        simbolos = new ArrayList<>();
    }

    /**
     * @return the simbolos
     */
    public ArrayList<Simbolo> getSimbolos() {
        return simbolos;
    }

    /**
     * @param simbolos the simbolos to set
     */
    public void addSimbolo(Simbolo simbolo) {
        simbolos.add(simbolo);
    }
    
    
    
}

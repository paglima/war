package War;

class CartaTerritorio implements Carta{
    
    private Territorio territorio;
    private Simbolo simbolo;
    
    public CartaTerritorio(Territorio territorio, Simbolo simbolo){
        this.territorio = territorio;
        this.simbolo = simbolo;
    }

    /**
     * @return the territorio
     */
    public Territorio getTerritorio() {
        return territorio;
    }

    /**
     * @param territorio the territorio to set
     */
    public void setTerritorio(Territorio territorio) {
        this.territorio = territorio;
    }

    /**
     * @return the simbolo
     */
    public Simbolo getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }
    
}

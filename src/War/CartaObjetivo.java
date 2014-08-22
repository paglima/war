package War;

class CartaObjetivo {
    
    private final int codigo;
    
    public CartaObjetivo(int codigo){
        this.codigo = codigo;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    public String getObjetivo(){
        switch(codigo){
            case 1: return "Conquistar na totalidade a EUROPA, a OCEANIA e mais um terceiro";
            case 2: return "Conquistar na totalidade a ASIA e a AMÉRICA DO SUL";
            case 3: return "Conquistar na totalidade a EUROPA, a AMÉRICA DO SUL e mais um terceiro";
            case 4: return "Conquistar 18 TERRITÓRIOS e ocupar cada um deles com pelo menos dois exércitos";
            case 5: return "Conquistar na totalidade a ASIA e a ÁFRICA";
            case 6: return "Conquistar na totalidade a AMÉRICA DO NORTE e a ÁFRICA";
            case 7: return "Conquistar 24 TERRITÓRIOS à sua escolha";
            case 8: return "Conquistar na totalidade a AMÉRICA DO NORTE e a OCEANIA";
            case 9: return "Destruir totalmente OS EXÉRCITOS AZUIS";
            case 10: return "Destruir totalmente OS EXÉRCITOS AMARELOS";
            case 11: return "Destruir totalmente OS EXÉRCITOS VERMELHOS";
            case 12: return "Destruir totalmente OS EXÉRCITOS PRETOS";
            case 13: return "Destruir totalmente OS EXÉRCITOS BRANCO";
            case 14: return "Destruir totalmente OS EXÉRCITOS VERDES";
            default: return "";
        }
    }
    
}

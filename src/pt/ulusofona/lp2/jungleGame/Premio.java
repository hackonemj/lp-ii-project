package pt.ulusofona.lp2.jungleGame;

public class Premio {
    String nome;
    int valor;
    int posicao;
    
    Premio(){
        
    }
    Premio(String nome, int valor, int posicao){
        this.nome = nome;
        this.valor = valor;
        this.posicao = posicao;
    }
    
    @Override
    public String toString(){
       return nome + " ("+ valor + ")";
   }
}

package pt.ulusofona.lp2.jungleGame;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    String nome;
    int id;
    int idEspecie;
    int velocidade;
    int energiaInicial;
    int posicao;
    
    int classificacao;
    List <Premio> premios;
    
    Animal(){
        
    }
    Animal(String nome, int id, int idEs, int velocidade, int energiaInicial){
        this.premios = new ArrayList<>();
        this.nome = nome;
        this.id = id;
        this.idEspecie = idEs;
        this.velocidade = velocidade;
        this.energiaInicial = energiaInicial;
    }
    public int getPosicao(){
        return posicao;
    }
    public String getImagePNG(){
        return null;
    }
    public String obterNomeEspecie(){
        String[] nomeEspecie = new String[10];
        nomeEspecie[0]="Águia";
        nomeEspecie[1]="Girafa";
        nomeEspecie[2]="Gorila";
        nomeEspecie[3]="Humano";
        nomeEspecie[4]="Leão";
        nomeEspecie[5]="Lebre";
        nomeEspecie[6]="Mocho";
        nomeEspecie[7]="Tartaruga";
        nomeEspecie[8]="Tigre";
        nomeEspecie[9]="Zebra";
        return nomeEspecie[idEspecie];
    }
    
}

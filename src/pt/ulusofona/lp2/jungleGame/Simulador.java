package pt.ulusofona.lp2.jungleGame;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulador {
    List <Animal> animais;
    List <Premio> premios;
    List <String> classificacaoFinal  = new ArrayList<>();
    List <String> pontuacaoFinal = new ArrayList<>();
    int comprimentoPista;
    
    String nome;
    int idAnimal;
    int idEspecie;
    int velocidade;
    int energiaInicial;
        
    String nomePremio;
    int valor;
    int posicao;
        
    int numAnimais;
    int numPremios;
    int seccao;
    
    int tempo = 0;
    int classificacao;
    
    public int obterClassificacao(){
        return classificacao++;
    }
    public void atribuirPremios(Animal a, int classificacao){
        for(Premio premio: premios){
            if(premio.posicao == classificacao){
                a.premios.add(premio);
            }
        }
    }
    public boolean iniciaJogo(File ficheiroInicial, int comprimentoPista){
        animais = new ArrayList<Animal>();
        premios = new ArrayList<Premio>();
        this.comprimentoPista = comprimentoPista;
        
        classificacaoFinal.add("CLASSIFICAÇÃO FINAL DA PROVA");
        pontuacaoFinal.add("\n");
        pontuacaoFinal.add("TABELA DE PRÉMIOS");
        
        numAnimais = 0;
        numPremios = 0;
        seccao = 0;
        tempo = 0;
        classificacao = 1;
        try {
            
            BufferedReader buffer = new BufferedReader(new FileReader(ficheiroInicial));
            while (buffer.ready()) {
                String linha = buffer.readLine();
                String[] dados = linha.split("");
                
                for(int index=0; index<dados.length; index++){
                    if(dados[index].contains(":")){
                        seccao++;
                    }
                }
                dados = linha.split(":");
                if(seccao == 1){
                    numAnimais = Integer.parseInt(dados[0]);
                    numPremios = Integer.parseInt(dados[1]);
                }
                else if(seccao == 4){
                    nome = dados[0];
                    idAnimal = Integer.parseInt(dados[1]);
                    idEspecie = Integer.parseInt(dados[2]);
                    velocidade = Integer.parseInt(dados[3]);
                    energiaInicial = Integer.parseInt(dados[4]);
                    Animal animal = new Animal(nome,idAnimal, idEspecie, velocidade, energiaInicial);
                    animais.add(animal);
                    
                }
                else if(seccao == 2){
                    nomePremio = dados[0];
                    valor = Integer.parseInt(dados[1]);
                    posicao = Integer.parseInt(dados[2]);
                    Premio premio = new Premio(nomePremio, valor, posicao);
                    premios.add(premio);
                }  
                
                seccao = 0;
                System.out.println(linha);
            }
            buffer.close();
            return true;
                
        } catch (IOException ex) {
            return false;
        }      
    }

    public boolean processaTurno() {
        int count = 0;
        int i = 0;
        double distancia;
        
        
        for(Animal animal:animais){
            
            if (animal.posicao == comprimentoPista-1){
                count++;
            }
            else{
                energiaInicial = animal.energiaInicial;
                velocidade = animal.velocidade;
            
                if(energiaInicial > 0){
                    distancia = velocidade * energiaInicial * 0.5 + 1;
                    if(distancia + animal.posicao > comprimentoPista -1 ){
                        animal.posicao = comprimentoPista-1;
                        System.out.println(animal.nome +" + "+ animal.posicao +" o tempo é ="+tempo + " " +animal.obterNomeEspecie());
                        
                        animal.classificacao = obterClassificacao();
                        classificacaoFinal.add("# " + animal.classificacao+ " - "+ animal.nome +" - "+ animal.obterNomeEspecie()+" - "+String.valueOf(tempo));
                        atribuirPremios(animal, animal.classificacao);
                        pontuacaoFinal.add(animal.nome +"\n"+ animal.premios);
                    }
                    else{
                        animal.posicao += (int)distancia;
                        animal.energiaInicial -= 1;
                    }
                }
                else{
                    animal.energiaInicial += 2;
                }
            
            }
            i++;
            
        }
        
        if(count==i){
            return false;
        }
        tempo++;
        return true;
    }
    public List<Animal> getAnimais(){
        return animais;
    }
    
    public List<String> getClassificacaoGeral(){
        //Collections.sort(classificacaoFinal);
        
        return classificacaoFinal;
    }
    
    public List<String> getTabelaPremios(){
       // Collections.sort(pontuacaoFinal);
        return pontuacaoFinal;   
    }
    
    public void escreverResultados(String filename){
        filename = "resultado.txt";
        
            try {

                File file = new File(filename);

                try (FileWriter escritor = new FileWriter(file)) {
                       String newLine = System.getProperty("line.separator");
                    for(String s: classificacaoFinal){
                        escritor.write(s + newLine);
                    }
                    
                    for(String p :pontuacaoFinal){
                        escritor.write(p + newLine);
                    }

                    escritor.close();   
                }
            }
            catch (IOException e) {
                System.out.println("O ficheiro " + filename + "não existe");
                
            }     
            
    }
}

package davi.ifmg.mente_boa.model;

public class Jogador {
    private int id;
    private String nome;
    private String cpf;
    private String apelido;

    public Jogador() {
        
    }

    public Jogador(String nome, String cpf, String apelido) {
        this.nome = nome;
        this.cpf = cpf;
        this.apelido = apelido;
    }
    
    public Jogador(int id, String nome, String cpf, String apelido) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.apelido = apelido;
    }
    
    public void setName(String nome){
       this.nome = nome;
    }
    
    public void setCPF(String cpf){
       this.cpf = cpf;
    }
    
    public void setApelido(String apelido){
       this.apelido = apelido;
    }  
    
    public String getApelido(){
       return apelido;
    }  
    
    public String getNome(){
       return nome;
    }  
    
    public String getCPF(){
       return cpf;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String toString(){
       return "Nome: " + nome + "\t CPF: " +  cpf + "\t Apelido: " + apelido;
    }
    
}

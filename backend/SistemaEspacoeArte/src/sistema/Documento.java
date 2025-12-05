package sistema;

public class Documento {
	
    private String nome;
    private String caminho;
    
    // Construtor
    public Documento(String nome, String caminho) {
        this.nome = nome;
        this.caminho = caminho;
    }
    
    // Getters e setters
    public String getNome() {return nome;}

    public String getCaminho() {return caminho;}

    public void setNome(String nome) {this.nome = nome;}

    public void setCaminho(String caminho) {this.caminho = caminho;}
}

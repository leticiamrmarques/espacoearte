package sistema;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {

    private static int proximoId = 1;

    private int idCliente;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    private List<Servico> servicos;
    private List<Compromisso> compromissos;

    public Cliente(String nome, String telefone, String email, String endereco) {
        this.idCliente = proximoId++;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.servicos = new ArrayList<>();
        this.compromissos = new ArrayList<>(); // inicializa lista
    }

    // Getters e Setters
    public int getIdCliente() { return idCliente; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    // Serviços
    public void adicionarServico(Servico s) {
        if (s != null && !servicos.contains(s)) {
            servicos.add(s);
        }
    }

    public void removerServico(Servico s) {
        servicos.remove(s);
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    // Compromissos
    public void adicionarCompromisso(Compromisso c) {
        if (c != null && !compromissos.contains(c)) {
            compromissos.add(c);
        }
    }

    public void removerCompromisso(Compromisso c) {
        compromissos.remove(c);
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public abstract void consultarInfo();
}

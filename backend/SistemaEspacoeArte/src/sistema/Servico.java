package sistema;

import java.util.ArrayList;
import java.util.List;

public class Servico {

    private static int proximoId = 1;

    private int idServico;
    private String descricao;
    private String enderecoEntrega;
    private double valorBase;
    private Cliente cliente;
    private Status status;
    private List<Compromisso> compromissos;
    private List<Documento> documentos;

    public enum Status { EM_ESPERA, EM_PRODUCAO, CONCLUIDO, CANCELADO }

    public Servico(String descricao, String enderecoEntrega, double valorBase, Cliente cliente) {
        this.idServico = proximoId++;
        this.descricao = descricao;
        this.enderecoEntrega = enderecoEntrega;
        this.valorBase = valorBase;
        this.cliente = cliente;
        this.status = Status.EM_ESPERA;
        this.compromissos = new ArrayList<>();
        this.documentos = new ArrayList<>();

        if (cliente != null) {
            cliente.adicionarServico(this);
        }

        RepositorioServico.getInstance().salvar(this);
    }

    public int getIdServico() { return idServico; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }

    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }

    public Cliente getCliente() { return cliente; }
    public Status getStatus() { return status; }

    public List<Compromisso> getCompromissos() { return compromissos; }
    
    // Métodos para gerenciar documentos
    public List<Documento> getDocumentos() { return documentos; }
    
    public void anexarDocumento(Documento documento) {
        if (documento != null && !documentos.contains(documento)) {
            documentos.add(documento);
            System.out.println("Documento '" + documento.getNome() + "' anexado ao serviço ID " + idServico);
        }
    }
    
    public void removerDocumento(Documento documento) {
        if (documentos.remove(documento)) {
            System.out.println("Documento '" + documento.getNome() + "' removido do serviço ID " + idServico);
        }
    }
    
    public Documento buscarDocumentoPorNome(String nome) {
        for (Documento doc : documentos) {
            if (doc.getNome().equalsIgnoreCase(nome)) {
                return doc;
            }
        }
        return null;
    }

    public void atribuirCompromisso(Compromisso c) {
        if (c != null && !compromissos.contains(c)) {
            compromissos.add(c);
        }
    }

    public void removerCompromisso(Compromisso c) {
        compromissos.remove(c);
    }

    public void cancelar() {
        status = Status.CANCELADO;
        for (Compromisso c : new ArrayList<>(compromissos)) {
            c.cancelar();
        }
        compromissos.clear();
        if (cliente != null) {
            cliente.removerServico(this);
        }
        RepositorioServico.getInstance().remover(idServico);
    }

    public void exibirDetalhes() {
        System.out.println("\n=== DETALHES DO SERVIÇO ===");
        System.out.println("ID: " + idServico);
        System.out.println("Descrição: " + descricao);
        System.out.println("Endereço: " + enderecoEntrega);
        System.out.println("Valor: R$ " + String.format("%.2f", valorBase));
        System.out.println("Status: " + status);
        System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "Não informado"));
        System.out.println("Compromissos vinculados: " + compromissos.size());
        System.out.println("Documentos anexados: " + documentos.size());
        
        if (!documentos.isEmpty()) {
            System.out.println("\nLista de documentos:");
            for (Documento doc : documentos) {
                System.out.println("  - " + doc.getNome() + " (" + doc.getCaminho() + ")");
            }
        }
    }
}
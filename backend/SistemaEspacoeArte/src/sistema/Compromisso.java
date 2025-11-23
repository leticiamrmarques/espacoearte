package sistema;

import java.time.LocalDateTime;

public class Compromisso {

    private static int proximoId = 1;
    private int idCompromisso;

    private LocalDateTime dataHora;
    private String descricao;
    private String funcionario;
    private Tipo tipo;
    private Status status;
    private Cliente cliente;   // todo compromisso deve ter cliente
    private Servico servico;   // opcional

    public enum Tipo {
        ORCAMENTO,
        REUNIAO,
        MEDIDAS,
        ENTREGA
    }

    public enum Status {
        A_CONFIRMAR,
        CONFIRMADO,
        REALIZADO,
        CANCELADO
    }

    // ==================== CONSTRUTOR ====================
    public Compromisso(LocalDateTime dataHora, String descricao, Tipo tipo,
                       String funcionario, Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Compromisso deve ter um cliente.");

        this.idCompromisso = proximoId++;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.tipo = tipo;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.status = Status.A_CONFIRMAR;

        // vincular ao cliente
        cliente.adicionarCompromisso(this);

        // salvar no repositório
        RepositorioCompromisso.getInstance().salvar(this);
    }

    // ==================== MÉTODOS ====================
    public void agendar() {
        this.status = Status.A_CONFIRMAR;
        System.out.println("Compromisso agendado: " + descricao + " (A CONFIRMAR)");
    }

    public void confirmar() {
        if (status != Status.CANCELADO) {
            status = Status.CONFIRMADO;
            System.out.println("Compromisso confirmado: " + descricao);
        } else {
            System.out.println("Não é possível confirmar compromisso cancelado.");
        }
    }

    public void reagendar(LocalDateTime novaDataHora) {
        if (status != Status.CANCELADO) {
            this.dataHora = novaDataHora;
            this.status = Status.A_CONFIRMAR;
            System.out.println("Compromisso reagendado: " + descricao + " para " + novaDataHora);
        } else {
            System.out.println("Não é possível reagendar compromisso cancelado.");
        }
    }

    public void marcarRealizado() {
        if (status != Status.CANCELADO) {
            status = Status.REALIZADO;
            System.out.println("Compromisso realizado: " + descricao);
        } else {
            System.out.println("Não é possível marcar compromisso cancelado como realizado.");
        }
    }

    public void cancelar() {
        if (status != Status.REALIZADO) {
            status = Status.CANCELADO;
            System.out.println("Compromisso cancelado: " + descricao);
        } else {
            System.out.println("Não é possível cancelar compromisso já realizado.");
        }
    }

    public void atualizarDescricao(String novaDescricao) {
        if (status != Status.CANCELADO) {
            this.descricao = novaDescricao;
        } else {
            System.out.println("Não é possível atualizar compromisso cancelado.");
        }
    }

    // ==================== GETTERS E SETTERS ====================
    public int getIdCompromisso() { return idCompromisso; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getDescricao() { return descricao; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public String getFuncionario() { return funcionario; }
    public void setFuncionario(String funcionario) { this.funcionario = funcionario; }

    public Cliente getCliente() { return cliente; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }

    public Status getStatus() { return status; }

    public void exibirDetalhes() {
        System.out.println("\n=== DETALHES DO COMPROMISSO ===");
        System.out.println("ID: " + idCompromisso);
        System.out.println("Data/Hora: " + (dataHora != null ? dataHora : "Não definida"));
        System.out.println("Descrição: " + (descricao != null ? descricao : "Não informada"));
        System.out.println("Tipo: " + (tipo != null ? tipo : "Não informado"));
        System.out.println("Funcionário: " + (funcionario != null ? funcionario : "Não informado"));
        
        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNome());
        } else {
            System.out.println("Cliente: Não definido");
        }

        if (servico != null) {
            System.out.println("Serviço vinculado: " + servico.getDescricao());
        } else {
            System.out.println("Serviço vinculado: Nenhum");
        }

        System.out.println("Status: " + (status != null ? status : "Não definido"));
    }

}
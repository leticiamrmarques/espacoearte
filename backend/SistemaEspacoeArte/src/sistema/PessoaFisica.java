package sistema;

public class PessoaFisica extends Cliente {
	
    private String cpf;
    private String profissao;
    private String estadoCivil;
    
    // Constutor
    public PessoaFisica(String nome, String telefone, String email, String endereco,
                        String cpf, String profissao, String estadoCivil) {

        super(nome, telefone, email, endereco);
        this.cpf = cpf;
        this.profissao = profissao;
        this.estadoCivil = estadoCivil;
    }
    
    // Métodos
    public boolean validarCPF() {
        return Validador.validarCPF(this.cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public void consultarInfo() {
        System.out.println("\n=== Pessoa Física ===");
        System.out.println("ID: " + getIdCliente());
        System.out.println("Nome: " + getNome());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Email: " + getEmail());
        System.out.println("Endereço: " + getEndereco());
        System.out.println("CPF: " + cpf);
        System.out.println("Profissão: " + profissao);
        System.out.println("Estado Civil: " + estadoCivil);
    }
}

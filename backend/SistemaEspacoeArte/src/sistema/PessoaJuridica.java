package sistema;

public class PessoaJuridica extends Cliente {
	
    private String cnpj;
    
    // Construtor
    public PessoaJuridica(String nome, String telefone, String email, String endereco,
                          String cnpj) {

        super(nome, telefone, email, endereco);
        this.cnpj = cnpj;
    }
    
    // Métodos
    public boolean validarCNPJ() {
        return Validador.validarCNPJ(this.cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void consultarInfo() {
        System.out.println("\n=== Pessoa Jurídica ===");
        System.out.println("ID: " + getIdCliente());
        System.out.println("Nome Fantasia: " + getNome());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Email: " + getEmail());
        System.out.println("Endereço: " + getEndereco());
        System.out.println("CNPJ: " + cnpj);
    }
}

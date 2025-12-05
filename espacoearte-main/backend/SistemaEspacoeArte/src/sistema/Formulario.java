package sistema;

public class Formulario {
	
    private static int proximoId = 1;
	
    private int IdFormulario;
    private String nome;
    private String telefone;
    private String email;
    private String mensagem;
    private ComoConheceu comoConheceu;
    private boolean visualizado;

    public enum ComoConheceu {
        GOOGLE,
        INSTAGRAM,
        INDICACAO,
        OUTRO
    }

    // Construtor
    public Formulario(String nome, String telefone, String email,
                      String mensagem, ComoConheceu comoConheceu) {
        this.IdFormulario = proximoId++;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.mensagem = mensagem;
        this.comoConheceu = comoConheceu;
        this.visualizado = false;
    }

    public void enviar() {
        if (validarDados()) {
            RepositorioFormulario.getInstance().salvar(this);
            System.out.println("Formulario enviado com sucesso!");
            System.out.println("ID: " + IdFormulario);
        } else {
            System.out.println("Erro ao enviar: dados invalidos");
        }
    }

    public boolean validarDados() {
        if (!Validador.validarCampoVazio(nome)) {
            System.out.println("Nome nao pode estar vazio");
            return false;
        }
        if (!Validador.validarTelefone(telefone)) {
            System.out.println("Telefone invalido");
            return false;
        }
        if (!Validador.validarEmail(email)) {
            System.out.println("Email invalido");
            return false;
        }
        if (!Validador.validarCampoVazio(mensagem)) {
            System.out.println("Mensagem nao pode estar vazia");
            return false;
        }
        if (comoConheceu == null) {
            System.out.println("Como conheceu nao pode estar vazio");
            return false;
        }
        return true;
    }

    public void marcarVisualizado() {
        this.visualizado = true;
        System.out.println("Formulario marcado como visualizado");
    }

    public int getIDformulario() {
        return IdFormulario;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public ComoConheceu getComoConheceu() {
        return comoConheceu;
    }

    public boolean isVisualizado() {
        return visualizado;
    }
}
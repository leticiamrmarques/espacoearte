package sistema;

public class Curriculo {
	
    private static int proximoId = 1;    
    private int idCurriculo;
    
    private String nome;
    private String telefone;
    private String email;
    private String areaInteresse;
    private double pretensaoSalarial;
    private String mensagem;
    private ComoConheceu comoConheceu;
    private Documento anexo;
    private boolean visualizado;

    public enum ComoConheceu {
        GOOGLE,
        INSTAGRAM,
        INDICACAO,
        OUTRO
    }

    // Construtor
    public Curriculo(String nome, String telefone, String email,
                     String areaInteresse, double pretensaoSalarial, String mensagem,
                     ComoConheceu comoConheceu, Documento anexo) {
        this.idCurriculo = proximoId++;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.areaInteresse = areaInteresse;
        this.pretensaoSalarial = pretensaoSalarial;
        this.mensagem = mensagem;
        this.comoConheceu = comoConheceu;
        this.anexo = anexo;
        this.visualizado = false;
    }

    public void enviar() {
        if (validarDados()) {
            RepositorioCurriculos.salvar(this);
            System.out.println("Currículo enviado com sucesso!");
            System.out.println("Candidato: " + nome);
            if (anexo != null) {
                System.out.println("Anexo: " + anexo.getNome());
            }
        } else {
            System.out.println("Erro ao enviar: dados inválidos");
        }
    }

    public boolean validarDados() {
        if (!Validador.validarCampoVazio(nome)) return false;
        if (!Validador.validarTelefone(telefone)) return false;
        if (!Validador.validarEmail(email)) return false;
        if (!Validador.validarCampoVazio(areaInteresse)) return false;
        if (pretensaoSalarial <= 0) return false;
        if (comoConheceu == null) return false;
        return true;
    }

    public void marcarVisualizado() {
        this.visualizado = true;
    }

    public int getIdCurriculo() { return idCurriculo; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getAreaInteresse() { return areaInteresse; }
    public double getPretensaoSalarial() { return pretensaoSalarial; }
    public String getMensagem() { return mensagem; }
    public ComoConheceu getComoConheceu() { return comoConheceu; }
    public Documento getAnexo() { return anexo; }
    public boolean isVisualizado() { return visualizado; }
    
    // Método para definir anexo
    public void setAnexo(Documento anexo) {
        this.anexo = anexo;
    }
}
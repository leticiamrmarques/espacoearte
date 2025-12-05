package sistema;

import java.time.LocalDateTime;

public class Administrador {

    private String cpfAdm;
    private String nome;
    private String email;
    private String senha;
    private boolean logado = false;

    private RepositorioCliente repositorio = new RepositorioCliente();

    public Administrador(String cpfAdm, String nome, String email, String senha) {
        this.cpfAdm = cpfAdm;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // ===================== LOGIN =====================
    public boolean login(String cpf, String senhaFornecida) {
        if (this.cpfAdm.equals(cpf) && this.senha.equals(senhaFornecida)) {
            logado = true;
            System.out.println("Administrador " + nome + " logado com sucesso.");
            return true;
        } else {
            System.out.println("CPF ou senha incorretos. Login falhou.");
            return false;
        }
    }

    public boolean verificarLogin() {
        return logado;
    }

    // ===================== FORMULÁRIO / CURRÍCULO =====================
    public void visualizarFormulario(Formulario form) {
        System.out.println("\n=== Formulário ===");
        System.out.println("ID: " + form.getIDformulario());
        System.out.println("Nome: " + form.getNome());
        System.out.println("Email: " + form.getEmail());
        System.out.println("Telefone: " + form.getTelefone());
        System.out.println("Mensagem: " + form.getMensagem());
        System.out.println("Como conheceu: " + form.getComoConheceu());
        System.out.println("Visualizado: " + form.isVisualizado());
    }

    public void marcarFormularioVisualizado(Formulario form) {
        form.marcarVisualizado();
        System.out.println("Marcado como visualizado por: " + nome);
    }

    public void visualizarCurriculo(Curriculo curr) {
        System.out.println("\n=== Currículo ===");
        System.out.println("ID: " + curr.getIdCurriculo());
        System.out.println("Nome: " + curr.getNome());
        System.out.println("Email: " + curr.getEmail());
        System.out.println("Telefone: " + curr.getTelefone());
        System.out.println("Área de interesse: " + curr.getAreaInteresse());
        System.out.println("Pretensão salarial: " + curr.getPretensaoSalarial());
        System.out.println("Como conheceu: " + curr.getComoConheceu()); // Agora retorna enum
        
        // Exibir informações do anexo se existir
        if (curr.getAnexo() != null) {
            System.out.println("Anexo: " + curr.getAnexo().getNome());
            System.out.println("Caminho: " + curr.getAnexo().getCaminho());
        } else {
            System.out.println("Anexo: Não há anexo");
        }
        
        System.out.println("Visualizado: " + curr.isVisualizado());
    }

    public void marcarCurriculoVisualizado(Curriculo curr) {
        curr.marcarVisualizado();
        System.out.println("Marcado como visualizado por: " + nome);
    }

    // ===================== CLIENTES =====================
    // Criar PF manual
    public PessoaFisica criarClientePessoaFisica(String nome, String telefone,
                                                 String email, String endereco,
                                                 String cpf, String profissao, String estadoCivil) {
        if (!verificarLogin()) return null;
        if (!Validador.validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido.");
            return null;
        }
        if (!Validador.validarEmail(email)) {
            System.out.println("Erro: Email inválido.");
            return null;
        }
        if (!Validador.validarTelefone(telefone)) {
            System.out.println("Erro: Telefone inválido.");
            return null;
        }

        PessoaFisica pf = new PessoaFisica(nome, telefone, email, endereco, cpf, profissao, estadoCivil);
        repositorio.adicionar(pf);
        System.out.println("Pessoa Física cadastrada: " + pf.getNome());
        return pf;
    }

    // Criar PJ manual
    public PessoaJuridica criarClientePessoaJuridica(String nome, String telefone,
                                                     String email, String endereco,
                                                     String cnpj) {
        if (!verificarLogin()) return null;
        if (!Validador.validarCNPJ(cnpj)) {
            System.out.println("Erro: CNPJ inválido.");
            return null;
        }
        if (!Validador.validarEmail(email)) {
            System.out.println("Erro: Email inválido.");
            return null;
        }
        if (!Validador.validarTelefone(telefone)) {
            System.out.println("Erro: Telefone inválido.");
            return null;
        }

        PessoaJuridica pj = new PessoaJuridica(nome, telefone, email, endereco, cnpj);
        repositorio.adicionar(pj);
        System.out.println("Pessoa Jurídica cadastrada: " + pj.getNome());
        return pj;
    }

    // Criar PF a partir de formulário
    public PessoaFisica criarClientePessoaFisicaDoFormulario(Formulario form, String endereco,
                                                             String cpf, String profissao, String estadoCivil) {
        if (!verificarLogin()) return null;
        if (!Validador.validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido.");
            return null;
        }

        PessoaFisica pf = new PessoaFisica(form.getNome(), form.getTelefone(), form.getEmail(),
                                           endereco, cpf, profissao, estadoCivil);
        repositorio.adicionar(pf);
        System.out.println("Pessoa Física cadastrada via formulário: " + pf.getNome());
        return pf;
    }

    // Criar PJ a partir de formulário
    public PessoaJuridica criarClientePessoaJuridicaDoFormulario(Formulario form, String endereco,
                                                                 String cnpj) {
        if (!verificarLogin()) return null;
        if (!Validador.validarCNPJ(cnpj)) {
            System.out.println("Erro: CNPJ inválido.");
            return null;
        }

        PessoaJuridica pj = new PessoaJuridica(form.getNome(), form.getTelefone(), form.getEmail(),
                                               endereco, cnpj);
        repositorio.adicionar(pj);
        System.out.println("Pessoa Jurídica cadastrada via formulário: " + pj.getNome());
        return pj;
    }

    // Edição de PF
    public void editarDadosPessoaFisica(PessoaFisica cliente, String novaProfissao, String novoEstadoCivil) {
        if (!verificarLogin() || cliente == null) return;
        if (novaProfissao != null && Validador.validarCampoVazio(novaProfissao)) {
            cliente.setProfissao(novaProfissao);
        }
        if (novoEstadoCivil != null && Validador.validarCampoVazio(novoEstadoCivil)) {
            cliente.setEstadoCivil(novoEstadoCivil);
        }
        System.out.println("Pessoa Física atualizada: " + cliente.getNome());
    }

    // Edição de PJ
    public void editarDadosPessoaJuridica(PessoaJuridica cliente, String novoCnpj) {
        if (!verificarLogin() || cliente == null) return;
        if (novoCnpj != null && Validador.validarCNPJ(novoCnpj)) {
            cliente.setCnpj(novoCnpj);
            System.out.println("Pessoa Jurídica atualizada: " + cliente.getNome());
        }
    }

    // Consulta de cliente
    public void consultarCliente(Cliente cliente) {
        if (!verificarLogin() || cliente == null) return;
        cliente.consultarInfo();
    }

    // Exclusão de cliente
    public void excluirCliente(Cliente cliente) {
        if (!verificarLogin() || cliente == null) return;
        repositorio.remover(cliente);
        System.out.println("Cliente excluído: " + cliente.getNome());
    }

    // ===================== SERVIÇOS =====================
    public Servico criarServico(String descricao, String enderecoEntrega, double valorBase, Cliente cliente) {
        if (!verificarLogin()) return null;
        if (!Validador.validarCampoVazio(descricao)) {
            System.out.println("Erro: Descrição do serviço não pode estar vazia.");
            return null;
        }
        if (!Validador.validarValorPositivo(valorBase)) {
            System.out.println("Erro: Valor base deve ser positivo.");
            return null;
        }

        Servico servico = new Servico(descricao, enderecoEntrega, valorBase, cliente);
        System.out.println("Serviço criado com sucesso: " + descricao);
        return servico;
    }

    public void editarServico(Servico servico, String novoEndereco, Double novoValor) {
        if (!verificarLogin() || servico == null) return;
        if (novoEndereco != null) servico.setEnderecoEntrega(novoEndereco);
        if (novoValor != null && Validador.validarValorPositivo(novoValor)) servico.setValorBase(novoValor);
        System.out.println("Serviço editado com sucesso");
    }

    public void excluirServico(Servico servico) {
        if (!verificarLogin() || servico == null) return;
        servico.cancelar();
        System.out.println("Serviço excluído: " + servico.getDescricao());
    }

    // ===================== COMPROMISSOS =====================
    public Compromisso criarCompromisso(LocalDateTime dataHora, String descricao, Compromisso.Tipo tipo,
                                       String funcionario, Cliente cliente) {
        if (!verificarLogin() || cliente == null) return null;
        Compromisso comp = new Compromisso(dataHora, descricao, tipo, funcionario, cliente);
        comp.agendar();
        return comp;
    }

    public void vincularServicoAoCompromisso(Servico servico, Compromisso compromisso) {
        if (!verificarLogin() || servico == null || compromisso == null) return;
        servico.atribuirCompromisso(compromisso);
        compromisso.setServico(servico);
        System.out.println("Serviço vinculado: " + servico.getDescricao());
    }

    public void atualizarCompromisso(Compromisso comp, LocalDateTime novaDataHora, String novaDescricao) {
        if (!verificarLogin() || comp == null) return;
        if (novaDataHora != null) comp.reagendar(novaDataHora);
        if (novaDescricao != null) comp.atualizarDescricao(novaDescricao);
        System.out.println("Compromisso atualizado com sucesso");
    }

    public void cancelarCompromisso(Compromisso comp) {
        if (!verificarLogin() || comp == null) return;
        comp.cancelar();
        System.out.println("Compromisso cancelado por: " + nome);
    }

    public void marcarCompromissoRealizado(Compromisso comp) {
        if (!verificarLogin() || comp == null) return;
        comp.marcarRealizado();
        System.out.println("Compromisso marcado como realizado por: " + nome);
    }

    // ===================== GETTERS =====================
    public String getCpfAdm() { return cpfAdm; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}
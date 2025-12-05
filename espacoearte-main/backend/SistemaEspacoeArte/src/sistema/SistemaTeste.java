package sistema;

import java.time.LocalDateTime;

public class SistemaTeste {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("TESTE DO SISTEMA ESPACO E ARTE");
        System.out.println("=================================\n");

        Administrador adm = new Administrador("98765432100", "Eliane Marques",
                "eliane@espacoearte.com.br", "senha123");

        // ===================== TESTE DE LOGIN INCORRETO =====================
        adm.login("98765432100", "senhaErrada");

        // ===================== LOGIN CORRETO =====================
        if (!adm.login("98765432100", "senha123")) { 
            return;
        }

        // ===================== FLUXO 1: FORMULÁRIO E CRIAÇÃO DE CLIENTE PF VIA FORMULÁRIO =====================
        System.out.println("\n--- Passo 1: Cliente envia formulário ---");
        Formulario form1 = new Formulario("Maria Silva", "11987654321",
                "maria@email.com", "Gostaria de contratar serviço de marcenaria para cozinha",
                Formulario.ComoConheceu.INSTAGRAM);
        form1.enviar();

        System.out.println("\n--- Passo 2: Administrador visualiza formulário ---");
        adm.visualizarFormulario(form1);
        adm.marcarFormularioVisualizado(form1);

        System.out.println("\n--- Passo 3: Administrador cria cliente PF via formulário ---");
        PessoaFisica cliente1 = adm.criarClientePessoaFisicaDoFormulario(
                form1, "Rua das Flores, 123", "12345678909", "Professora", "Solteira"
        );

        if (cliente1 != null) {
            System.out.println("CPF válido? " + cliente1.validarCPF());
            adm.consultarCliente(cliente1);
        }

        // ===================== FLUXO 2: CRIAÇÃO E GESTÃO DE SERVIÇO =====================
        System.out.println("\n--- Passo 1: Administrador cria serviço ---");
        Servico servico1 = adm.criarServico("Decoração de ambiente residencial",
                cliente1.getEndereco(), 1500.0, cliente1);
        servico1.exibirDetalhes();

        System.out.println("\n--- Passo 2: Administrador edita serviço ---");
        adm.editarServico(servico1, "Rua das Flores, 123 - Apto 501", 1800.0);
        servico1.exibirDetalhes();

        // ===================== TESTE: ANEXAR DOCUMENTOS AO SERVIÇO =====================
        System.out.println("\n--- Passo 3: Anexar documentos ao serviço ---");
        Documento contrato = new Documento("Contrato de Serviço", "/docs/contrato_001.pdf");
        Documento projeto = new Documento("Projeto 3D", "/docs/projeto_3d.dwg");
        Documento orcamento = new Documento("Orçamento Detalhado", "/docs/orcamento.xlsx");
        
        servico1.anexarDocumento(contrato);
        servico1.anexarDocumento(projeto);
        servico1.anexarDocumento(orcamento);
        
        servico1.exibirDetalhes();

        // ===================== FLUXO 3: CRIAÇÃO E GESTÃO DE COMPROMISSO =====================
        System.out.println("\n--- Passo 1: Administrador cria compromisso ---");
        LocalDateTime dataCompromisso = LocalDateTime.of(2025, 12, 15, 14, 30);
        Compromisso comp1 = adm.criarCompromisso(dataCompromisso,
                "Reunião para projeto de decoração", 
                Compromisso.Tipo.REUNIAO,
                "Carlos Mendes", cliente1);
        comp1.exibirDetalhes();

        System.out.println("\n--- Passo 2: Vinculando serviço ao compromisso ---");
        adm.vincularServicoAoCompromisso(servico1, comp1);

        System.out.println("\n--- Passo 3: Administrador reagenda compromisso ---");
        LocalDateTime novaData = LocalDateTime.of(2025, 12, 16, 10, 0);
        comp1.reagendar(novaData);

        System.out.println("\n--- Passo 4: Marcando compromisso como confirmado ---");
        comp1.confirmar();
        comp1.exibirDetalhes();

        // ===================== TESTE: DIFERENTES TIPOS DE COMPROMISSO =====================
        System.out.println("\n--- Testando diferentes tipos de compromisso ---");
        
        Compromisso compOrcamento = adm.criarCompromisso(
                LocalDateTime.of(2025, 12, 10, 9, 0),
                "Apresentação de orçamento inicial",
                Compromisso.Tipo.ORCAMENTO,
                "Ana Oliveira", cliente1);
        System.out.println("Criado: " + compOrcamento.getTipo());
        
        Compromisso compMedidas = adm.criarCompromisso(
                LocalDateTime.of(2025, 12, 18, 14, 0),
                "Tirar medidas do ambiente",
                Compromisso.Tipo.MEDIDAS,
                "Pedro Santos", cliente1);
        System.out.println("Criado: " + compMedidas.getTipo());
        
        Compromisso compEntrega = adm.criarCompromisso(
                LocalDateTime.of(2026, 1, 30, 16, 0),
                "Entrega do projeto finalizado",
                Compromisso.Tipo.ENTREGA,
                "Carlos Mendes", cliente1);
        System.out.println("Criado: " + compEntrega.getTipo());

        // ===================== FLUXO 4: CURRÍCULO =====================
        System.out.println("\n--- Passo 1: Candidato envia currículo ---");
        
        // Criar documento para o currículo
        Documento curriculoDoc = new Documento("Currículo - Ana Costa", "/uploads/curriculo_ana.pdf");
        
        Curriculo curr1 = new Curriculo("Ana Costa", "11988887777",
                "ana@email.com", "Projetos de interiores", 2500.0,
                "Tenho 3 anos de experiência", 
                Curriculo.ComoConheceu.GOOGLE,
                curriculoDoc); 
        curr1.enviar();

        System.out.println("\n--- Passo 2: Administrador visualiza currículo ---");
        adm.visualizarCurriculo(curr1);
        adm.marcarCurriculoVisualizado(curr1);

        // ===================== TESTE: CURRÍCULO COM DIFERENTES FONTES =====================
        System.out.println("\n--- Testando currículos com diferentes fontes ---");
        
        Curriculo curr2 = new Curriculo("João Silva", "11977776666",
                "joao@email.com", "Designer", 3000.0,
                "5 anos de experiência", 
                Curriculo.ComoConheceu.GOOGLE,
                new Documento("CV - João Silva", "/uploads/joao.pdf"));
        curr2.enviar();
        System.out.println("Como conheceu: " + curr2.getComoConheceu());
        
        Curriculo curr3 = new Curriculo("Paula Lima", "11966665555",
                "paula@email.com", "Arquiteta", 5000.0,
                "10 anos de experiência", 
                Curriculo.ComoConheceu.INDICACAO,
                null); // Sem documento
        curr3.enviar();
        System.out.println("Como conheceu: " + curr3.getComoConheceu());

        // ===================== FLUXO 5: CLIENTE PJ VIA FORMULÁRIO =====================
        System.out.println("\n--- Passo 1: Empresa envia formulário ---");
        Formulario form2 = new Formulario("Empresa XYZ Ltda", "1133334444",
                "contato@xyz.com.br", "Interesse em móveis planejados para ambiente corporativo",
                Formulario.ComoConheceu.INDICACAO);
        form2.enviar();

        System.out.println("\n--- Passo 2: Administrador visualiza formulário ---");
        adm.visualizarFormulario(form2);
        adm.marcarFormularioVisualizado(form2);

        System.out.println("\n--- Passo 3: Administrador cria cliente PJ via formulário ---");
        PessoaJuridica cliente2 = adm.criarClientePessoaJuridicaDoFormulario(
                form2, "102 norte, 1000", "12345678000195"
        );

        if (cliente2 != null) {
            System.out.println("CNPJ válido? " + cliente2.validarCNPJ());
            adm.consultarCliente(cliente2);
        }

        // ===================== FLUXO 6: CRIAÇÃO MANUAL DE CLIENTES =====================
        System.out.println("\n--- Passo 1: Criação manual de cliente PF ---");
        PessoaFisica pfManual = adm.criarClientePessoaFisica(
                "Joana", "11987654325", "joana@email.com",
                "106 sul, 123", "12345678911", "Médica", "Casada"
        );

        System.out.println("\n--- Passo 2: Criação manual de cliente PJ ---");
        PessoaJuridica pjManual = adm.criarClientePessoaJuridica(
                "Empresa XYZW Ltda", "1133335555", "contato@xyzw.com.br",
                "912 sul, 1000", "12345678000195"
        );

        System.out.println("\n--- Passo 3: Criação de cliente PF via formulário ---");
        Formulario formPF = new Formulario("Joao Santos", "11977776666",
                "joao@email.com", "Desejo contratar serviços de decoração", 
                Formulario.ComoConheceu.INSTAGRAM);
        formPF.enviar();
        PessoaFisica pfForm = adm.criarClientePessoaFisicaDoFormulario(
                formPF, "504 sul, 456", "98765432109", "Advogado", "Casado"
        );

        System.out.println("\n--- Passo 4: Criação de cliente PJ via formulário ---");
        Formulario formPJ = new Formulario("Empresa ABC Ltda", "11988887777",
                "contato@abc.com.br", "Interessada em serviços corporativos", 
                Formulario.ComoConheceu.GOOGLE);
        formPJ.enviar();
        PessoaJuridica pjForm = adm.criarClientePessoaJuridicaDoFormulario(
                formPJ, "104 norte, 100", "98765432000100"
        );

        System.out.println("\n--- Passo 5: Edição de clientes ---");
        if (pfManual != null) {
            adm.editarDadosPessoaFisica(pfManual, "Enfermeira", "Casada");
        }
        if (pjManual != null) {
            adm.editarDadosPessoaJuridica(pjManual, "12345678000100");
        }

        System.out.println("\n--- Passo 6: Consulta de clientes ---");
        if (pfManual != null) adm.consultarCliente(pfManual);
        if (pfForm != null) adm.consultarCliente(pfForm);
        if (pjManual != null) adm.consultarCliente(pjManual);
        if (pjForm != null) adm.consultarCliente(pjForm);

        System.out.println("\n--- Passo 7: Exclusão de clientes ---");
        if (pfForm != null) adm.excluirCliente(pfForm);
        if (pjForm != null) adm.excluirCliente(pjForm);

        // ===================== FLUXO 7: CANCELAMENTO DE COMPROMISSO =====================
        System.out.println("\n--- Teste de cancelamento de compromisso ---");
        Compromisso comp2 = adm.criarCompromisso(LocalDateTime.now().plusDays(5),
                "Consultoria empresarial", 
                Compromisso.Tipo.REUNIAO,
                "Pedro Silva", cliente2);
        comp2.exibirDetalhes();
        comp2.cancelar();

        // ===================== TESTE: MARCAR COMPROMISSO COMO REALIZADO =====================
        System.out.println("\n--- Teste: Marcar compromisso como realizado ---");
        adm.marcarCompromissoRealizado(compOrcamento);
        compOrcamento.exibirDetalhes();

        // ===================== TESTE: ATUALIZAR COMPROMISSO =====================
        System.out.println("\n--- Teste: Atualizar compromisso ---");
        adm.atualizarCompromisso(compMedidas, 
                LocalDateTime.of(2025, 12, 19, 10, 0), 
                "Medidas finais do ambiente - atualizado");
        compMedidas.exibirDetalhes();

        // ===================== TESTE: REMOVER DOCUMENTO DO SERVIÇO =====================
        System.out.println("\n--- Teste: Remover documento do serviço ---");
        servico1.removerDocumento(orcamento);
        servico1.exibirDetalhes();

        // ===================== TESTE: BUSCAR DOCUMENTO POR NOME =====================
        System.out.println("\n--- Teste: Buscar documento por nome ---");
        Documento docEncontrado = servico1.buscarDocumentoPorNome("Contrato de Serviço");
        if (docEncontrado != null) {
            System.out.println("Documento encontrado: " + docEncontrado.getNome());
            System.out.println("Caminho: " + docEncontrado.getCaminho());
        }

        // ===================== TESTE: TODAS AS OPÇÕES DE ENUM =====================
        System.out.println("\n--- Teste: Listando todas as opções de enums ---");
        
        System.out.println("\nTipos de Compromisso:");
        for (Compromisso.Tipo tipo : Compromisso.Tipo.values()) {
            System.out.println("  - " + tipo);
        }
        
        System.out.println("\nStatus de Compromisso:");
        for (Compromisso.Status status : Compromisso.Status.values()) {
            System.out.println("  - " + status);
        }
        
        System.out.println("\nStatus de Serviço:");
        for (Servico.Status status : Servico.Status.values()) {
            System.out.println("  - " + status);
        }
        
        System.out.println("\nOpções 'Como Conheceu' (Formulário):");
        for (Formulario.ComoConheceu opcao : Formulario.ComoConheceu.values()) {
            System.out.println("  - " + opcao);
        }
        
        System.out.println("\nOpções 'Como Conheceu' (Currículo):");
        for (Curriculo.ComoConheceu opcao : Curriculo.ComoConheceu.values()) {
            System.out.println("  - " + opcao);
        }

        // ===================== TESTES DE VALIDAÇÃO =====================
        System.out.println("\n--- TESTES DE VALIDAÇÃO ---");
        System.out.println("\nEmail válido: " + Validador.validarEmail("maria@email.com"));
        System.out.println("Email inválido: " + Validador.validarEmail("maria@"));

        System.out.println("\nTelefone válido: " + Validador.validarTelefone("11987654321"));
        System.out.println("Telefone inválido: " + Validador.validarTelefone("123"));

        System.out.println("\nCPF válido: " + Validador.validarCPF("12345678909"));
        System.out.println("CPF inválido: " + Validador.validarCPF("11111111111"));

        System.out.println("\nCNPJ válido: " + Validador.validarCNPJ("12345678000195"));
        System.out.println("CNPJ inválido: " + Validador.validarCNPJ("00000000000000"));

        System.out.println("\nValor positivo válido: " + Validador.validarValorPositivo(1500.0));
        System.out.println("Valor negativo inválido: " + Validador.validarValorPositivo(-100.0));

        System.out.println("\nCampo não vazio: " + Validador.validarCampoVazio("Texto"));
        System.out.println("Campo vazio: " + Validador.validarCampoVazio(""));

        // ===================== TESTE: FLUXO COMPLETO DE UM SERVIÇO =====================
        System.out.println("\n--- TESTE: FLUXO COMPLETO DE UM SERVIÇO ---");
        
        // 1. Formulário chega
        Formulario formCompleto = new Formulario(
                "Roberto Alves", "11955554444", "roberto@email.com",
                "Quero reformar meu apartamento completo",
                Formulario.ComoConheceu.OUTRO);
        formCompleto.enviar();
        adm.visualizarFormulario(formCompleto);
        adm.marcarFormularioVisualizado(formCompleto);
        
        // 2. Cliente é criado
        PessoaFisica clienteCompleto = adm.criarClientePessoaFisicaDoFormulario(
                formCompleto, "Rua das Palmeiras, 789", "12345678909", "Engenheiro", "Casado");
        
        // Verificar se o cliente foi criado com sucesso antes de continuar
        if (clienteCompleto == null) {
            System.out.println("Erro: Cliente não foi criado. Encerrando teste de fluxo completo.");
            System.out.println("\n===========================================");
            System.out.println("TESTES CONCLUÍDOS COM ALGUNS ERROS!");
            System.out.println("===========================================");
            return;
        }
        
        // 3. Serviço é criado
        Servico servicoCompleto = adm.criarServico(
                "Reforma completa de apartamento", 
                clienteCompleto.getEndereco(), 
                35000.0, 
                clienteCompleto);
        
        // 4. Documentos são anexados
        servicoCompleto.anexarDocumento(new Documento("Contrato", "/docs/contrato_reforma.pdf"));
        servicoCompleto.anexarDocumento(new Documento("Projeto Arquitetônico", "/docs/projeto.dwg"));
        
        // 5. Compromissos são criados
        Compromisso c1 = adm.criarCompromisso(
                LocalDateTime.now().plusDays(2), "Orçamento inicial",
                Compromisso.Tipo.ORCAMENTO, "Ana Lima", clienteCompleto);
        
        Compromisso c2 = adm.criarCompromisso(
                LocalDateTime.now().plusDays(5), "Reunião aprovação projeto",
                Compromisso.Tipo.REUNIAO, "Ana Lima", clienteCompleto);
        
        Compromisso c3 = adm.criarCompromisso(
                LocalDateTime.now().plusDays(7), "Medidas finais",
                Compromisso.Tipo.MEDIDAS, "Carlos Tech", clienteCompleto);
        
        Compromisso c4 = adm.criarCompromisso(
                LocalDateTime.now().plusDays(60), "Entrega da obra",
                Compromisso.Tipo.ENTREGA, "Ana Lima", clienteCompleto);
        
        // 6. Vincular compromissos ao serviço
        adm.vincularServicoAoCompromisso(servicoCompleto, c1);
        adm.vincularServicoAoCompromisso(servicoCompleto, c2);
        adm.vincularServicoAoCompromisso(servicoCompleto, c3);
        adm.vincularServicoAoCompromisso(servicoCompleto, c4);
        
        // 7. Exibir detalhes finais
        System.out.println("\n=== RESUMO DO SERVIÇO COMPLETO ===");
        servicoCompleto.exibirDetalhes();
        System.out.println("\nCompromissos vinculados:");
        System.out.println("1. " + c1.getTipo() + " - " + c1.getDescricao() + " - " + c1.getStatus());
        System.out.println("2. " + c2.getTipo() + " - " + c2.getDescricao() + " - " + c2.getStatus());
        System.out.println("3. " + c3.getTipo() + " - " + c3.getDescricao() + " - " + c3.getStatus());
        System.out.println("4. " + c4.getTipo() + " - " + c4.getDescricao() + " - " + c4.getStatus());

        System.out.println("\n===========================================");
        System.out.println("TODOS OS TESTES CONCLUÍDOS COM SUCESSO!");
        System.out.println("===========================================");
    }
}
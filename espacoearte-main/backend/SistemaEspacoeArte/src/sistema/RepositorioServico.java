package sistema;

import java.util.ArrayList;
import java.util.List;

public class RepositorioServico {

    private static RepositorioServico instance;
    private List<Servico> servicos;

    private RepositorioServico() {
        servicos = new ArrayList<>();
    }

    public static RepositorioServico getInstance() {
        if (instance == null) {
            instance = new RepositorioServico();
        }
        return instance;
    }

    public void salvar(Servico servico) {
        servicos.add(servico);
    }

    public void remover(int id) {
        servicos.removeIf(s -> s.getIdServico() == id);
    }

    public Servico buscarPorId(int id) {
        return servicos.stream()
                .filter(s -> s.getIdServico() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Servico> listar() {
        return servicos;
    }
}


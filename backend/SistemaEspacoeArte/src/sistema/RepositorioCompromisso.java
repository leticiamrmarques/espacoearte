package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCompromisso {

    private static RepositorioCompromisso instance;
    private List<Compromisso> compromissos;

    private RepositorioCompromisso() {
        compromissos = new ArrayList<>();
    }

    public static RepositorioCompromisso getInstance() {
        if (instance == null) {
            instance = new RepositorioCompromisso();
        }
        return instance;
    }

    public void salvar(Compromisso c) {
        compromissos.add(c);
    }

    public void remover(int id) {
        compromissos.removeIf(c -> c.getIdCompromisso() == id);
    }

    public boolean existeEm(LocalDateTime dataHora, String funcionario) {
        return compromissos.stream()
                .anyMatch(c -> c.getDataHora().equals(dataHora)
                             && c.getFuncionario().equals(funcionario));
    }

    public List<Compromisso> listar() {
        return compromissos;
    }

    public Compromisso buscarPorId(int id) {
        return compromissos.stream()
                .filter(c -> c.getIdCompromisso() == id)
                .findFirst()
                .orElse(null);
    }
}

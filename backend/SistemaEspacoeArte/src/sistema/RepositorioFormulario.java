package sistema;

import java.util.ArrayList;
import java.util.List;

public class RepositorioFormulario {

    private static RepositorioFormulario instance;
    private List<Formulario> formularios;

    private RepositorioFormulario() {
        formularios = new ArrayList<>();
    }

    public static RepositorioFormulario getInstance() {
        if (instance == null) {
            instance = new RepositorioFormulario();
        }
        return instance;
    }

    public void salvar(Formulario f) {
        formularios.add(f);
    }

    public List<Formulario> listar() {
        return formularios;
    }

    public Formulario buscarPorId(int id) {
        return formularios.stream()
                .filter(f -> f.getIDformulario() == id)
                .findFirst()
                .orElse(null);
    }
}

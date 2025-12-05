package sistema;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCurriculos {
    private static final List<Curriculo> curriculos = new ArrayList<>();

    public static void salvar(Curriculo c) {
        curriculos.add(c);
    }

    public static List<Curriculo> listar() {
        return curriculos;
    }
}

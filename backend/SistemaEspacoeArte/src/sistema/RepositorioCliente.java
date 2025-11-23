package sistema;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCliente {
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getIdCliente() == id) {
                return c;
            }
        }
        return null;
    }

    public void remover(Cliente cliente) {
        clientes.remove(cliente);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}

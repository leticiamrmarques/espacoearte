package sistema;

public class Validador {

    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }
        
        String telefoneNumeros = telefone.replaceAll("[^0-9]", "");
        return telefoneNumeros.length() >= 10 && telefoneNumeros.length() <= 11;
    }

    public static boolean validarCampoVazio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }

    //Valida o CPF
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }
        
        // Remove caracteres não numéricos
        String cpfNumeros = cpf.replaceAll("[^0-9]", "");
        
        if (cpfNumeros.length() != 11) {
            return false;
        }

        // Verifica CPFs inválidos conhecidos
        if (cpfNumeros.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validação dos dígitos verificadores
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpfNumeros.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpfNumeros.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return Character.getNumericValue(cpfNumeros.charAt(9)) == primeiroDigito &&
               Character.getNumericValue(cpfNumeros.charAt(10)) == segundoDigito;
    }

    //Valida o CNPJ 
    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }

        String cnpjNumeros = cnpj.replaceAll("[^0-9]", "");

        if (cnpjNumeros.length() != 14) {
            return false;
        }

        // Verifica CNPJs inválidos (todos dígitos iguais)
        if (cnpjNumeros.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Cálculo do primeiro dígito
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpjNumeros.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int primeiroDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

        // Cálculo do segundo dígito
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpjNumeros.charAt(i)) * peso;
            peso = (peso == 2) ? 9 : peso - 1;
        }
        int segundoDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

        return Character.getNumericValue(cnpjNumeros.charAt(12)) == primeiroDigito &&
               Character.getNumericValue(cnpjNumeros.charAt(13)) == segundoDigito;
    }

    //Valida valor monetário positivo
     
    public static boolean validarValorPositivo(double valor) {
        return valor > 0;
    }
}
package application;

import db.DB;
import exemplos.Departamento;
import exemplos.Transacoes;
import exemplos.Vendedor;

public class Program {

    public static void main(String[] args) {

        Departamento.consultarDepartamentos();
        Vendedor.inserirVendedor();
        Vendedor.atualizarSalario();
        Departamento.deletarDepartamento();
        Transacoes.realizarTransacao();
        DB.closeConnection();
    }
}
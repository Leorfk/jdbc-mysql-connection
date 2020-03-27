package application;

import db.DB;
import exemplos.Departamento;
import exemplos.Vendedor;

public class Program {

    public static void main(String[] args) {

        Departamento.consultarDepartamentos();
        Vendedor.inserirVendedor();
        Vendedor.atualizarSalario();

        DB.closeConnection();
    }
}
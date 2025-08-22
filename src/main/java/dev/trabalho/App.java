package dev.trabalho;

import dev.trabalho.dao.cliente.ClienteDAO;
import dev.trabalho.dao.contrato.ContratoDAO;
import dev.trabalho.dao.imovel.ImovelDAO;

/**
 * Hello world!
 */
public class App {

    public static ClienteDAO clienteDAO = new ClienteDAO();
    public static ImovelDAO imovelDAO = new ImovelDAO();
    public static ContratoDAO contratoDAO = new ContratoDAO();

    public static void main(String[] args) {

        //Cliente cliente = new Cliente(null, "1234", "1234", "felipe.pilz@univile.br", "Casa minha");


        //clienteDAO.create(cliente);

        /*
        imovelDAO.create(new Imovel(
                "Rua das Flores, 123",
                "Apartamento",
                true
        ));
         */

        /*
        contratoDAO.create(new Contrato(
                1,
                1,
                Date.valueOf("2025-09-01"),
                Date.valueOf("2026-08-31"),
                new BigDecimal("2500.00"),
                true,
                "Contrato de teste para apartamento"
        ));
         */


        System.out.println(clienteDAO.find());
        System.out.println(imovelDAO.find());
        System.out.println(contratoDAO.find());
    }
}

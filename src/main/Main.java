package main;

import db.ConexaoBD;
import model.RelatorioPrioridade;
import model.TrechoRodovia;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {

            System.out.println("=== TESTE DE CONEXAO ===");

            Connection conexao = ConexaoBD
                    .getInstancia()
                    .conectar();

            if (conexao != null && !conexao.isClosed()) {
                System.out.println("Conexao com Oracle realizada com sucesso!");
            }

            ConexaoBD.getInstancia().desconectar();

            System.out.println("Conexao encerrada.");

        } catch (Exception e) {

            System.out.println("Erro ao conectar com Oracle:");
            System.out.println(e.getMessage());
        }
    }
}
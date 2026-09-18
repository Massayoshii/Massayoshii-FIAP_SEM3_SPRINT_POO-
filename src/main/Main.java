package main;

import dao.RelatorioPrioridadeDAO;
import dao.TrechoRodoviaDAO;
import db.ConexaoBD;
import model.RelatorioPrioridade;
import model.TrechoRodovia;
import service.GeradorRelatorio;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConexaoBD conexao = ConexaoBD.getInstancia();

        try {
            System.out.println("==========================================");
            System.out.println("        TESTE DE CONEXAO COM ORACLE");
            System.out.println("==========================================");

            conexao.conectar();

            System.out.println("Conexao realizada com sucesso!");
            System.out.println("Banco conectado: " + conexao.estaConectado());

            TrechoRodoviaDAO trechoDAO = new TrechoRodoviaDAO();
            RelatorioPrioridadeDAO relatorioDAO =
                    new RelatorioPrioridadeDAO();

            System.out.println("\n==========================================");
            System.out.println("             INSERINDO TRECHOS");
            System.out.println("==========================================");

            TrechoRodovia trecho1 =
                    new TrechoRodovia(10, 15, 30, "umido");

            TrechoRodovia trecho2 =
                    new TrechoRodovia(20, 25, 18, "seco");

            TrechoRodovia trecho3 =
                    new TrechoRodovia(30, 35, 8, "seco");

            Long idTrecho1 = trechoDAO.inserir(trecho1);
            Long idTrecho2 = trechoDAO.inserir(trecho2);
            Long idTrecho3 = trechoDAO.inserir(trecho3);

            System.out.println(
                    "Trecho 1 inserido. ID: " + idTrecho1
            );

            System.out.println(
                    "Trecho 2 inserido. ID: " + idTrecho2
            );

            System.out.println(
                    "Trecho 3 inserido. ID: " + idTrecho3
            );

            System.out.println("\n==========================================");
            System.out.println("          TRECHOS CADASTRADOS");
            System.out.println("==========================================");

            List<TrechoRodovia> trechos =
                    trechoDAO.listarTodas();

            for (TrechoRodovia trecho : trechos) {

                System.out.println(
                        "ID: " + trecho.getId()
                                + " | "
                                + trecho
                );
            }

            System.out.println("\n==========================================");
            System.out.println("          BUSCANDO TRECHO POR ID");
            System.out.println("==========================================");

            TrechoRodovia trechoEncontrado =
                    trechoDAO.buscarPorId(idTrecho1);

            if (trechoEncontrado != null) {

                System.out.println("Trecho encontrado:");
                System.out.println(trechoEncontrado);

            } else {

                System.out.println("Trecho nao encontrado.");
            }

            System.out.println("\n==========================================");
            System.out.println("             ATUALIZANDO TRECHO");
            System.out.println("==========================================");

            trechoEncontrado.registrarCrescimento(10);

            boolean atualizado =
                    trechoDAO.atualizar(trechoEncontrado);

            System.out.println(
                    "Trecho atualizado? " + atualizado
            );

            System.out.println("\n==========================================");
            System.out.println("       TRECHO APOS ATUALIZACAO");
            System.out.println("==========================================");

            TrechoRodovia trechoAtualizado =
                    trechoDAO.buscarPorId(idTrecho1);

            System.out.println(trechoAtualizado);

            System.out.println("\n==========================================");
            System.out.println("             GERANDO RELATORIO");
            System.out.println("==========================================");

            GeradorRelatorio gerador =
                    new GeradorRelatorio();

            TrechoRodovia[] trechosParaRelatorio = {
                    trechoAtualizado,
                    trecho2,
                    trecho3
            };

            RelatorioPrioridade relatorio =
                    gerador.gerarRelatorio(
                            trechosParaRelatorio
                    );

            System.out.println("\n==========================================");
            System.out.println("          SALVANDO RELATORIO");
            System.out.println("==========================================");

            Long idRelatorio =
                    relatorioDAO.inserir(relatorio);

            System.out.println(
                    "Relatorio salvo com ID: "
                            + idRelatorio
            );

            System.out.println("\n==========================================");
            System.out.println("          BUSCANDO RELATORIO");
            System.out.println("==========================================");

            RelatorioPrioridade relatorioEncontrado =
                    relatorioDAO.buscarPorId(idRelatorio);

            if (relatorioEncontrado != null) {

                System.out.println(relatorioEncontrado);

            } else {

                System.out.println(
                        "Relatorio nao encontrado."
                );
            }

            System.out.println("\n==========================================");
            System.out.println("       HISTORICO DE RELATORIOS");
            System.out.println("==========================================");

            List<RelatorioPrioridade> relatorios =
                    relatorioDAO.listarTodas();

            for (RelatorioPrioridade item : relatorios) {

                System.out.println(item);
            }

            System.out.println("\n==========================================");
            System.out.println("             DELETANDO TRECHO");
            System.out.println("==========================================");

            boolean deletado =
                    trechoDAO.deletar(idTrecho3);

            System.out.println(
                    "Trecho deletado? " + deletado
            );

            TrechoRodovia trechoDeletado =
                    trechoDAO.buscarPorId(idTrecho3);

            if (trechoDeletado == null) {

                System.out.println(
                        "Exclusao confirmada."
                );

            } else {

                System.out.println(
                        "Trecho ainda existe."
                );
            }

            System.out.println("\n==========================================");
            System.out.println("        TESTE FINALIZADO COM SUCESSO");
            System.out.println("==========================================");

        } catch (Exception e) {

            System.out.println("\n==========================================");
            System.out.println("             ERRO NA EXECUCAO");
            System.out.println("==========================================");

            e.printStackTrace();

        } finally {

            try {

                conexao.desconectar();

                System.out.println(
                        "\nConexao encerrada."
                );

            } catch (Exception e) {

                System.out.println(
                        "Erro ao encerrar conexao."
                );

                e.printStackTrace();
            }
        }
    }
}
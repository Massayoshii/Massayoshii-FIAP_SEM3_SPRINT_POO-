package model;

public class RelatorioPrioridade {
    public void gerarRelatorio(TrechoRodovia [] trechos){
        System.out.println("\n=== RELATORIO DE PRIORIDADE === ");
        for (TrechoRodovia trecho : trechos){
            System.out.println(trecho);

            if(trecho.getNivelVegetacaoCm() >= 25){
                System.out.println("\nPRIORIDADE ALTA -> Roçada mecanizada");

                IntervencaoOperacional rocada = new RocadaMecanizada();
                rocada.executarServico();
            }else if (trecho.getNivelVegetacaoCm() >= 12){
                System.out.println("\nPRIORIDADE MEDIA -> Pulverizacao");
                IntervencaoOperacional pulverizacao = new Pulverizacao();
                pulverizacao.executarServico();
            }else {
                System.out.println("\nSem necessidade de intervencao");
            }
        }
    }
}

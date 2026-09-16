# Perguntas de Reflexão

## Por que não faz sentido para a Motiva que uma equipe execute apenas uma "Intervenção Operacional" genérica sem especificar qual é?

Porque a equipe precisa saber exatamente qual serviço será realizado, como roçada mecanizada ou pulverização. Uma intervenção genérica não informa os equipamentos, procedimentos e recursos necessários para a execução do serviço.

---

## Qual a diferença arquitetural entre fazer um Trecho herdar de uma classe abstrata vs. implementar uma Interface?

A herança de uma classe abstrata representa uma relação de especialização, onde a classe filha é um tipo da classe base. Já a implementação de uma interface representa apenas um comportamento ou capacidade que a classe possui.

No projeto:
- `RocadaMecanizada` herda de `IntervencaoOperacional` porque é um tipo de intervenção.
- `TrechoRodovia` implementa `MonitoravelViaIoT` porque possui a capacidade de transmitir dados via sensores IoT.

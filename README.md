# Descrição:
Trabalho final da disciplina de Programação I do IFMG Campus Formiga, cujo objetivo era o desenvolvimento de um jogo da memória em Java, que possui-se um CRUD simples e utilizasse Orientação a Objetos, executado no terminal. Entretanto, a proposta foi aprimorada mediante a implementação de interfaces gráficas. 

## A despeito das interfaces gráficas:
Fora utilizada a biblioteca padrão do Java para UI, o swing. Tendo sido utilizado um modelo de Tela (IFrame) principal, que utiliza CardLayout para mudar os componentes da tela, com essa mudança sendo feita por meio dos botões do menu principal. 

### Especificamente o JPanel JogarPanel:
Esse em específico utiliza outro CardLayout, sendo três Cards, um para escolher a dificuldade, outro para mostrar os números sorteados por 10 segundos e outro para permitir que o usuário insere os números sorteados.

Também, foram utilizados JDialogs para fornecer informações básicas, mensagens de alerta e requisição ao usuário, além de mensagens de confirmação.

## A despeito de algoritmos clássicos:
Fora utilizada um '*Mange Sort*' para organizar os pontos dos jogadores, procurando por suas pontuações em seus jogos; dessa forma calculando o Ranque das maiores médias de pontuações dos jogadores.

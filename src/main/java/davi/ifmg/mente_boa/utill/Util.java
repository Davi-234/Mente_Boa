package davi.ifmg.mente_boa.utill;

import davi.ifmg.mente_boa.model.Jogador;
import davi.ifmg.mente_boa.model.Ranque;
import davi.ifmg.mente_boa.service.JogadorService;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JTextField;

public class Util {

    public static String limaprCPF(String cpf) {
        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("\\D", ""); // '\\D' é um 'regex' que significa qualquer caractere que não é uma dígito, assim, quando identificadi, este é substituido por "".      
    }

    public static String formatarCPF(String cpf) {

        cpf = limaprCPF(cpf);

        return cpf.substring(0, 3) + "."
                + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-"
                + cpf.substring(9, 11);

    }

    public static boolean isCPFValido(String cpf) {
        String invalidosCPF[] = {"111.111.111-11", "222.222.222-22", "333.333.333-33", "444.444.444-44", "555.555.555-55", "666.666.666-66", "777.777.777-77",
             "888.888.888-88", "999.999.999-99"};

        //.replace retira os "." e o "-";
        String cpfNumeros = limaprCPF(cpf);

        for (int i = 0; i < invalidosCPF.length; i++) {
            String cpfInvaalido = limaprCPF(invalidosCPF[i]);

            if (cpfNumeros.equals(cpfInvaalido)) {
                return false;
            }
        }

        //Variaveis locais do método de validar o CPF:
        int somatorioDigito1 = 0;
        int somatorioDigito2 = 0;
        int primeiroDigitoCalculo = 0;
        int segundoDigitoCalculo = 0;

        //Garante que o cpf tenha 11 Digitos:
        if (cpfNumeros.length() != 11) {
            return false;
        }

        //Calculo do primeiro Digito verificador:
        for (int h = 0, peso = 10; h < 9; h++, peso--) {
            int ValorNumericoDoCPF = Character.getNumericValue(cpfNumeros.charAt(h));
            //Character.getNumericValue, converte char do tipo '6' para um númerot: 6
            //charAt lê cada caractere de uma string, começando do 0 até o maxímo de caracteres da String menos 1;
            somatorioDigito1 += (ValorNumericoDoCPF * peso);
        }
        primeiroDigitoCalculo = (somatorioDigito1 % 11 < 2) ? 0 : 11 - (somatorioDigito1 % 11);
        //If/else resumido em uma linha(Elvis operator). '?0' é igual à '= 0'/ ':' é igual à 'else',"senão".

        //Calculo do segundo digito verificador:
        for (int h = 0, peso = 11; h < 10; h++, peso--) {
            int valorNumericoDoCPF = Character.getNumericValue(cpfNumeros.charAt(h));
            somatorioDigito2 += (valorNumericoDoCPF * peso);
        }
        segundoDigitoCalculo = (somatorioDigito2 % 11 < 2) ? 0 : 11 - (somatorioDigito2 % 11);

        //Converte os dois últimos valores do CPF digitado de char para números int:
        int verificadorDoPrimeiroDigito = Character.getNumericValue(cpfNumeros.charAt(9));//Penúltimo
        int verificadorDoSegundoDigito = Character.getNumericValue(cpfNumeros.charAt(10));//último

        //Compara os valores conseguidos com os calculos com os do CPF do jogador. Se dorem diferentes o CPF ínvalido
        if ((verificadorDoPrimeiroDigito != primeiroDigitoCalculo) || (verificadorDoSegundoDigito != segundoDigitoCalculo)) {
            return false;
        }

        return true;
    }

    //--------------------------------------------------------------------------------------------------------------------
    public static String processamentoDosDados(JTextField campoNome, JTextField campoCPF, JTextField campoApelido) {
        String nomeAuxiliar = campoNome.getText();
        String cpfAuxiliar = campoCPF.getText();
        String apelidoAuxiliar = campoApelido.getText();

        Jogador jogador = new Jogador();

        //--------------------------------
        if ((nomeAuxiliar.trim().length() >= 2) || (nomeAuxiliar == null)) {
            jogador.setName(nomeAuxiliar.trim());
        } else {
            return "Nome inválido";
        }
        //--------------------------------
        if ((apelidoAuxiliar.trim().length() >= 2 || apelidoAuxiliar == null)) {
            jogador.setApelido(apelidoAuxiliar.trim());
        } else {
            return "Apelido Inválido";
        }
        //--------------------------------
        if (Util.isCPFValido(cpfAuxiliar)) {
            jogador.setCPF(cpfAuxiliar.trim());
        } else {
            return "CPF inválido";
        }
        //-------------------------------

        JogadorService jogadorIncluir = new JogadorService();
        String mensagem = jogadorIncluir.inserir(jogador);
        return mensagem;
    }

    //----------------------------------------------------------------------------------------------------------------------------
    public static int lerInteriro() {
        Scanner input = new Scanner(System.in);
        int numero = 0;
        boolean invalido;

        try {
            numero = input.nextInt();
            invalido = false;

        } catch (Exception e) {
            invalido = true;
            return -1;
        }

        return numero;
    }

    //Descupe-me Bruno, mas isso é necessário...
    //Isso tornara a organizaçã do ranque muito mais eficiênte.
    public static void mangeSortDivide(Ranque[] finall) {
        //Se o vetor têm tamanho '1', quer dizer que este já está organizado
        if (finall.length <= 1) {
            return;
        }

        int meio = finall.length / 2;

        Ranque aEsquerda[] = new Ranque[finall.length - meio];
        Ranque aDireita[] = new Ranque[meio];

        //Prenche os novos vetores
        for (int i = 0; i < meio; i++) {
            aDireita[i] = finall[i];
        }

        for (int i = meio; i < finall.length; i++) {
            aEsquerda[i - meio] = finall[i];
        }

        mangeSortDivide(aEsquerda);
        mangeSortDivide(aDireita);
        mangeSortOrgaiza(aEsquerda, aDireita, finall);
    }

    private static void mangeSortOrgaiza(Ranque esquerda[], Ranque direita[], Ranque finall[]) {
        //Reorganiza os vetores
        int esquer = 0, direit = 0, finallNum = 0;

        while (esquer < esquerda.length && direit < direita.length) {
            if (esquerda[esquer].getMedia() >= direita[direit].getMedia()) {
                finall[finallNum++] = esquerda[esquer++];
            } else {
                finall[finallNum++] = direita[direit++];
            }
        }

        //Se sobra algo da esquerda manda para o final do vetor
        while (esquer < esquerda.length) {
            finall[finallNum++] = esquerda[esquer++];
        }

        //Se sobra algo da direita manda para o final do vetor
        while (direit < direita.length) {
            finall[finallNum++] = direita[direit++];
        }

    }

    public static int[] sorteaNumeros(int dificuldade) {
        int numerosSorteados[];

        final int SIZE_SORT;
        final int LIMIT_SORT;

        switch (dificuldade) {
            case 1:
                SIZE_SORT = 9;
                LIMIT_SORT = 12;

                break;
            case 2:
                SIZE_SORT = 12;
                LIMIT_SORT = 21;

                break;
            case 3:
                SIZE_SORT = 18;
                LIMIT_SORT = 30;

                break;

            default:
                return null;
        }

        numerosSorteados = new int[SIZE_SORT];

        Random sorteio = new Random();

        //Números estão saindo repetidos
        for (int i = 0; i < numerosSorteados.length; i++) {
            int numeroSorteado;
            boolean repetido;

            do {
                repetido = false;
                numeroSorteado = sorteio.nextInt(LIMIT_SORT + 1);

                for (int j = 0; j < numerosSorteados.length; j++) {
                    if (numerosSorteados[j] == numeroSorteado) {
                        repetido = true;
                        break;
                    }
                }

            } while (repetido);

            numerosSorteados[i] = numeroSorteado;
        }

        return numerosSorteados;
    }

    public static int calcularAcertos(int numerosDoJogo[], int numeroDigitado[]) {
        int acertos = 0;

        for (int i = 0; i < numerosDoJogo.length; i++) {
            //Calcula o número de acertos
            int j = 0;
            while (j < numerosDoJogo.length) {
                if (numerosDoJogo[i] == numeroDigitado[j++]) {
                    acertos++;
                    break;
                }
            }
        }

        return acertos;
    }
}

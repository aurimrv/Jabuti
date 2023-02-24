package vending;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Classe responsável pela interface com a máquina de venda.
 * Nesta versão, a forma de comunicação com a máquina é por meio de uma
 * interface modo texto com dados fornecidos via teclado ou arquivo.
 *
 * @author Auri Vincenzi
 */
public class VendingMachineInterface {

    /**
     * Este método simula o comportamento da máquina de vendas. As ações
     * permitidas para realizar suas tarefas são as de inserir moedas, solicitar
     * a devolução das moedas ou solicitar a compra de determinado item. Para
     * realizar tais ações, o usuário interage com o sistema enviando os
     * comandos definidos a seguir: <BR>
     * <UL>
     * <LI>insertCoin &lt;valor&gt;;
     * <LI>returnCoin; and
     * <LI>vendItem &lt;item_number&gt;.
     * </UL>
     * 
     * <P>
     * Essas operações podem ser fornecidas via teclado ou por meio de um
     * arquivo texto contendo, em cada linha, a invocação de uma das operações
     * que se deseja realizar. Para encerrar a execução via teclado, basta
     * pressionar CTRL+D.
     *
     * Após executar cada uma das operações, uma mensagem é exebida no display
     * da máquina para o usuário tomar conhecimento do ocorrido e se ele obteve
     * sucesso ou não em sua solicitação.
     *
     *
     * @param args - quando vazio significa que a entrada de dados será
     * feita via teclado, do contrário, o valor fornecido é interpretado como
     * o nome de um arquivo texto com comandos para a máquina.
     *
     * @throws Exception - qualquer exceção fora aquelas lançadas pela
     * máquina de vendas.
     */
    static public void main(String args[]) throws Exception {
        BufferedReader drvInput;
        String tcLine = new String();

        String methodName = new String();

        VendingMachine machine = new VendingMachine();

        if (args.length < 1) // leitura das operações via teclado
        {
            drvInput = new BufferedReader(new InputStreamReader(System.in));
        } else // leitura das operações via arquivo texto
        {
            drvInput = new BufferedReader(new FileReader(args[0]));
        }

        System.out.println("VendingMachine LIGADA");

        // para entradas via teclado, CTRL+D pára a execução da máquina
        while ((tcLine = drvInput.readLine()) != null) {
            StringTokenizer tcTokens = new StringTokenizer(tcLine);

            if (tcTokens.hasMoreTokens()) {
                methodName = tcTokens.nextToken();
            }

            int value = 0;

            // Uma moeda é inserida
            if (methodName.equals("insertCoin")) {
                Integer moeda = new Integer(tcTokens.nextToken());
                value = machine.insertCoin(moeda); 
                System.out.println("Crédito atual = " + value);
            } // Uma ou mais moedas são devolvidas (caso exista alguma)
            else if (methodName.equals("returnCoin")) {
                value = machine.returnCoin();
                if (value == 0) {
                    System.err.println("Sem crédito para devolução");
                } else {
                    System.out.println("Retire suas moedas");
                }
            } // Solicitação para a entrega de determinado item
            else if (methodName.equals("vendItem")) {
                Integer selection = new Integer(tcTokens.nextToken());

                try {
                    value = machine.vendItem(selection.intValue());

                    System.out.println("Retire o item desejado!!!");
                    System.out.println("Saldo atual = " + value);
                } catch (NoCoinsException nce) {
                    System.out.println("Nenhuma moeda inserida!!!");
                } catch (InvalidItemException ite) {
                    System.out.println("O item selecionado (" + selection
                            + ") é inválido!!!");
                } catch (UnavailableItemException uie) {
                    System.out.println("O item selecionado (" + selection
                            + ") está esgotado!!!");
                } catch (NotEnoughtCreditException nece) {
                    System.out.println("O crédito atual é insuficiente para a compra do item "
                            + selection + ".");
                }
            } else {
                System.out.println("Operação inválida!!!");
            }
        }
        System.out.println("VendingMachine DESLIGADA");
    }
}

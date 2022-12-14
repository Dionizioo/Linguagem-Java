package grafica;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActionCalcular extends AbstractAction {
    private JTextField num1, num2; // display Calculadora
    private JComboBox<String> Caixa;
    public ActionCalcular(JTextField num1, JTextField num2, JComboBox<String> Caixa){

        // Instanciar 
        this.num1 = num1;
        this.num2 = num2;
        this.Caixa = Caixa;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        try{
            double x = Double.parseDouble(num1.getText()); // Declarar a Variavel "X"
            double y = Double.parseDouble(num2.getText()); // Declarar a Variavel "Y"
            String operacao = Caixa.getSelectedItem().toString(); // Declarar a Variavel String
            if(operacao.equals("Soma")){
                double resultado = x + y; // Soma
                JOptionPane.showMessageDialog(null, String.format("O resultado de %.2f + %.2f = %.2f", x, y, resultado),"Calculadora Simples", JOptionPane.INFORMATION_MESSAGE);
            } else if(operacao.equals("Subtração")){
                double resultado = x - y; // Subtração
                JOptionPane.showMessageDialog(null, String.format("O resultado de %.2f - %.2f = %.2f", x, y, resultado),"Calculadora Simples", JOptionPane.INFORMATION_MESSAGE);
            } else if(operacao.equals("Multiplicação")){
                double resultado = x * y; // Multiplicação
                JOptionPane.showMessageDialog(null, String.format("O resultado de %.2f * %.2f = %.2f", x, y, resultado),"Calculadora Simples", JOptionPane.INFORMATION_MESSAGE);
            } else if(operacao.equals("Divisão")){
                double resultado = x / y; // Divisão
                JOptionPane.showMessageDialog(null, String.format("O resultado de %.2f / %.2f = %.2f", x, y, resultado),"Calculadora Simples", JOptionPane.INFORMATION_MESSAGE);
            }                      
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Número inválido!", "Erro", JOptionPane.ERROR_MESSAGE); // Caso se o Usuario Digitar Errado 
        }
    }
    
}

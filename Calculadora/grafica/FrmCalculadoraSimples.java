package grafica;

import javax.swing.JButton; // Adicionar os Botões
import javax.swing.JComboBox; // Adicionar a caixa de texto com as opçoes

import javax.swing.JFrame; // Adicionar o Texto
import javax.swing.JLabel; // Exibir Rotulos
import javax.swing.JTextField; // Grafico

import java.awt.FlowLayout; // Layout
import java.awt.Color;
import java.awt.Dimension; // dimensoes

public class FrmCalculadoraSimples extends JFrame{

    private JTextField num1, num2; // criação dos Textos Y e X
    private JComboBox Caixa; // Criação da caixa de opção

    public FrmCalculadoraSimples(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add( new JLabel("Calculadora Simples - Java")).setForeground(Color.RED); // Adicionando Cor

        add(new JLabel("Primeiro Numero: ")).setForeground(Color.BLUE); // Adicionando o Texto no Programa e Cor
        add(num1 = new JTextField(10)); 

        add(new JLabel("Segundo Numero: ")).setForeground(Color.blue); // Adicionando o Texto no Programa
        add(num2 = new JTextField(10));
        
        add(new JLabel("Operação:")); // Texto da Operação
        String[] opera = {"Soma", "Subtração", "Multiplicação", "Divisão"};
        add(Caixa = new JComboBox<String>(opera));

        JButton btnCalcular = new JButton("Calcular"); // Crição do Botão calcular
        add(btnCalcular);
        setSize(new Dimension(250, 300)); //dimensão

        ActionCalcular action = new ActionCalcular (num1, num2, Caixa);
        btnCalcular.addActionListener(action);
    }

}
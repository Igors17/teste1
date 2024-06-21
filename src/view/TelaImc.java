package view;

import model.Imc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaImc {

    private JPanel painelTitulo = new JPanel();
    private JLabel labelTitulo = new JLabel("Índice de massa Corporal");
    private JLabel lbpeso = new JLabel("peso:");
    private JTextField txtPeso = new JTextField();
    private JLabel lbTituloResultadoImc = new JLabel("Resultado do IMC");
    private JLabel lbResultadoImc = new JLabel("");
    private JLabel lbStatusIMC = new JLabel("");

    private JLabel lbaltura = new JLabel("Altura:");
    private JTextField txtAltura = new JTextField();

    private String imagePath = "../images/";
    private Icon iconBtnCalcular = new ImageIcon(getClass().getResource(imagePath + "calc2.png"));
    private Icon iconBtnLimpar = new ImageIcon(getClass().getResource(imagePath + "restart24.png"));

    int peso = 0;
    double altura = 0.0;



    public TelaImc(){
        criarTela();
    }

    public void criarTela(){
        JFrame tela = new JFrame();
        tela.setTitle("Calculadora IMC");
        tela.setSize(500, 300);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLayout(null);


        // DEFINIR O PAINEL DE TITULO
        painelTitulo.setBounds(0,0,500,50);
        painelTitulo.setBackground(new Color(221,181,87));
        painelTitulo.setLayout(null);

        labelTitulo.setBounds(10,10,200,30);
        painelTitulo.add(labelTitulo);

        // COMPONENTES TELA
        lbpeso.setBounds(10, 70,150,30);
        txtPeso.setBounds(10, 100,150,35);
        txtPeso.setFont(new Font("Arial", Font.BOLD, 24));

        tela.getContentPane().add(painelTitulo);
        tela.getContentPane().add(lbpeso);
        tela.getContentPane().add(txtPeso);

        lbaltura.setBounds(10,140,150,30);
        txtAltura.setBounds(10,170,150,35);
        txtAltura.setFont(new Font("Arial", Font.BOLD, 24));

        lbTituloResultadoImc.setBounds(225,70,250,30);
        lbTituloResultadoImc.setForeground(Color.BLACK);
        lbTituloResultadoImc.setFont(new Font("Arial",Font.BOLD,24));
        lbTituloResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        lbResultadoImc.setBounds(225,110,250,30);
        lbResultadoImc.setFont(new Font("Arial", Font.BOLD,38));
        lbResultadoImc.setForeground(Color.RED);
        lbResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        lbStatusIMC.setBounds(300,150,250,30);
        lbStatusIMC.setFont(new Font("Arial", Font.BOLD,24));
        lbStatusIMC.setForeground(Color.GREEN);
        lbResultadoImc.setHorizontalAlignment(JLabel.CENTER);

         JButton btnCalcular = new JButton("Calcular");
         btnCalcular.setIcon(iconBtnCalcular);
         btnCalcular.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 calacularImc();
             }
         });

         JButton btnLimpar = new JButton("Limpar");
         btnLimpar.setIcon(iconBtnLimpar);

        btnCalcular.setBounds(190,220,130,30);
        btnLimpar.setBounds(350,220,130,30);

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });


        tela.getContentPane().add(painelTitulo);
        tela.getContentPane().add(lbaltura);
        tela.getContentPane().add(txtAltura);
        tela.getContentPane().add(lbaltura);
        tela.getContentPane().add(txtAltura);
        tela.getContentPane().add(lbTituloResultadoImc);
        tela.getContentPane().add(lbResultadoImc);
        tela.getContentPane().add(lbStatusIMC);
        tela.getContentPane().add(btnCalcular);
        tela.getContentPane().add(btnLimpar);


        tela.setVisible(true);

    }

    private void limparTela(){
        txtAltura.setText("");
        txtPeso.setText("");
        lbStatusIMC.setText("");
        lbResultadoImc.setText("");
        txtPeso.requestFocus();
    }

    Imc imc = new Imc();

    private void calacularImc() {

        if (validarDados()) {

            imc.setPeso(peso);
            imc.setAltura(altura);

            String resultImc = String.valueOf(imc.getImc());


            if (imc.getImc() <= 18.5 || imc.getImc() <= 25.0) {
                painelTitulo.setBackground(Color.GREEN);
                lbResultadoImc.setBackground(Color.GREEN);
            } else {
                painelTitulo.setBackground(Color.RED);
                lbResultadoImc.setBackground(Color.RED);
            }



            lbResultadoImc.setText(String.format("%.2f" , imc.getImc()).replace(",", ","));
            lbResultadoImc.setText(imc.getStatus());


        }
    }
    private Boolean validarDados() {
        try{

           peso = Integer.parseInt(txtPeso.getText().trim());


        } catch (NumberFormatException erro) {
            System.out.println(erro);
            JOptionPane.showMessageDialog(
                    null,
                    "O peso deve ser um valor númerico !!",
                    "valor inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            altura = Double.parseDouble(txtAltura.getText().replace(",", ",").trim());
        } catch (NumberFormatException erro){
            System.out.println(erro);
            JOptionPane.showMessageDialog(
                    null,
                    "O peso deve ser um valor númerico !!",
                    "valor inválido",
                    JOptionPane.ERROR_MESSAGE

            );
            return false;
        }

        return true;
    }

}

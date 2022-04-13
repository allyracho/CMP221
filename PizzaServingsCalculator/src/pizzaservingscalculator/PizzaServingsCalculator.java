/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaservingscalculator;

/**
 *
 * @author allyracho
 */

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

        
public class PizzaServingsCalculator extends JFrame {

    private final JLabel title;
    private final JLabel question;
    private final JTextField input;
    private final JButton button;
    private final JPanel line2;
    private final JLabel blank;

    
    public PizzaServingsCalculator(){
        super("Pizza Servings Calculator");
        setLayout(new GridLayout(4,1));
        
        title = new JLabel("Pizza Servings Calculator",JLabel.CENTER);
        title.setToolTipText("This is the title label");
        title.setForeground(Color.red);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 26));
        add(title);
        
        question = new JLabel("Enter the size of the pizza in inches:", JLabel.CENTER);
        question.setToolTipText("This is the question label");
        question.setHorizontalTextPosition(JLabel.CENTER);
        question.setFont(new Font("Times New Roman", Font.BOLD, 12));
       
        input = new JTextField(4);
        add(input);
        
        line2 = new JPanel();
        line2.add(question);
        line2.add(input);
        add(line2);

        button = new JButton("Calculate Servings");
        button.setFont(new Font("Times New Roman", Font.BOLD,12));
        add(button);
        
        ButtonHandler handler = new ButtonHandler();
        button.addActionListener(handler);
        
        blank = new JLabel();
        add(blank);
       

    }
    
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
           
            double size = Double.parseDouble(input.getText());
    
            double servings = Math.pow((size/8),2);
            
            blank.setText(String.format("%s%.2f%s%.2f%s","A ",size," inch pizza will serve ",servings," people."));
            
        }
        
    }
    
    
    
    public static void main(String[] args) {
        
        PizzaServingsCalculator pizzaserving = new PizzaServingsCalculator();
        pizzaserving.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pizzaserving.setSize(350,300);
        pizzaserving.setVisible(true);
        
    }
    
}

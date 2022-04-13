/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savingsaccount;

/**
 *
 * @author allyracho
 * This program implements and calculates two savings accounts 
 * over 12 months with an interest rate of .04 then an 
 * additional month with interest of .05.
 */
public class SavingsAccount {

    /**
     * @param args the command line arguments
     */
    //set variables
    static private double annualInterestRate;
    private double savingsBalance;
    
    //constructor method
    public SavingsAccount(double balance){
        savingsBalance = balance;
        annualInterestRate = 0;
    }
    
    //method to calculate monthly interest and increasing savings balance by total
    public void calculateMonthlyInterest() {
        double monthlyInterest;
        monthlyInterest = (this.savingsBalance * annualInterestRate)/12;
        this.savingsBalance+=monthlyInterest;
    }
    
    //method to set interest rate
    public static void setInterestRate(double newInterest) {
        annualInterestRate = newInterest;
    }
       
    //main method which initatates 2 objects (saver1,saver2) and 
    //sets, calculates, and prints their savings balance over 12 month period.
    //Interest rate then set to new value and savings balance is calculated/printed
    //for additional month.
    public static void main(String[] args) {
        SavingsAccount saver1 = new SavingsAccount(2000.00);
        SavingsAccount saver2 = new SavingsAccount(3000.00);
        System.out.println("Savings Account Balances");
        System.out.println("Month\t\tSaver1\t\tSaver2");
        
        int month = 1;
        while (month<=12){
            
            saver1.setInterestRate(.04);
            saver1.calculateMonthlyInterest();
            saver2.setInterestRate(.04);
            saver2.calculateMonthlyInterest();
            System.out.println(month+"\t"+"\t"+(String.format("%.2f",saver1.savingsBalance))+"\t"+"\t"+(String.format("%.2f",saver2.savingsBalance)));
            month++;
    } 
        
        saver1.setInterestRate(.05);
        saver2.setInterestRate(.05);        
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.println("13"+"\t"+"\t"+(String.format("%.2f", saver1.savingsBalance))+"\t"+"\t"+(String.format("%.2f", saver2.savingsBalance)));
        }
    }


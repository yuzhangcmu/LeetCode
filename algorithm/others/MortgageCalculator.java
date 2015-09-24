package Algorithms.algorithm.others;

 
import java.text.NumberFormat;
import java.util.Scanner;
 
/**
 * Write a Java program without a graphical user interface that 
 * calculates and displays the mortgage payment amount given the 
 * amount of the mortgage, the term of the mortgage, and the 
 * interest rate of the mortgage.
 * 
 * Then display the balance over the term of the loan.
 * 
 * @author http://learn-java-by-example.com
 *
 */
 
public class MortgageCalculator {
 
   /**
    * Display monthly balance for the term of a loan
    * 
    * @param loanAmount total amount of loan
    * @param termInYears term of loan in years
    * @param interestRate loan interest rate, 5.6% = 5.6
    * @param monthlyPayment monthly payment
    */
   public static void displayMonthlyBalance(int loanAmount, 
         int termInYears, double interestRate, double monthlyPayment) {
       
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
       
      interestRate /= 100.0;
      double monthlyRate = interestRate / 12.0;
      int termInMonths = termInYears * 12;
       
      // Loop through the term of the loan tracking the balance
       
      double balance = loanAmount;
      for (int i=0; i<termInMonths; i++) {
          
         // Add interest to the balance
          
         balance += (balance * monthlyRate);
          
         // Subtract the monthly payment
          
         balance -= monthlyPayment;
          
         // Display running balance
          
         System.out.println("Balance after payment "+(i+1)+": "+
            currencyFormat.format(balance));
      }
   }
    
   /**
    * Calculates the monthly payment for a specified loan
    * 
    * @param loanAmount total amount of loan
    * @param termInYears term of loan in years
    * @param interestRate loan interest rate, 5.6% = 5.6
    * @return monthly payment
    */
   public static double calculateMonthlyPayment(
         int loanAmount, int termInYears, double interestRate) {
          
         // Convert interest rate into a decimal
         // eg. 6.5% = 0.065
          
         interestRate /= 100.0;
          
         // Monthly interest rate 
         // is the yearly rate divided by 12
          
         double monthlyRate = interestRate / 12.0;
          
         // The length of the term in months 
         // is the number of years times 12
          
         int termInMonths = termInYears * 12;
          
         // Calculate the monthly payment
         // Typically this formula is provided so 
         // we won't go into the details
          
         // The Math.pow() method is used 
         // to calculate values raised to a power
          
         double monthlyPayment = 
            (loanAmount*monthlyRate) / 
               (1-Math.pow(1+monthlyRate, -termInMonths));
          
         return monthlyPayment;
      }
 
   public static void main(String[] args) {
       
      Scanner scanner = new Scanner(System.in);
 
      // Prompt user for details of loan
       
      System.out.print("Enter loan amount: ");
      int loanAmount = scanner.nextInt();
      System.out.print("Enter loan term (in years): ");
      int termInYears = scanner.nextInt();
      System.out.print("Enter interest rate: ");
      double interestRate = scanner.nextDouble();
       
      // Calculate the monthly payment
       
      double monthlyPayment = calculateMonthlyPayment(
            loanAmount, termInYears, interestRate);
 
      // NumberFormat is useful for formatting numbers
      // In our case we'll use it for 
      // formatting currency and percentage values
       
      NumberFormat currencyFormat = 
         NumberFormat.getCurrencyInstance();
      NumberFormat interestFormat = 
         NumberFormat.getPercentInstance();
 
      // Display details of the loan
 
      System.out.println("Loan Amount: "+
         currencyFormat.format(loanAmount));
      System.out.println("Loan Term: "+
         termInYears+" years");
      System.out.println("Interest Rate: "+
         interestFormat.format(interestRate));
      System.out.println("Monthly Payment: "+
         currencyFormat.format(monthlyPayment));
 
      // Now display the monthly balance for
      // the term of the loan
       
      displayMonthlyBalance(
         loanAmount, termInYears, interestRate, monthlyPayment);
   }
 
}
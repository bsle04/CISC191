import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the first number:");
        int firstNumber = scnr.nextInt();
        System.out.println("Enter the second number:");
        int secondNumber = scnr.nextInt();
        System.out.println("The GCD of " + firstNumber + " and " + secondNumber + " is " + gcd(firstNumber, secondNumber));
    }

    public static int gcd(int firstNumber, int secondNumber){
        if(firstNumber%secondNumber == 0){
            return secondNumber;
        }else{
            return gcd(secondNumber, firstNumber%secondNumber);
        }
    }
}
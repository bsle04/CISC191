import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        System.out.println(fibonacci(input));


    }

    public static int fibonacci(int input){
        if(input < 0){
            return -1;
        }
        if(input == 0){
            return 0;
        }
        if(input == 1){
            return 1;
        }

        return fibonacci(input - 1) + fibonacci(input-2);
    }
}
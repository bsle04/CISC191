public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6 ,7, 8, 9};
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
        reverseArray(array);
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }

    public static void reverseArray(int[] input){
        for(int i = 0; i < input.length/2; i++){
            int temp = input[i];
            input[i] = input[input.length-i-1];
            input[input.length-i-1] = temp;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        int[] array1 = {2, 3, 0, 6, 1, 5};
        System.out.println(task2(array1));
        int[] array2 = {8, 2, 3, 9, 7, 5, 0, 6};
        System.out.println(task2(array2));

    }

    public static int task2(int[] array){
        //sort the array
        int missing = 0;
        for(int i = 0; i < array.length; i++){

        }
        for(int i = 0; i < array.length-1; i++){
            if(array[i] != array[i+1] - 1){
                missing = array[i=1] -1;
            }
        }
        return missing;
    }
}
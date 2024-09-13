import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a few numbers");
        int[] newList = new int[8];
        for(int i = 0; i < 8; i++){
            newList[i] = input.nextInt();
        }
        mergeSort(newList);
        for(int j = 0; j < 8; j++){
            System.out.print(newList[j] + " ");
        }
    }

    public static void mergeSort(int[] list){
        int n = list.length;
        if (n < 2)
            return;
        int mid = n / 2;
        int left[] = new int[mid];
        int right[] = new int[n - mid];
        for (int i = 0; i < mid; i++)
            left[i] = list[i];
        for (int i = mid; i < n; i++)
            right[i - mid] = list[i];
        mergeSort(left);
        mergeSort(right);
        merge(list, left, right);
    }

    public static void merge(int arr[], int left[], int right[]) {
        int nL = left.length;
        int nR = right.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < nL && j < nR) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < nL) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nR) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
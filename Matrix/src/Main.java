import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double[][] matrix1 = new double[2000][2000];
        double[][] matrix2 = new double[2000][2000];
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 2000; j++) {
                matrix1[i][j] = matrix2[i][j] = Math.random() * 100;
            }
        }
        long startTime = System.currentTimeMillis();
        parallelMultiplyMatrix(matrix1, matrix2);
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel time: " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        sequentialMultiplyMatrix(matrix1, matrix2);
        endTime = System.currentTimeMillis();
        System.out.println("Sequential time: " + (endTime - startTime) + " milliseconds");

    }

    public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {
        double[][] newArray = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    newArray[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        System.out.println("sequential done");
        return newArray;
    }

    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
        List<Thread> threads = new ArrayList<>();
        double[][] newArray = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            RowMultiplyWorker task = new RowMultiplyWorker(newArray, a, b, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            if (threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }
        System.out.println("parallel done");
        return newArray;
    }

    private static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}

class RowMultiplyWorker implements Runnable{
    private double[][] newArray;
    private double[][] a;
    private double[][] b;
    private int k;
    public RowMultiplyWorker(double[][] newArray, double[][] a, double[][] b, int k){
        this.newArray = newArray;
        this.a = a;
        this.b = b;
        this.k = k;
    }

    public void run(){
        for(int i = 0; i < b[0].length; i++){
            newArray[k][i] = 0;
            for(int j = 0; j < a[k].length; j++){
                newArray[k][i] += a[k][j] * b[j][i];
            }
        }
    }
}
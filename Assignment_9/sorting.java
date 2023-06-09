package Assignments.Assignment_9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class sorting {
    public static void main(String[] args) {
        try {
            File fileForTime = new File("time.csv");
            fileForTime.createNewFile();
            File fileForComparisons = new File("comp.csv");
            fileForComparisons.createNewFile();
            File fileforExchanges = new File("exc.csv");
            fileforExchanges.createNewFile();
            FileWriter fWriterForTime = new FileWriter("time.csv", true);
            fWriterForTime.append("Insertion Sort, Quick Sort, Shell Sort" + System.getProperty("line.separator"));
            fWriterForTime.close();
            FileWriter fWriterforComps = new FileWriter("comp.csv, true");
            fWriterforComps.append("Insertion Sort, Quick Sort, Shell Sort" + System.getProperty("line.separator"));
            fWriterforComps.close(); 
            FileWriter fWriterforExc = new FileWriter("exc.csv", true);
            fWriterforExc.append("Insertion Sort, Quick Sort, Shell Sort" + System.getProperty("line.separator"));
            fWriterforExc.close();
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }

        for (int i = 10; i < 10000; i*=2) {
            int[] x, y, z;
            x = genRndmArray(i);
            y = genRndmArray(i);
            z = genRndmArray(i);
            
            long startTime = System.nanoTime();
            int[] insertionSortResults = insertionSort(x);
            long insertionSortTime = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            int[] quickSortResults = quickSort(y, 0, y.length-1);
            long quickSortTime = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            int[] shellSortResults = shellSort(z);
            long shellSortTime = System.nanoTime() - startTime;

            try {
                FileWriter fWriterForTime = new FileWriter("time.csv", true);
                    fWriterForTime.append(i + ", " + insertionSortTime + ", " + quickSortTime + ", " + shellSortTime + System.getProperty("line.separator"));
                    fWriterForTime.close();
                    FileWriter comparisonsFileWriter = new FileWriter("comp.csv", true);
                    comparisonsFileWriter.append(i + ", " + insertionSortResults[0] + ", " + quickSortResults[0] + ", " + shellSortResults[0] + System.getProperty("line.separator"));
                    comparisonsFileWriter.close();
                    FileWriter exchangesFileWriter = new FileWriter("exc.csv", true);
                    exchangesFileWriter.append(i + ", " + insertionSortResults[1] + ", " + quickSortResults[1] + ", " + shellSortResults[1] + System.getProperty("line.separator"));
                    exchangesFileWriter.close();
            } catch (IOException e) {
                System.out.println("Error occurred");
                e.printStackTrace();
            }
        }
    }

    public static int[] genRndmArray(int n){
        int[] list = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list[i] = random.nextInt(10000);
        }
        return list;
    }

//Sorting Functions found from rosettacode.org
//Insertion Sort Function
    public static int[] insertionSort(int []X){
        int comp = 0;
        int exc = 0;
        for (int i = 0; i < X.length; i++) {
            int val = X[i];
            int j = i - 1;
            while (j >= 0 && X[j] > val) {
                X[j + 1] = X[j];
                j = j-1;
                comp++;
                exc++;
            }
            X[j + 1] = val;
            exc++;
        }
        return new int[]{comp, exc};
    }

//Quick Sort Functions
    public static int[] quickSort(int[] X, int first, int last){
        int comp = 0;
        int exc = 0;
        comp++;
        if (first < last) {
            int[] a = partition(X, first, last);
            int pivIndex = a[0];
            comp += a[1];
            exc += a[2];
            int[] x = quickSort(X, first, pivIndex - 1);
            comp += x[0];
            exc += x[1];
            int[] y = quickSort(X, pivIndex + 1, last);
            comp += y[0];
            exc += y[1];
        }
        
        return new int[]{comp, exc};
    }

    public static int[] partition(int[] X, int first, int last){
        int comp = 0;
        int exc = 0;
        int pivot = X[last];
        int i = (first-1);
        for (int j = first; j < last; j++) {
            comp++;
            if(X[j] < pivot){
                i++;
                int temp = X[i];
                X[i] = X[j];
                X[j] = temp;
                exc += 2;
            }
        }
        int temp = X[i+1];
        X[i+1] = X[last];
        X[last] = temp;
        exc += 2;
        return new int[]{i+1, comp, exc};
    }

    //Shell Sort Functions
    public static int[] shellSort(int[] a){
        int comp = 0;
        int exc = 0;
        int inc = a.length / 2;
        while(inc > 0){
            for (int i = inc; i < a.length; i++) {
                int j = i;
                int temp = a[i];
                while (j >= inc && a[j-inc] > temp) {
                    a[j] = a[j - inc];
                    j = j - inc;
                    comp++;
                    exc++;
                }
                exc++;
                a[j] = temp;
            }
            if (inc == 2) {
                inc = 1;
            } else{
                inc *= (5.0 / 11);
            }
        }
        return new int[]{comp,exc};
    }

    
}
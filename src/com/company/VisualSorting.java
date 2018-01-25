package com.company;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

import static edu.princeton.cs.algs4.StdDraw.pause;

public class VisualSorting {
    private static final int DELAY = 1000;//delay time for each animation
    private static final int SIZE = 20;

    /**
     * Sort an array of element of double type.
     *
     * @param a an array of double type to be sorted
     * @param b an array to record the sorted index & the indices moved one position to the right
     */
    static void insertionSort(double[] a, double[] b) {
        int cnt = 1;

        for(int i=1; i<a.length; i++) {
            int j = i;
            for(; j>0 && a[j] < a[j-1]; j--) {
                double tmp = a[j];
                a[j] = a[j-1];
                a[j-1] = tmp;
                b[cnt++] = j;
            }
            b[0] = j;
        }
    }

    /**
     * Test whether an array is sorted.
     * @param a an array of double type
     * @return true if the array is sorted
     */
    static boolean isSorted(double[] a) {

        for(int i=0; i<a.length-1; i++) {
            if(a[i] > a[i+1]) return false;
        }
        return true;
    }
    /**
     * Draw a visual trace on the canvas to visualize the element using insertion sort.
     * The sorted index & indices moved one position to the right are stored in b[0] &
     * b[1, j], respectively.
     *
     * @param a an array of double type to be sorted
     * @param b an array to record the sorted index & the indices moved one position to the right
     */
    static void visualSorting(double[] a, double[] b) {
        int N = a.length;

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        //insertionSort(a, b);

        for(int i=0; i<N; i++) {
            double x =  16.0 *i/N + 2;
            double y = a[i]*50.0;
            double rw = 5.0/N;
            double rh = a[i]*50.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }


    }

    static void fillColor(Double[] a) {

    }

    public static void main(String[] args) {
        double[] a = new double[SIZE];
        int[] movedIndices = new int[SIZE];
        int sortedIndex = 0;

        for(int i=0; i<a.length; i++)
            a[i] = StdRandom.uniform();

        while(true) {
            insertionSort(a, a);
            if(isSorted(a)) break;
            pause(DELAY);
        }

    }
}

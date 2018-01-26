package com.company;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

import static edu.princeton.cs.algs4.StdDraw.pause;

public class VisualTrace {
    //private constants of the class
    private static final int SIZE = 20;
    private static final int DELAY = 300;//millisecond
    //private enum of the class
    private enum BarColor {//a seq or set of values
        RED,
        BLACK,
        GRAY
    }

    private static void selection(double a[]) {
        int[] colorIndices = new int[SIZE];
        /**
         * for i in unsorted subarray
         *  for j in sorted subarray
         *      if A[j] < A[j-1] exch(a, j ,j-1)
         *
         * [3,  1,  2]
         * _    _____
         * red  black
         *
         * A[i] is sorted
         * record j after for loop, which is j-1
         * A[j] is moved
         * record the index after move
         * 4   3
         * j-1 j
         * 3   4
         * j-1 j
         * 2   3 1
         * j-1 j
         * 1   2 3
         * j-1 j
         * current index j
         * j minus 1
         * record the red & black indices starting from index 1, before each pass of sorting A
         *
         *
         */
        for(int i=1; i<a.length; i++) {
            int cnt = 1;
            int j=i;

            while(j>0 && a[j] < a[j-1]) {
                exch(a, j, j-1);
                colorIndices[cnt++] = j;
                j--;
            }
            colorIndices[0] = j;
            show(a, colorIndices);
        }
    }

    private static boolean isSorted(double a[]) {
        /**
         * for i in A
         *  if A[i] > A[i+1] break
         */
        for(int i=0; i<a.length-1; i++) {//the former is n-2, the latter is n-1
            if(a[i] > a[i+1]) return false;
        }
        return true;
    }
    private static void exch(double[] a, int i, int j) {
        double tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private static void show(double[] a, int[] colorIndices) {
        /**
         * clear last pass
         * show current pass of sorting A, ending in a partially sorted array
         */
        int N = a.length;

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);

        StdDraw.clear();
        /**
         * Follow the given formula:
         * x-axis coordinate general term, y-axis coordinate general term
         * radius width general term, radius height general term
         *
         * select C[0] to color the red index
         * select C[1..n-1] to color the black indices
         */
        for(int i=0; i<N; i++) {
            double x =  16.0 *i/N + 2;
            double y = a[i]*50.0;
            double rw = 5.0/N;
            double rh = a[i]*50.0;
            switch(getColor(i, colorIndices)) {//enumeration iteration
                case RED:
                    StdDraw.setPenColor(Color.RED);
                    break;
                case BLACK:
                    StdDraw.setPenColor(Color.BLACK);
                    break;
                case GRAY:
                    StdDraw.setPenColor(Color.GRAY);
            }
            StdDraw.filledRectangle(x, y, rw, rh);
        }


        //for(int i=0; i<colorIndices.length; i++) {
          //  if()
        //}
        /**
         * if i is red
         *  set the pen color to red
         * else if i is black
         *  set the pen color to black
         * else
         *  set the pen color gray
         * draw a rect
         *           A
         * 1 2 3 5 4
         * 0 1 2 3 4
         *
         *          C
         * 3 1 2 black
         * - ---
         * red
         *
         *
         * if i = C[0] return RED
         * linear search
         * for j=1 to n-1
         *  if i = C[j] return BLACK
         * return GRAY
         *
         */


        pause(DELAY);
    }
    private static BarColor getColor(int i, int[] colorIndices) {
        if(i == colorIndices[0]) return BarColor.RED;
        for(int j=1; j<colorIndices.length; j++) {
            if(i == colorIndices[j]) return  BarColor.BLACK;
        }
        return BarColor.GRAY;
    }
    public static void main(String[] args) {
        double[] a = new double[SIZE];
        /**
         *
         * while true:
         *  show each pass of sorting A, ending in a partially sorted array
         *  if A is sorted: break
         *  pause DELAY
         */
        for(int i=0; i<a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        while (true) {
            //show A in totally sorted order, then pause & break the loop
            //show A in totally sorted order, break the loop
            selection(a);
            if(isSorted(a)) break;
        }


    }
}

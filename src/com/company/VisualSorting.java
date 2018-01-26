/**
 * Draw the array contents as vertical bars like the visual traces in this section,
 * redrawing the bars after each pass, to produce an animated effect, ending in a "sorted" picture
 * where the bars appear in order of their height.
 */

package com.company;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import static edu.princeton.cs.algs4.StdDraw.pause;

public class VisualSorting {
    private static final int DELAY = 1000;//delay time for each animation
    private static final int SIZE = 20;
    private static final int ANIMATION_TIMES = 20;

    private static void selectionSort(double[] a) {
        /**
         * for final pos i in A
         *  for j in unsorted A
         *   if j < min min - j
         * exchange(a, i, min)
         *
         * 1 3 5 4 2 1
         *     - -----
         *     i min
         */
        int N = a.length;
        for(int i=0; i<N; i++) {
            int min = i;
            for(int j=i+1; j<N; j++) {
                if(a[j] < a[min]) min = j;
            }
            exch(a, min, i);
        }
        // redraw the vertical bars, ending in a sorted picture
        show(a);
    }
    private static void exch(double[] a, int i, int j) {
        double tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private static void show(double[] a) {
        int N = a.length;

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.clear();
        /**
         * Follow the given formula:
         * x-axis coordinate general term, y-axis coordinate general term
         * radius width general term, radius height general term
         */
        for(int i=0; i<N; i++) {
            double x =  16.0 *i/N + 2;
            double y = a[i]*50.0;
            double rw = 5.0/N;
            double rh = a[i]*50.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
    public static void main(String[] args) {

        for(int i=0; i<ANIMATION_TIMES; i++) {
            double[] a = new double[SIZE];

            for(int j=0; j<a.length; j++) {
                a[j] = StdRandom.uniform();
            }
            selectionSort(a);
            pause(DELAY);
        }

    }
}

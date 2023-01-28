package weblab;

import java.util.*;
import java.lang.Math;
import java.util.Arrays.*;

public class Solution {

    /**
     * Sorts and merges `in[start, ..., start + inc - 1]` and `in[start + inc, ..., start + 2*inc - 1]` into `out`.
     *
     * @param in The input array, sorted in their intervals `in[start, ..., start + inc - 1]` and `in[start + inc, ..., start + 2*inc - 1]`.
     * @param out The output array that should be updated to have `in[start, ..., start + inc - 1]` and `in[start + inc, ..., start + 2*inc - 1]`
     *            in sorted fashion at `out[start, start + 2*inc - 1]`.
     * @param start The index of the first element to be merged into out.
     * @param inc The size of each of the intervals to be merged into out.
     */
    public static void merge(int[] in, int[] out, int start, int inc) {
        int idx1 = start;
        int end1 = start + inc - 1;
        int idx2 = start + inc;
        int end2 = start + 2*inc - 1;
        int outx = start;

        while (idx1 <= end1 && idx2 <= end2 && idx1 < out.length && idx2 < out.length) {
            if (in[idx1] <= in[idx2]) {
                out[outx++] = in[idx1++];
            } else {
                out[outx++] = in[idx2++];
            }
        }
        while (idx1 <= end1 && idx1 < out.length) {
            out[outx++] = in[idx1++];
        }
        while (idx2 <= end2 && idx2 < out.length) {
            out[outx++] = in[idx2++];
        }
    }

    /**
     * Sorts the input array using bottom-up merge sort.
     *
     * @param array The array to be sorted.
     * @return The resulting sorted array.
     */
    public static int[] mergeSortBottomUp(int[] arr) {
        if (arr == null) {return null;}
        int[] in = Arrays.copyOf(arr, arr.length);
        int[] out = new int[arr.length];

        for (int j = 1; j < arr.length; j *= 2) {
            for (int i = 0; 2*i*j < arr.length; i++) {
                merge(in, out, 2*i*j, j);
            }
            in = Arrays.copyOf(out, out.length);
        }

        return out;
    }
}

package com.example;

public class QuickSortVisualizer {
    private VisualizationCallback callback;

    // Abstract methods that must be defined in ArrayVisualizer.java
    public interface VisualizationCallback {
        void onSwap(int index1, int index2);
        void onCompare(int index1, int index2);
        void onPivotSelected(int pivotIndex);
        void onPartitionComplete(int pivotFinalPosition); 
        void onSubarraySorted(int start, int end);
    }

    public void setCallback(VisualizationCallback callback) {
        this.callback = callback;
    }

    private void swap(int[] arr, int i, int j) {
        if (callback != null) {
            callback.onSwap(i, j);
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        if (callback != null) {
            callback.onPivotSelected(high);
        }

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (callback != null) {
                callback.onCompare(j, high);
            }

            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        if (callback != null) {
            callback.onPartitionComplete(i + 1);
        }

        return i + 1;
    }

    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high); 

            if (callback != null) {
                callback.onSubarraySorted(low, high);
            }
        }
    }
}

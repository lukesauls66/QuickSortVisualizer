package com.example;

import java.util.Arrays;

import com.example.QuickSortVisualizer.VisualizationCallback;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ArrayVisualizer extends Canvas implements VisualizationCallback {
    private int[] arr;
    private boolean[] sortedElements; // track which are finished (green)
    private int comparingIndex1 = -1; // yellow
    private int comparingIndex2 = -1; // yellow
    private int pivotIndex = -1; // red
    private Label pivotIndexLabel;
    private Label comparingLabel;

    private static final Color UNSORTED_COLOR = Color.web("#90CAF9");
    private static final Color SORTED_COLOR = Color.web("#4CAF50");
    private static final Color COMPARING_COLOR = Color.web("#FF9800");
    private static final Color PIVOT_COLOR = Color.web("#F44336");
    private static final Color BACKGROUND_COLOR = Color.web("#263238");

    public ArrayVisualizer(double width, double height) {
        super(width, height);
    }

    public void setArray(int[] arr) {
        this.arr = arr.clone();
        this.sortedElements = new boolean[arr.length];
        redraw();
    }

    public void setComparing(int index1, int index2) {
        this.comparingIndex1 = index1;
        this.comparingIndex2 = index2;
        
        // Update the comparing label on JavaFX thread
        if (comparingLabel != null && arr != null && 
            index1 >= 0 && index1 < arr.length && 
            index2 >= 0 && index2 < arr.length) {
            Platform.runLater(() -> 
                comparingLabel.setText("Comparing " + arr[index1] + " to " + arr[index2])
            );
        }
        
        redraw();
    }

    public void setPivot(int index) {
        this.pivotIndex = index;
        
        // Update the pivot index label on JavaFX thread
        if (pivotIndexLabel != null) {
            Platform.runLater(() -> 
                pivotIndexLabel.setText("Pivot Index: " + index)
            );
        }
        
        redraw();
    }

    public void setSorted(int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            sortedElements[i] = true;
        }
        redraw();
    }

    public void clearHighlights() {
        comparingIndex1 = -1;
        comparingIndex2 = -1;
        pivotIndex = -1;
        redraw();
    }

    private void redraw() {
        if (arr == null)
            return;

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.clearRect(0, 0, getWidth(), getHeight());

        double canvasWidth = getWidth();
        double canvasHeight = getHeight();
        double barWidth = canvasWidth / arr.length;

        int maxVal = Arrays.stream(arr).max().getAsInt();

        for (int i = 0; i < arr.length; i++) {
            double barHeight = (arr[i] / (double) maxVal) * (canvasHeight - 20);
            double x = i * barWidth;
            double y = canvasHeight - barHeight;

            Color barColor = getBarColor(i);

            gc.setFill(Color.web("#00000020"));
            gc.fillRoundRect(x + 2, y, barWidth - 4, barHeight, 6, 6);

            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null,
                    new Stop(0, barColor),
                    new Stop(1, barColor.darker()));
            gc.setFill(gradient);
            gc.fillRoundRect(x + 4, y + 2, barWidth - 4, barHeight, 8, 8);
        }
    }

    private Color getBarColor(int index) {
        if (index == pivotIndex) {
            return PIVOT_COLOR;
        } else if (index == comparingIndex1 || index == comparingIndex2) {
            return COMPARING_COLOR;
        } else if (sortedElements[index]) {
            return SORTED_COLOR;
        } else {
            return UNSORTED_COLOR;
        }
    }

    public void updateArray(int[] newArray) {
        this.arr = newArray.clone();
        redraw();
    }

    public void setPivotIndexLabel(@SuppressWarnings("exports") Label pivotIndexLabel) {
        this.pivotIndexLabel = pivotIndexLabel;
    }

    public void setComparingLabel(@SuppressWarnings("exports") Label comparingLabel) {
        this.comparingLabel = comparingLabel;
    }

    @Override
    public void onSwap(int index1, int index2) {
        if (arr != null && index1 >= 0 && index1 < arr.length &&
                index2 >= 0 && index2 < arr.length) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;

            setComparing(index1, index2);
            redraw();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void onCompare(int index1, int index2) {
        setComparing(index1, index2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onPivotSelected(int pivotIndex) {
        setPivot(pivotIndex);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onPartitionComplete(int pivotFinalPosition) {
        clearHighlights();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onSubarraySorted(int start, int end) {
        setSorted(start, end);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

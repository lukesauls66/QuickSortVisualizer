package com.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) {
        ArrayVisualizer visualizer = new ArrayVisualizer(800, 400);
        QuickSortVisualizer sorter = new QuickSortVisualizer();

        // Connect the visualizer to the sorter via callback
        sorter.setCallback(visualizer);

        // Init array in visualizer
        int[] sampleArray = { 64, 34, 25, 12, 22, 11, 90, 5, 77, 30, 45, 88, 15, 67, 3, 92, 18, 56, 73, 41 };
        visualizer.setArray(sampleArray);

        Button startButton = new Button("Start Sort");
        Button resetButton = new Button("Reset Array");

        startButton.setOnAction(e -> startSorting(sorter, sampleArray.clone()));
        resetButton.setOnAction(e -> {
            int[] newArray = { 64, 34, 25, 12, 22, 11, 90, 5, 77, 30, 45, 88, 15, 67, 3, 92, 18, 56, 73, 41 };
            visualizer.setArray(newArray);
        });

        HBox controls = new HBox(10, startButton, resetButton);
        BorderPane root = new BorderPane();
        root.setCenter(visualizer);
        root.setBottom(controls);

        Scene scene = new Scene(root, 850, 500);
        stage.setTitle("QuickSort Visualizer");
        stage.setScene(scene);
        stage.show();
    }

    private void startSorting(QuickSortVisualizer sorter, int[] array) {
        Task<Void> sortTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                sorter.sort(array, 0, array.length - 1);
                return null;
            }
        };

        // Async process to allow for interface interacts while the sort animation runs
        Thread sortThread = new Thread(sortTask);
        sortThread.setDaemon(true);
        sortThread.start();
    }

    public static void main(String[] args) {
        launch();
    }

}
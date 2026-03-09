# QuickSort Visualizer

A real-time visual demonstration of the QuickSort algorithm built with JavaFX. This interactive application allows you to watch how QuickSort partitions and sorts an array step-by-step with color-coded animations.

## 🎯 Features

- **Real-time Visualization**: Watch QuickSort algorithm in action with smooth animations
- **Color-coded Elements**:
  - 🔵 Blue: Unsorted elements
  - 🟡 Orange: Elements being compared
  - 🔴 Red: Current pivot element
  - 🟢 Green: Sorted elements
- **Interactive Controls**: Start sorting and reset array with simple buttons
- **Threaded Execution**: Non-blocking UI that remains responsive during sorting
- **Clean Architecture**: Separation of sorting logic and visualization through callback pattern

## 📋 Requirements

- **Java 21** or higher
- **Maven 3.6+** for dependency management
- **JavaFX 21** (automatically handled by Maven)

## 🚀 Installation & Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/lukesauls66/QuickSortVisualizer.git
   cd QuickSortVisualizer
   ```

2. **Build and run with Maven**

   ```bash
   mvn clean javafx:run
   ```

   Or alternatively:

   ```bash
   mvn clean compile
   mvn javafx:run
   ```

## 🎮 Usage

1. **Launch the Application**: Run the command above and a window will open showing an unsorted array
2. **Start Sorting**: Click the "Start Sort" button to begin the QuickSort visualization
3. **Watch the Animation**: Observe how the algorithm:
   - Selects pivot elements (red)
   - Compares elements (orange)
   - Partitions the array
   - Recursively sorts subarrays
   - Marks completed sections (green)
4. **Reset**: Click "Reset Array" to restore the original unsorted array

## 📁 Project Structure

```
sort-visualizer/
├── pom.xml                          # Maven configuration
├── README.md                        # This file
└── src/main/java/com/example/
    ├── App.java                     # Main JavaFX application
    ├── ArrayVisualizer.java         # Canvas-based array visualization
    ├── QuickSortVisualizer.java     # QuickSort algorithm with callbacks
    └── SystemInfo.java              # System information utility
```

## 🔧 How It Works

### Architecture Overview

The project uses a clean **callback pattern** to separate the sorting algorithm from its visualization:

1. **QuickSortVisualizer**: Implements the QuickSort algorithm and triggers callback events
2. **VisualizationCallback Interface**: Defines events for sorting milestones
3. **ArrayVisualizer**: Implements callbacks to update the visual representation
4. **Threading**: Sorting runs on a background daemon thread to keep UI responsive

### Key Components

- **`VisualizationCallback`**: Interface defining callback methods for sorting events
- **`ArrayVisualizer`**: JavaFX Canvas that renders the array and implements visualization callbacks
- **`QuickSortVisualizer`**: Core sorting logic with callback triggers at key algorithm steps
- **`App`**: Main JavaFX application orchestrating the UI and threading

### Callback Events

- `onSwap()`: Two elements are being swapped
- `onCompare()`: Two elements are being compared
- `onPivotSelected()`: A pivot element has been chosen
- `onPartitionComplete()`: Partitioning phase is finished
- `onSubarraySorted()`: A subarray has been completely sorted

## 🎨 Color Scheme

The visualizer uses a modern dark theme with intuitive color coding:

- **Background**: Dark Blue-Grey (`#263238`)
- **Unsorted Elements**: Light Blue (`#90CAF9`)
- **Comparing Elements**: Orange (`#FF9800`)
- **Pivot Element**: Red (`#F44336`)
- **Sorted Elements**: Green (`#4CAF50`)

## 🛠️ Development

### Modifying Visualization

The `ArrayVisualizer` class handles all rendering logic. You can customize:

- Colors by modifying the static Color constants
- Animation timing by adjusting delay values
- Bar sizing and spacing in the drawing logic

## 📈 Educational Value

This project demonstrates several important computer science concepts:

- **Algorithm Visualization**: Understanding QuickSort through visual representation
- **Divide and Conquer**: Seeing how QuickSort recursively breaks down the problem
- **Time Complexity**: Observing O(n log n) average case performance
- **Design Patterns**: Callback pattern for loose coupling
- **Concurrent Programming**: Background threading in GUI applications

---

**Developed as part of Honors coursework - Spring 2026**

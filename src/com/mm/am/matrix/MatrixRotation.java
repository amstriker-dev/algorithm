package com.mm.am.matrix;

import java.util.*;


public class MatrixRotation {

    int rotation;

    public static void main(String[] args) {

        MatrixRotation mr = new MatrixRotation();

        String[][] matrix = mr.readMatrix();

        matrix = mr.rotateMatrix(matrix);

        mr.printMatrix(matrix);
    }

    public String[][] rotateMatrix(String[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        String[][] rotatedMatrix = new String[rows][cols];

        // Reading layers
        List<String> layer;
        List<List<String>> layers = new ArrayList<>((Math.max(rows,cols) / 2) + 1);

        int startRow = 0;
        int startCol = 0;

        boolean finished = false;
        while (!finished) {

            int maxRows = rows - startRow;
            int maxCols = cols - startCol;

            layer = new ArrayList<String>(maxRows*2 + maxCols*2);

            // Top layer
            for (int j=startCol; j<maxCols; j++) {
                layer.add(matrix[startRow][j]);
            }

            // Right layer
            for (int i=startRow+1; i<maxRows-1; i++) {
                layer.add(matrix[i][maxCols-1]);
            }

            // Bottom layer
            for (int j=maxCols-1; j>=startCol; j--) {
                layer.add(matrix[maxRows-1][j]);
            }

            // Left layer
            for (int i=maxRows-2; i>=startRow+1; i--) {
                layer.add(matrix[i][startCol]);
            }

            layers.add(layer);

            startRow++;
            startCol++;

            if (startCol > (cols/2) || startRow > (rows/2)) {
                finished = true;
            }
        }

        // Rotating layers
        for (List<String> l : layers) {
            rotateList(l);
        }

        // Filling new matrix
        startRow = 0;
        startCol = 0;
        int layerCount = 0;
        finished = false;
        while (!finished) {

            int maxRows = rows - startRow;
            int maxCols = cols - startCol;

            layer = layers.get(layerCount++);

            // Top layer
            for (int j=startCol; j<maxCols; j++) {
                rotatedMatrix[startRow][j] = layer.remove(0);
            }

            // Right layer
            for (int i=startRow+1; i<maxRows-1; i++) {
                rotatedMatrix[i][maxCols-1] = layer.remove(0);
            }

            // Bottom layer
            for (int j=maxCols-1; j>=startCol; j--) {
                rotatedMatrix[maxRows-1][j] = layer.remove(0);
            }

            // Left layer
            for (int i=maxRows-2; i>=startRow+1; i--) {
                rotatedMatrix[i][startCol] = layer.remove(0);
            }

            layers.add(layer);

            startRow++;
            startCol++;

            if (startCol > (cols/2) || startRow > (rows/2)) {
                finished = true;
            }

        }

        return rotatedMatrix;
    }

    private void rotateList(List<String> list) {

        if (list.size() == 0) {
            return;
        }

        int length = list.size();
        int rotate = this.rotation;

        // Calculating minimum number of rotations
        if (rotate > length) {
            rotate = rotate - (rotate / length) * length ;
        }

        for (int i=0; i<rotate; i++) {
            String v = list.remove(0);
            list.add(v);
        }
    }

    public String[][] readMatrix() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number whose factorial is to be found: ");

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        this.rotation = scanner.nextInt();

        String[][] matrix = new String[rows][cols];

        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                matrix[i][j] = scanner.next();
            }
        }

        return matrix;
    }

    public void printMatrix(String[][] matrix) {

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Matrix {
    private ArrayList<Integer> values; // Array of values
    private ArrayList<Integer> rowIndices; //Array of line numbers
    private ArrayList<Integer> colIndices; // Array of column numbers
    private int numRows;
    private int numCols;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        values = new ArrayList<>();
        rowIndices = new ArrayList<>();
        colIndices = new ArrayList<>();
    }

    // add value into matrix
    public void addValue(int row, int col, int value) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        if (value != 0) {
            rowIndices.add(row);
            colIndices.add(col);
            values.add(value);
        }
    }

    public void printMatrix() {
        int currentIndex = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {

                if (currentIndex < values.size() && // Checking for non-zero elements
                        rowIndices.get(currentIndex) == i && // Checking for the current element belongs to the row
                        colIndices.get(currentIndex) == j) { // Checking for the current element belongs to the current column
                    System.out.print(values.get(currentIndex) + " ");
                    currentIndex++;
                } else {
                    System.out.print("0 ");
                }
            }


            System.out.println();
        }
    }


    //Этот метод пройдет по всем элементам матрицы и поместит сначала элементы большие b,
    //а затем элементы меньшие b, не сортируя их.
    //В результате мы получим матрицу, в которой ненулевые элементы упорядочены, как описано в задаче.
    public void rearrangeMatrixByThreshold(int b) {
        ArrayList<Integer> rearrangedValues = new ArrayList<>();

        // Сначала добавляем элементы больше или равные b
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            if (value >= b) {
                rearrangedValues.add(value);
            }
        }

        // Затем добавляем элементы меньше b
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            if (value < b) {
                rearrangedValues.add(value);
            }
        }

        // Заменяем значения в исходной матрице согласно новому порядку
        for (int i = 0; i < values.size(); i++) {
            values.set(i, rearrangedValues.get(i));
        }

        // Выводим результат в виде матрицы
        printMatrix();
    }


}

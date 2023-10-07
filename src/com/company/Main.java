package com.company;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = {53, 16, 32, 465, 88, 9, 10};




    }

    // Дано бинарное дерево. Каждую вершину с чётным номером поменять
    // местами с сыном, имеющим чётный номер.
    public static void thirdLab() {
        System.out.println("Input size binary tree: ");
        int sizeBinaryTree = inputInt();
        int element = 0;

        List<Integer> valueBinaryTree = new ArrayList<>(sizeBinaryTree);

        // Enter value to arrBinaryTree
        for (int i = 0; i < sizeBinaryTree; i++) {
            System.out.println("Input value: ");
            valueBinaryTree.add(inputInt());
        }

        BinaryTree binaryTree = new BinaryTree();

        for(int i : valueBinaryTree) {
            binaryTree.insert(i);
        }
        binaryTree.printTree();

        binaryTree.swapNodesWithEvenChildrenAndPrint();


    }

    // Дана разреженная матрица (CS) и число b.
    // Матрица просматривается слева на право, и сверху вниз.
    // На места ненулевых элементов матрицы вначале поместить все её ненулевые элементы большие b,
    // а затем ненулевые элементы меньшие b. Элементы не сортировать.
    public static void secondLab() {
        // Ввод размеров матрицы (строк и столбцов)
        System.out.println("Please, input the matrix size (rows, columns)");
        int rows = inputInt(); // Ввод числа строк
        int columns = inputInt(); // Ввод числа столбцов
        Matrix sparseMatrix = new Matrix(rows, columns); // Создание разреженной матрицы

        // Заполнение матрицы значениями
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println("Enter a value for row " + i + " and column " + j + ":");
                int value = inputInt(); // Ввод значения для элемента матрицы
                sparseMatrix.addValue(i, j, value); // Добавление значения в матрицу
            }
        }

        // Вывод исходной матрицы
        sparseMatrix.printMatrix();
        System.out.println();

        // Ввод значения b
        System.out.println("Please, input b: ");
        int b = inputInt(); // Ввод значения b

        // Перестановка элементов матрицы по заданному порогу b и вывод результата
        sparseMatrix.rearrangeMatrixByThreshold(b);
    }

    //Одинокий король долго ходил по бесконечной шахматной доске.
    // Известна последовательность из N его ходов (вверх, вниз, влево, вправо, вверх-влево и т.п.).
    // Написать программу, определяющую побывал ли король дважды
    // на одном и том же поле за минимально возможное при заданном N число вычислений.
    public static boolean firstLab(int n_moves) {


        int x = 0;  // Координаты x
        int y = 0;  // Координаты y
        String move, currentPosition; // Ход короля | текущая позиция
        Set<String> visitedPosition = new LinkedHashSet<>(); // Set- структура данных хранящая уникальные значения. LinkedHashSet- сохраняет порядок ввода.

        // Делаем ходы королем, пока
        for (int i = 0; i < n_moves; i++) {

            do {
                System.out.println("Введите ход короля. Возможные ходы: вверх, вниз, влево, вправо, вверх-влево, вверх-вправо, вниз-влево, вниз-вправо");
                move = scanner.nextLine();
            }
            while (!isCorrectData(move));

            switch (move) {
                case "вверх":
                    y++;
                    break;
                case "вниз":
                    y--;
                    break;
                case "влево":
                    x--;
                    break;
                case "вправо":
                    x++;
                    break;
                case "вверх-влево":
                    y++;
                    x--;
                    break;
                case "вверх-вправо":
                    y++;
                    x++;
                    break;
                case "вниз-влево":
                    y--;
                    x--;
                    break;
                case "вниз-вправо":
                    y--;
                    x++;
                    break;
            }
            currentPosition = x + ":" + y;
            System.out.println("Текущая позиция короля: " + currentPosition);
            if (visitedPosition.contains(currentPosition)) {
                System.out.println("Король был в этой клетке");
                return true;
            } else {
                visitedPosition.add(currentPosition);
            }
        }

        System.out.println("Король не был в одной клетке два раза ");
        return false;

    }


    // Проверяем входные данные на правильность ввода.
    public static boolean isCorrectData(String data) {

        // ArrayList<String> - динамический массив хранящий String
        ArrayList<String> movesKing = new ArrayList<>(
                Arrays.asList("вверх", "вниз", "влево", "вправо", "вверх-влево", "вверх-вправо", "вниз-влево", "вниз-вправо"));

        // contains - проверка, содержит ли список data
        if (movesKing.contains(data)) {
            return true;
        } else {
            return false;
        }
    }

    public static int inputInt() {
        int value = 0;
        boolean inputValid = false;

        while (!inputValid) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                inputValid = true;
            } else {
                System.out.println("Please, input the digit:");
                scanner.nextLine();
            }
        }

        return value;
    }

}


package com.company;

import java.util.*;
import java.util.regex.Pattern;

import static com.company.SubtreeFinder.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = {53, 16, 32, 465, 88, 9, 10};
        firstLab();

    }

    // Entering and verifying the correct value
    // if digit: return value | else infinite while until the correct value is entered
    public static char inputIsCorrect() {
        String answerForPanel = scanner.nextLine();
        try {
            if (Character.isDigit(answerForPanel.charAt(0))) {
                return answerForPanel.charAt(0);
            } else {
                while (!Character.isDigit(answerForPanel.charAt(0))) {
                    System.out.println("Пожалуйста, введите цифру");
                    answerForPanel = scanner.nextLine();
                }
                return answerForPanel.charAt(0);
            }
        } catch (StringIndexOutOfBoundsException c) {
            System.out.println("bad data");
        }
        return '9';
    }

    public static boolean isPossibleToPair(int[] numbers, int n, HashMap<Integer, Integer> pairs) {
        for (int i = 0; i < n; i++) {
            int num1 = numbers[i];
            int num2 = numbers[2 * n - i - 1];

            if (num1 * num2 < 0) {
                return false; // Если произведение пары имеет разные знаки, разбиение невозможно
            }

            pairs.put(num1, num2);
        }
        return true;
    }

    // int[] numbers = {2, 3, 4, 6, 1, 12};
    // int[] numbers = {-2, -3, -4, -6, -1, -12};

    public static void sixthLab() {

        System.out.print("Введите размер массива (2N): ");
        int size = inputInt();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Введите число #" + (i + 1) + ": ");
            numbers[i] = inputInt();
        }

        // Сортировка массива
        Arrays.sort(numbers);

        int n = numbers.length / 2;
        HashMap<Integer, Integer> pairs = new HashMap<>();

        if (isPossibleToPair(numbers, n, pairs)) {
            // Вывод пар и произведений
            for (int i = 0; i < n; i++) {
                int num1 = pairs.get(numbers[i]);
                int num2 = numbers[i];
                int product = num1 * num2;
                System.out.println(num1 + " " + num2 + " Произведение: " + product);
            }
        } else {
            System.out.println("Невозможно разбить числа на пары с равными произведениями.");
        }
    }

    public static void fifthLab() {
        MyHashTable<String, Integer> myHashTable = new MyHashTable<>();
        System.out.print("Введите количество элементов для хэш-таблицы: "); // Запрашиваем количество элементов у пользователя
        int n = inputInt();
        String SNILS;
        int value;

        for (int i = 0; i < n; i++) {
            SNILS = inputCorrectSNILS(); // Запрашиваем корректный СНИЛС у пользователя
            System.out.println("ВВЕДИТЕ данные: ");
            value = inputInt(); // Запрашиваем целочисленное значение у пользователя
            myHashTable.put(SNILS, value); // Вставляем пару ключ-значение в хэш-таблицу
        }

        myHashTable.printTable(); // Выводим содержимое хэш-таблицы на экран
    }

    public static void fourthLab() {
        TreeNode root = SubtreeFinder.buildTree();
        System.out.println("Дерево:");
        printTree(root);

        TreeNode minRatioSubtree = findSubtreeWithMinRatio(root);
        TreeNode maxRatioSubtree = findSubtreeWithMaxRatio(root);

        System.out.println("\nПоддерево с минимальным отношением (сумма весов / число вершин):");
        printTree(minRatioSubtree);

        System.out.println("\nПоддерево с максимальным отношением (сумма весов / число вершин):");
        printTree(maxRatioSubtree);
    }

    // Дано бинарное дерево. Каждую вершину с чётным номером поменять
    // местами с сыном, имеющим чётный номер.
    public static void thirdLab() {
        System.out.println("Введите размер двоичного дерева: ");
        int sizeBinaryTree = inputInt();
        int element = 0;

        List<Integer> valueBinaryTree = new ArrayList<>(sizeBinaryTree);

        // Enter value to arrBinaryTree
        for (int i = 0; i < sizeBinaryTree; i++) {
            System.out.println("Введите значение: ");
            valueBinaryTree.add(inputInt());
        }

        BinaryTree binaryTree = new BinaryTree();

        for (int i : valueBinaryTree) {
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
        System.out.println("Пожалуйста, введите размер матрицы (строки, столбцы)");
        int rows = inputInt(); // Ввод числа строк
        int columns = inputInt(); // Ввод числа столбцов
        Matrix sparseMatrix = new Matrix(rows, columns); // Создание разреженной матрицы

        // Заполнение матрицы значениями
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println("Введите значение для строки " + i + " и колонна " + j + ":");
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
    public static boolean firstLab() {

        int x = 0;  // Координаты x
        int y = 0;  // Координаты y
        String move;
        boolean isTrue = true;
        String currentPosition = "0:0"; // Ход короля | текущая позиция
        Set<String> visitedPosition = new LinkedHashSet<>(); // Set- структура данных хранящая уникальные зреначения. LinkedHashSet- сохраняет порядок ввода.
        visitedPosition.add(currentPosition);
        while (isTrue) {
            System.out.println("Введите количество ходов короля: ");
            int n_moves = inputInt();

            // Делаем ходы королем, пока
            for (int i = 0; i < n_moves; i++) {
                System.out.println("Введите ход короля. Возможные ходы: вверх, вниз, влево, вправо, вверх-влево, вверх-вправо, вниз-влево, вниз-вправо");
                do {
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
                    System.out.println("Продолжить? 1 - да, 2 - нет");
                    int a = inputInt();
                    if (a == 2) {
                        isTrue = false;
                        return true;
                    } else {
                        visitedPosition.clear();
                        currentPosition = "0:0";
                        visitedPosition.add(currentPosition);
                        x = 0;
                        y = 0;
                    }
                } else {
                    visitedPosition.add(currentPosition);
                    //System.out.println("Введите ход короля. Возможные ходы: вверх, вниз, влево, вправо, вверх-влево, вверх-вправо, вниз-влево, вниз-вправо");
                }
            }
            if (visitedPosition.contains(currentPosition)) {
                System.out.println("Король не был в одной клетке два раза" +
                        " \nАНАЛИТИК ДРУГ ЧЕЛОВЕКА");
            }
            System.out.println("Продолжить? 1 - да, 2 - нет");
            int a = inputInt();
            if (a == 2) {
                isTrue = false;
                return true;
            } else {
                visitedPosition.clear();
                currentPosition = "0:0";
                visitedPosition.add(currentPosition);
                x = 0;
                y = 0;
            }
        }
        return false;
    }

    // Проверяем входные данные на правильность ввода.
    public static boolean isCorrectData(String data) {
        // ArrayList<String> - динамический массив хранящий String
        ArrayListCustom<String> movesKing = new ArrayListCustom<>();
        movesKing.add("вверх");
        movesKing.add("вниз");
        movesKing.add("влево");
        movesKing.add("вправо");
        movesKing.add("вверх-влево");
        movesKing.add("вверх-вправо");
        movesKing.add("вверх-вправо");
        movesKing.add("вниз-влево");
        movesKing.add("вниз-вправо");

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
                System.out.println("Пожалуйста, введите цифру:");
                scanner.nextLine();
            }
        }

        return value;
    }

    // Корректный снилс - 123-456-789 01
    public static String inputCorrectSNILS() {
        Scanner scanner = new Scanner(System.in);
        String regex = "^\\d{3}-\\d{3}-\\d{3} \\d{2}$"; // Регулярное выражение для СНИЛС
        Pattern pattern = Pattern.compile(regex);
        String snils = null;

        do {
            if (snils != null) {
                System.out.println("Введенный СНИЛС не соответствует формату (XXX-XXX-XXX YY). Пожалуйста, попробуйте еще раз.");
            }
            System.out.println("Введите СНИЛС (XXX-XXX-XXX YY):");
            snils = scanner.nextLine();
        } while (!pattern.matcher(snils).matches());

        return snils;
    }

}



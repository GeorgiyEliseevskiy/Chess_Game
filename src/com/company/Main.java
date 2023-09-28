package com.company;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	    int[] arr = {53, 16, 32, 465, 88, 9, 10};

        firstLab(4);

    }

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
            if(visitedPosition.contains(currentPosition)) {
                System.out.println("Король был в этой клетке");
                return true;
            }
            else {
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
        if(movesKing.contains(data)) {
            return true;
        }
        else {
            return false;
        }
    }


}

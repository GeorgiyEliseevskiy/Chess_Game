package com.company.golovolomka;

/**
 * Какуро ("Kakuro") - это числовая головоломка, математический эквивалент кроссворда.
 * Необходимо вставить в клетки цифры от 1 до 9, причем некоторые клетки неактивны (такие клетки помечены черным цветом).
 *  В клетках с заданными числовыми значениями правое верхнее значение означает сумму цифр в ряду,
 * а значение снизу слева равно сумме цифр столбца ниже клетки. Например, число 6 можно представить
 * как сумму 1 и 5, 2 и 4; одинаковые цифры (3 и 3) использовать запрещено.
 */
public class KakuroSolver {

    private final Queue<Queue<Cell>> kakuro;
    // Конструктор класса, принимает на вход начальное состояние кроссворда Kakuro
    public KakuroSolver(Queue<Queue<Cell>> kakuro) {
        this.kakuro = kakuro;
        startSolving();
    }
    // Метод для инициации процесса решения кроссворда
    private void startSolving() {
        // Если решение найдено, вывести решение в консоль и отобразить графически
        if (solve(kakuro, 0, 0)) {
            System.out.println("Solution:");
            kakuro.displayGUI();
        } else {
            System.out.println("Cannot Solve the Puzzle");
        }
    }
    // Рекурсивный метод для решения кроссворда
    private boolean solve(Queue<Queue<Cell>> board, int row, int col) {
        // Если достигнут конец последней строки, значит, решение найдено
        if (row == board.getSize()) {
            return true;
        }
        // Если достигнут конец текущей строки, переход к следующей строке
        Queue q = (Queue) board.getQueue()[0];
        if (col == q.getSize()) {
            return solve(board, row + 1, 0);
        }
        // Если текущая ячейка не является частью головоломки, пропустить ее и перейти к следующей
        if (!board.getElementFrom(row, col).isPuzzle()) {
            return solve(board, row, col + 1);
        }
        // Попытка установки значений от 1 до 9 и проверка их корректности
        for (int i = 1; i <= 9; i++) {
            if (isValidRow(board, row, col, i) && isValidCol(board, row, col, i)) {
                board.getElementFrom(row, col).setValue(i);
                // Рекурсивный вызов для следующей ячейки в текущей строке
                if (solve(board, row, col + 1)) {
                    return true;
                }
            }
        }

        return false;
    }
    // Проверка корректности заполнения строки
    private boolean isValidRow(Queue<Queue<Cell>> board, int row, int col, int value) {
        int sum = value;
        int totalValue = 0;
        // Проверка слева от текущей ячейки
        for (int i = col - 1; i >= 0; i--) {
            if (!board.getElementFrom(row, i).isPuzzle()) {
                totalValue = board.getElementFrom(row, i).getAcross();
                break;
            }
            sum += board.getElementFrom(row, i).getValue();
            if (board.getElementFrom(row, i).getValue() == value) {
                return false;
            }
        }
        // Если сумма превышает установленное значение, возвращается false
        if (sum > totalValue) {
            return false;
        }
        // Проверка условий для последней ячейки в строке
        Queue q = (Queue) board.getQueue()[0];
        if (col == q.getQueueArr().length - 1) {
            if (sum < totalValue) {
                return false;
            }
        } else if (!board.getElementFrom(row, col + 1).isPuzzle()) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }
    // Проверка корректности заполнения столбца
    private boolean isValidCol(Queue<Queue<Cell>> board, int row, int col, int value) {
        int sum = value;
        int totalValue = 0;
        // Проверка сверху от текущей ячейки
        for (int i = row - 1; i >= 0; i--) {
            if (!board.getElementFrom(i, col).isPuzzle()) {
                totalValue = board.getElementFrom(i, col).getDown();
                break;
            }
            sum += board.getElementFrom(i, col).getValue();
            if (board.getElementFrom(i, col).getValue() == value) {
                return false;
            }
        }
        // Если сумма превышает установленное значение, возвращается false
        if (sum > totalValue) {
            return false;
        }
        // Проверка условий для последней ячейки в столбце
        if (row == board.getSize() - 1) {
            if (sum < totalValue) {
                return false;
            }
        } else if (!board.getElementFrom(row + 1, col).isPuzzle()) {
            if (sum < totalValue) {
                return false;
            }
        }

        return true;
    }
}
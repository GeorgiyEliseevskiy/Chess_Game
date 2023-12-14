package com.company.golovolomka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue<T> {

    private final int size;
    private final T[] queue;
    private int front;
    private int rear;

    private static final int GAP = 1;
    // Конструктор очереди
    public Queue(int size) {
        this.size = size;
        queue = (T[]) new Object[size];
        front = -1;
        rear = -1;
    }

    /**
     *Структура файла головоломки:
     * 0,0 - размер (0 строк, 0 столбоцов)
     * Блоки NoClue, Clue, и Puzzle представляют строки и столбцы головоломки.
     *
     * NoClue: Это ячейки, которые не содержат инструкций (нет ни подсказок, ни значений).
     *
     * Clue x y: Это подсказочные ячейки с указанием значений x и y для столбца и строки соответственно.
     *
     * Puzzle: Это головоломочные ячейки, которые будут заполняться числами.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            String str = "game_" + i + ".txt";
            Queue<Queue<Cell>> board = readFromFile(str);
            if (board != null) {
                System.out.println("Puzzle: " + str);
                new KakuroSolver(board);
            }
        }
    }
    // Получение массива очереди
    public T[] getQueueArr() {
        return queue;
    }
    // Получение объектов очереди в виде массива
    public Object[] getQueue() {
        return queue;
    }
    // Получение размера очереди
    public int getSize() {
        return size;
    }
    // Проверка, пуста ли очередь
    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }
    // Проверка, заполнена ли очередь
    public boolean isFull() {
        return (rear + 1) % size == front;
    }
    // Добавление элемента в очередь (enqueue)
    public void enqueue(T value) {
        if (!isFull()) {
            if (isEmpty()) {
                front++;
                rear++;
                queue[rear] = value;
            } else {
                rear = (rear + 1) % size;
                queue[rear] = value;
            }
        }
    }
    // Получение элемента из указанной позиции в очереди
    public Cell getElementFrom(int row, int col) {
        if (queue[row] instanceof Queue) {
            Queue q = (Queue) queue[row];
            if (q.getQueueArr()[col] instanceof Cell) {
                return (Cell) q.getQueueArr()[col];
            }
        }

        return null;
    }
    // Отображение решения головоломки в графическом интерфейсе
    public void displayGUI() {
        // Получаем первую очередь внутри головоломки
        Queue q = (Queue) getQueue()[0];
        // Создаем основную панель
        JPanel mainPanel = new JPanel();
        // Создаем панель для головоломки с сеткой
        JPanel kakuroPanel = new JPanel(new GridLayout(queue.length, q.getQueueArr().length, GAP, GAP));
        // Устанавливаем границы и фон для панели головоломки
        kakuroPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        kakuroPanel.setBackground(Color.BLACK);

        // Итерируемся по объектам внутри очереди
        for (T obj : queue) {
            // Если объект является очередью
            if (obj instanceof Queue) {
                // Преобразуем объект в очередь
                Queue qu = (Queue) obj;
                // Итерируемся по элементам внутри вложенной очереди
                for (int i = 0; i < qu.getQueueArr().length; i++) {
                    // Если элемент является объектом типа Cell
                    if (qu.getQueueArr()[i] instanceof Cell) {
                        // Преобразуем объект в ячейку
                        Cell cell = (Cell) qu.getQueueArr()[i];
                        // Если фон ячейки не серый
                        if (!cell.getBackground().equals(Color.GRAY)) {
                            // Если ячейка является головоломочной
                            if (cell.isPuzzle()) {
                                // Устанавливаем текст ячейки равным её значению
                                cell.setText(String.valueOf(cell.getValue()));
                                // Устанавливаем белый фон
                                cell.setBackground(Color.WHITE);
                            } else {
                                // Если ячейка не головоломочная, устанавливаем текст как три пробела
                                cell.setText("   ");
                                // Устанавливаем светло-серый фон
                                cell.setBackground(Color.LIGHT_GRAY);
                            }
                        }
                        // Устанавливаем предпочтительный размер для ячейки
                        cell.setPreferredSize(new Dimension(50, 50));
                        // Добавляем ячейку на панель головоломки
                        kakuroPanel.add(cell);
                    }
                }
            }
        }

        // Устанавливаем компоновку для основной панели
        mainPanel.setLayout(new BorderLayout());
        // Добавляем панель головоломки на центр основной панели
        mainPanel.add(kakuroPanel, BorderLayout.CENTER);

        // Создаем окно с заголовком "Solution"
        JFrame frame = new JFrame("Solution");
        // Устанавливаем операцию закрытия окна при закрытии
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Добавляем основную панель на окно
        frame.add(mainPanel);
        // Устанавливаем размер окна, достаточный для отображения всех компонентов
        frame.pack();
        // Устанавливаем положение окна по центру экрана
        frame.setLocationRelativeTo(null);
        // Отображаем окно
        frame.setVisible(true);
    }
    // Метод чтения данных из файла и создания структуры данных для головоломки
    private static Queue<Queue<Cell>> readFromFile(String fileName) {
        // Создаем объект файла, используя путь к файлу в директории ресурсов
        File file = new File(System.getProperty("user.dir") + "/resources/" + fileName);
        // Инициализируем переменные для структуры данных головоломки
        Queue<Queue<Cell>> board = null;
        Queue<Cell> queue;

        try {
            // Создаем сканнер для чтения данных из файла
            Scanner scanner = new Scanner(file);

            // Читаем количество строк и столбцов из файла
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();

            // Проверяем, что размеры головоломки больше двух
            if (rows <= 2 || cols <= 2) {
                System.out.println("Error - Dimensions are less than two");
                return null;
            }

            // Инициализируем структуру данных для головоломки
            board = new Queue<>(rows);
            queue = new Queue<>(cols);

            // Читаем данные из файла и создаем структуру головоломки
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Читаем следующую строку из файла
                    String next = scanner.next();
                    switch (next) {
                        case "Puzzle":
                            // Если следующая строка - "Puzzle", добавляем головоломочную ячейку в очередь
                            queue.enqueue(new Cell());
                            break;
                        case "Clue":
                            // Если следующая строка - "Clue", читаем значения для создания подсказочной ячейки
                            int across = scanner.nextInt();
                            int down = scanner.nextInt();
                            // Проверяем, что значения неотрицательны, и добавляем подсказочную ячейку в очередь
                            if (down >= 0 && across >= 0) {
                                queue.enqueue(new Cell(across, down));
                            }
                            break;
                        default:
                            // Если строка не "Puzzle" и не "Clue", создаем обычную ячейку и добавляем в очередь
                            Cell f = new Cell();
                            f.setIsPuzzle(false);
                            queue.enqueue(f);
                            break;
                    }
                    // Если очередь заполнена, добавляем ее в головоломку и создаем новую очередь
                    if (queue.isFull()) {
                        board.enqueue(queue);
                        queue = new Queue<>(cols);
                    }
                }
            }
            // Закрываем сканнер после чтения данных из файла
            scanner.close();
        } catch (FileNotFoundException | NumberFormatException | NoSuchElementException e) {
            // Обрабатываем возможные исключения и выводим сообщение об ошибке
            System.out.println(e.toString());
        }
        // Возвращаем готовую структуру данных головоломки
        return board;
    }

}

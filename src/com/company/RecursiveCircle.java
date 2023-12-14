package com.company;

import javax.swing.*;
import java.awt.*;
//5
// Определение класса RecursiveCircle, наследуемого от JFrame
public class RecursiveCircle extends JFrame {

    private static final int k = 50; // Задаем значение k (условие для прекращения рекурсии)

    // Конструктор класса
    public RecursiveCircle() {
        setTitle("Recursive Circle"); // Заголовок окна
        setSize(800, 600); // Размеры окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        setLocationRelativeTo(null); // Размещение окна по центру экрана
    }

    // Метод для рекурсивного рисования окружностей
    private void drawRecursiveCircle(Graphics g, int x, int y, int r) {
        if (r > k) {
            // Рекурсивные вызовы для четырех окружностей
            drawRecursiveCircle(g, x + r, y, r / 2);
            drawRecursiveCircle(g, x, y + r, r / 2);
            drawRecursiveCircle(g, x - r, y, r / 2);
            drawRecursiveCircle(g, x, y - r, r / 2);
        }

        g.drawOval(x - r, y - r, 2 * r, 2 * r); // Рисование окружности
    }

    // Переопределение метода paint для рисования
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawRecursiveCircle(g, getWidth() / 2, getHeight() / 2, getHeight() / 4); // Начальный вызов рекурсии
    }

    // Точка входа в программу
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveCircle app = new RecursiveCircle(); // Создание экземпляра приложения
            app.setVisible(true); // Сделать окно видимым
        });
    }
}

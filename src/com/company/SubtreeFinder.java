package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    List<TreeNode> children;

    TreeNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}

// Дано N-дерево
// Найти поддеревья с min и max отношением (сумма весов / число вершин). Вывести эти поддеревья.
public class SubtreeFinder {

    //запрашивает корневое значение и количество детей для корневого узла,
    // а затем запрашивает значения для каждого дочернего узла и строит дерево,
    // используя объекты TreeNode. Возвращает корневой узел построенного дерев
    public static TreeNode buildTree() {
        // Вывод строки с просьбой ввести значение корневого узла
        System.out.print("Введите значение корневого узла: ");
        // Считывание введенного значения для корневого узла с использованием метода inputInt()
        int rootValue = Main.inputInt();
        // Создание объекта TreeNode с введенным значением в качестве корневого узла
        TreeNode root = new TreeNode(rootValue);
        // Создание очереди для обработки дочерних узлов в процессе построения дерева
        Queue<TreeNode> queue = new LinkedList<>();
        // Добавление корневого узла в очередь для начала построения дерева
        queue.add(root);


        // Цикл выполняется, пока очередь не пуста, то есть пока есть узлы для обработки
        while (!queue.isEmpty()) {
            // Извлечение узла из очереди для текущей итерации построения дерева
            TreeNode currentNode = queue.poll();
            // Вывод запроса на ввод количества дочерних узлов для текущего узла
            System.out.print("Введите количество детей для узла " + currentNode.val + ": ");
            // Считывание введенного количества дочерних узлов
            int numChildren = Main.inputInt();
            // Цикл для обработки каждого дочернего узла
            for (int i = 0; i < numChildren; i++) {
                // Вывод запроса на ввод значения для текущего дочернего узла
                System.out.print("Введите значение для дочернего узла " + (i + 1) + " узла " + currentNode.val + ": ");
                // Считывание введенного значения для дочернего узла
                int childValue = Main.inputInt();
                // Создание объекта TreeNode с введенным значением в качестве дочернего узла
                TreeNode childNode = new TreeNode(childValue);
                // Добавление созданного дочернего узла в список дочерних узлов текущего узла
                currentNode.children.add(childNode);
                // Добавление дочернего узла в очередь для последующей обработки
                queue.add(childNode);
            }
        }

        return root;
    }

    public static void printTree(TreeNode node, int depth) {
        // Метод для вывода структуры дерева с отступами в зависимости от уровня

        if (node == null) {
            return;
        }
        // Проверка, является ли текущий узел null; если да, то возврат из метода

        // Вывод значения текущего узла с отступами, основанными на глубине дерева
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.val);

        // Рекурсивный вывод дочерних узлов с увеличением глубины
        for (TreeNode child : node.children) {
            printTree(child, depth + 1);
        }
    }


    // Метод для нахождения поддерева с минимальным отношением
    public static TreeNode findSubtreeWithMinRatio(TreeNode root) {
        double[] minRatio = {Double.MAX_VALUE};
        TreeNode[] result = new TreeNode[1];

        findSubtreeWithMinRatioHelper(root, minRatio, result);

        return result[0];
    }

    // Вспомогательный рекурсивный метод для поиска поддерева с минимальным отношением
    private static int findSubtreeWithMinRatioHelper(TreeNode node, double[] minRatio, TreeNode[] result) {
        // Рекурсивный метод для обхода дерева с целью поиска поддерева с минимальным отношением

        // Если текущий узел null, возвращается 0 (количество узлов равно 0)
        if (node == null) {
            return 0;
        }

        int sum = node.val;
        // Инициализация переменных для суммы значений узлов и подсчета количества узлов в поддереве
        int count = 1;

        for (TreeNode child : node.children) {
            // Рекурсивный вызов для каждого дочернего узла для получения суммы значений и количества узлов
            int childSum = findSubtreeWithMinRatioHelper(child, minRatio, result);

            // Обновление общей суммы значений узлов
            sum += childSum;
            // Обновление общего количества узлов в поддереве
            count += countNodes(child);
        }

        // Вычисление отношения суммы значений узлов к их общему количеству
        double ratio = (double) sum / count;

        if (ratio < minRatio[0]) {
            // Если текущее отношение меньше минимального, обновляется значение минимального отношения
            minRatio[0] = ratio;
            // Обновление узла, представляющего поддерево с минимальным отношением
            result[0] = node;
        }

        // Возврат общей суммы значений узлов в текущем поддереве
        return sum;
    }

    // Метод для нахождения поддерева с максимальным отношением
    public static TreeNode findSubtreeWithMaxRatio(TreeNode root) {
        double[] maxRatio = {Double.MIN_VALUE};
        TreeNode[] result = new TreeNode[1];

        findSubtreeWithMaxRatioHelper(root, maxRatio, result);

        return result[0];
    }

    // Вспомогательный рекурсивный метод для поиска поддерева с максимальным отношением
    private static int findSubtreeWithMaxRatioHelper(TreeNode node, double[] maxRatio, TreeNode[] result) {
        // Рекурсивный метод для обхода дерева с целью поиска поддерева с максимальным отношением

        if (node == null) {
            return 0;
        }
        // Если текущий узел null, возвращается 0 (количество узлов равно 0)

        int sum = node.val;
        int count = 1;
        // Инициализация переменных для суммы значений узлов и подсчета количества узлов в поддереве

        for (TreeNode child : node.children) {
            // Рекурсивный вызов для каждого дочернего узла для получения суммы значений и количества узлов
            int childSum = findSubtreeWithMaxRatioHelper(child, maxRatio, result);

            // Обновление общей суммы значений узлов
            sum += childSum;
            // Обновление общего количества узлов в поддереве
            count += countNodes(child);
        }

        // Вычисление отношения суммы значений узлов к их общему количеству
        double ratio = (double) sum / count;

        if (ratio > maxRatio[0]) {
            // Если текущее отношение больше максимального, обновляется значение максимального отношения
            maxRatio[0] = ratio;
            // Обновление узла, представляющего поддерево с максимальным отношением
            result[0] = node;
        }

        // Возврат общей суммы значений узлов в текущем поддереве
        return sum;
    }

    // Метод для подсчета числа вершин в поддереве
    private static int countNodes(TreeNode node) {
        // Рекурсивный метод для подсчета числа вершин в поддереве, включая текущий узел

        // Если текущий узел null, возвращается 0 (количество узлов равно 0)
        if (node == null) {
            return 0;
        }

        // Инициализация переменной для подсчета числа вершин, начиная с текущего узла
        int count = 1;

        // Рекурсивный вызов для каждого дочернего узла для получения числа вершин
        for (TreeNode child : node.children) {
            count += countNodes(child);
        }

        // Возврат общего числа вершин в текущем поддереве
        return count;
    }

}

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

// 14 Дано N-дерево. Найти все поддеревья, структура которых совпадает с заданной.
public class SubtreeFinder {

    //запрашивает корневое значение и количество детей для корневого узла,
    // а затем запрашивает значения для каждого дочернего узла и строит дерево,
    // используя объекты TreeNode. Возвращает корневой узел построенного дерев
    public static TreeNode buildTree() {
        System.out.print("Введите значение корневого узла: ");
        int rootValue = Main.inputInt();
        TreeNode root = new TreeNode(rootValue);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.print("Введите количество детей для узла " + currentNode.val + ": ");
            int numChildren = Main.inputInt();

            for (int i = 0; i < numChildren; i++) {
                System.out.print("Введите значение для дочернего узла " + (i + 1) + " узла " + currentNode.val + ": ");
                int childValue = Main.inputInt();
                TreeNode childNode = new TreeNode(childValue);
                currentNode.children.add(childNode);
                queue.add(childNode);
            }
        }

        return root;
    }

    public static void printTree(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        for (TreeNode child : node.children) {
            printTree(child);
        }
    }

    // Метод для нахождения поддерева с минимальным отношением
    public static TreeNode findSubtreeWithMinRatio(TreeNode root) {
        double[] minRatio = { Double.MAX_VALUE };
        TreeNode[] result = new TreeNode[1];

        findSubtreeWithMinRatioHelper(root, minRatio, result);

        return result[0];
    }

    // Вспомогательный рекурсивный метод для поиска поддерева с минимальным отношением
    private static int findSubtreeWithMinRatioHelper(TreeNode node, double[] minRatio, TreeNode[] result) {
        if (node == null) {
            return 0;
        }

        int sum = node.val;
        int count = 1;

        for (TreeNode child : node.children) {
            int childSum = findSubtreeWithMinRatioHelper(child, minRatio, result);
            sum += childSum;
            count += countNodes(child);
        }

        double ratio = (double) sum / count;

        if (ratio < minRatio[0]) {
            minRatio[0] = ratio;
            result[0] = node;
        }

        return sum;
    }

    // Метод для нахождения поддерева с максимальным отношением
    public static TreeNode findSubtreeWithMaxRatio(TreeNode root) {
        double[] maxRatio = { Double.MIN_VALUE };
        TreeNode[] result = new TreeNode[1];

        findSubtreeWithMaxRatioHelper(root, maxRatio, result);

        return result[0];
    }

    // Вспомогательный рекурсивный метод для поиска поддерева с максимальным отношением
    private static int findSubtreeWithMaxRatioHelper(TreeNode node, double[] maxRatio, TreeNode[] result) {
        if (node == null) {
            return 0;
        }

        int sum = node.val;
        int count = 1;

        for (TreeNode child : node.children) {
            int childSum = findSubtreeWithMaxRatioHelper(child, maxRatio, result);
            sum += childSum;
            count += countNodes(child);
        }

        double ratio = (double) sum / count;

        if (ratio > maxRatio[0]) {
            maxRatio[0] = ratio;
            result[0] = node;
        }

        return sum;
    }

    // Метод для подсчета числа вершин в поддереве
    private static int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int count = 1;

        for (TreeNode child : node.children) {
            count += countNodes(child);
        }

        return count;
    }

}

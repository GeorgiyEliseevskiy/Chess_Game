package com.company;


public class BinaryTree {

    private static class TreeNode { // статический, потому что не должен иметь доступа к внешним полям
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public TreeNode root;

    public BinaryTree() {
        root = null;
    }

    // Рекурсивный метод добавления нового элемента
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // root - текущий элемент
    // если root = null, то это конец двоичного дерева
    // Рекурсивно определяем, какое поддерево вставить
    // возвращаем root
    public TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Обмен вершины с четным номером с ее четным сыном
    public TreeNode swapNodesWithEvenChildren(TreeNode node) {
        if (node == null) {
            return node;
        }

        // Рекурсивно вызываем для левого и правого поддеревьев
        node.left = swapNodesWithEvenChildren(node.left);
        node.right = swapNodesWithEvenChildren(node.right);

        // Если у текущей вершины четный номер
        if (node.data % 2 == 0) {
            // Обменяем сыновьями с четными номерами, если они есть
            if (node.left != null && node.left.data % 2 == 0) {
                int temp = node.data;
                node.data = node.left.data;
                node.left.data = temp;
            } else if (node.right != null && node.right.data % 2 == 0) {
                int temp = node.data;
                node.data = node.right.data;
                node.right.data = temp;
            }
        }

        return node;
    }

    // Метод для обмена вершин с четными номерами и вывода дерева
    public void swapNodesWithEvenChildrenAndPrint() {
        root = swapNodesWithEvenChildren(root);
        printTree();
    }


    public void printTree() {
        System.out.println("Древовидное представление дерева:");
        printTreeRec(root, 0);
    }

    // Рекурсивная print
    private void printTreeRec(TreeNode currentNode, int level) {
        if (currentNode != null) {
            // Печать уровней
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(currentNode.data);
            // Рекурсивный вызов для левого и правого поддеревьев
            printTreeRec(currentNode.left, level + 1);
            printTreeRec(currentNode.right, level + 1);
        }
    }
}
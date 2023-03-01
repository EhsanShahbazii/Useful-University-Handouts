import sun.misc.Queue;

import java.util.Stack;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);
    }

    public static int nodeHeights(Node node) {
        if (node == null) return -1;
        if (node.left == null && node.right == null) return 0;
        else {
            int lh = nodeHeights(node.left);
            int rh = nodeHeights(node.right);

            return Math.max(lh + 1, rh + 1);
        }
    }

    public static int leafCounts(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        else {
            return leafCounts(node.left) + leafCounts(node.right);
        }
    }

    public static int internalNodesCount(Node node) {
        if (node == null) return 0;
        else return (1 + internalNodesCount(node.left) + internalNodesCount(node.right));
    }

    public static void deleteTree(Node node) {
        if (node == null) return;
        deleteTree(node.left);
        deleteTree(node.right);
        System.out.println(node.m_data);
    }

    public static int maxInTree(Node node) {
        int lmax, rmax;
        if (node == null) return Integer.MIN_VALUE;

        lmax = maxInTree(node.left);
        rmax = maxInTree(node.right);

        return Math.max(lmax, rmax);
    }

    public static void preOrder(Node root) {
        if (root == null) {}
        else {
            System.out.println(root.m_data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void inOrder(Node root) {
        if (root == null) {}
        else {
            inOrder(root.left);
            System.out.println(root.m_data);
            inOrder(root.right);
        }
    }

    public static void postOrder(Node root) {
        if (root == null) {}
        else {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.m_data);
        }
    }

    public static void inOrderStack(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        boolean flag = false;

        while (!flag) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                if (!stack.empty()) {
                    current = stack.pop();
                    System.out.println(current);
                    current = current.right;
                } else {
                    flag = true;
                }
            }
        }
    }

    public static void levelOrder(Node root) throws InterruptedException {
        Node temp = root;
        Queue<Node> queue = new Queue<>();

        while (temp != null) {
            System.out.println(temp.m_data);

            if (temp.left != null) queue.enqueue(temp.left);
            if (temp.right != null) queue.enqueue(temp.right);

            temp = queue.dequeue();
        }
    }

    public static boolean identical(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true;

        if (root1 != null && root2 != null)
            return root1.m_data == root2.m_data && identical(root1.left, root2.left) && identical(root1.right, root2.right);
        return false;
    }

    public static boolean isComplete(Node root) throws InterruptedException {
        Node temp;
        Queue<Node> queue = new Queue<>();
        boolean flag = false;

        if (root == null) return true;
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            temp = queue.dequeue();
            if (temp.left != null) {
                if (flag) return false;
                queue.enqueue(temp.left);
            } else {
                flag = true;
            }
            if (temp.right != null) {
                if (flag) return false;
                queue.enqueue(temp.right);
            } else {
                flag = true;
            }
        }
        return true;
    }

    public static int findLevel(Node root, Node item, int level) {
        if (root == null) return 0;
        if (root == item) return level;

        int index;
        index = findLevel(root.left, item, level + 1);
        if (index != 0) return index;
        index = findLevel(root.right, item, level + 1);

        return index;
    }

    public static boolean ancestor(Node root, Node item) {
        if (root == null) return false;
        if (root == item) return true;

        if (ancestor(root.left, item) || ancestor(root.right, item)) {
            System.out.println(root.m_data);
            return true;
        }
        return false;
    }
}

class Node {
    int m_data;
    Node left;
    Node right;

    Node() {}

    Node(int data) {
        m_data = data;
        left = null;
        right = null;
    }
}

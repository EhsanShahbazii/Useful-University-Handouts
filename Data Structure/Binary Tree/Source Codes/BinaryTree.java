public class Main {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);

        preOrder(root);
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

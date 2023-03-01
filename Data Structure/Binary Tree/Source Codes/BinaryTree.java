public class Main {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);

        System.out.println();
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

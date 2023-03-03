public class BST {
    public static Node insert(Node root, int value) {
        if (root == null) return new Node(value);

        if (value < root.m_data)
            root.left = insert(root.left, value);
        else
            root.right = insert(root.right, value);

        return root;
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

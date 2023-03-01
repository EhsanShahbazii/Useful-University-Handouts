## درخت دودویی (binary tree)
درخت مرتبی که هر عنصر آن حداکثر دارای دو فرزند باشد.
```console
           A                      A                    P                      F                   1                   1
         /   \                  /   \                /   \                  /   \                / \                 / \
        B     C                B     C              F     S                B     G              2   3               2   3
       / \     \              / \   /              / \   / \              / \     \            / \                 / \
      D   F     H            D   E  F             B   H  R  Y            A   D     I          4   5               4   5
     / \   \   /            /        \               /     / \              / \    /             /
    I  J    L  D           H          I             G     T   Z            C   E  H             6
                          / \        /               \
                         K   L      M                W
```
## درخت دودویی پر (full binary tree)
ذرختی که در آن هر گره به غیر از برگ ها دارای دو فرزند است.
- count of leafs: `2^h`
- count of nodes: `2^(h+1) - 1`
- count of internal nodes: `floor(n/2)`
- always in binary tree: `n(0) = n(2) + 1`

## درخت دودویی کامل (complete binary tree)
هر سطح به جز احتمالا آخرین سطح، کاملا پر شده است و همه گره ها تا جایی که ممکن است در چپ درخت قرار می گیرند.

## پیاده سازی درخت دودویی
هر گره در درخت دودویی شامل سه قسمت `data, left, right` می باشد.
```java
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
```
ما یک درخت دارای 6 گره می سازیم که شکل آن به صورت زیر می باشد:
```console
     1
    / \
   2   3
  / \
 4   5
    /
   6
```
و پیاده سازی آن نیز به صورت زیر است:
```java
public static void main(String[] args) {
   Node root = new Node(1);
   root.left = new Node(2);
   root.right = new Node(3);
   root.left.left = new Node(4);
   root.left.right = new Node(5);
   root.left.right.left = new Node(6);
}
```

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
برای یافتن ارتفاع یک گره به صورت زیر عمل می کنیم:
```java
public static int nodeHeights(Node node) {
    if (node == null) return -1;
    if (node.left == null && node.right == null) return 0;
    else {
        int lh = nodeHeights(node.left);
        int rh = nodeHeights(node.right);

        return Math.max(lh + 1, rh + 1);
    }
}
```
برای محاسبه تعداد برگ ها به صورت زیر می نویسیم:
```java
public static int leafCounts(Node node) {
    if (node == null) return 0;
    if (node.left == null && node.right == null) return 1;
    else {
        return leafCounts(node.left) + leafCounts(node.right);
    }
}
```
برای یافتن تعداد گره ها نیز این کد را می نویسیم:
```java
public static int internalNodesCount(Node node) {
    if (node == null) return 0;
    else return (1 + internalNodesCount(node.left) + internalNodesCount(node.right));
}
```
برای حذف کردن کل درخت نیز به صورت زیر می نویسیم:
```java
public static void deleteTree(Node node) {
    if (node == null) return;
    deleteTree(node.left);
    deleteTree(node.right);
    System.out.println(node.m_data);
}
```
برای یافتن بزرگ ترین مقدار در بین گره های درخت نیز می نویسیم:
```java
public static int maxInTree(Node node) {
    int lmax, rmax;
    if (node == null) return Integer.MIN_VALUE;

    lmax = maxInTree(node.left);
    rmax = maxInTree(node.right);

    return Math.max(lmax, rmax);
}
```
## پیمایش درخت دودویی
سه مدل پیمایش داریم که به عبارتند از:
1. پیشوندی (preorder) => VLR => ریشه، چپ، راست
2. میانوندی (inorder) => LVR => چپ، ریشه، راست
3. پسوندی (postorder) => LRV => چپ، راست، ریشه

مثال: سه پیمایش پیشوندی، میانوندی و پسوندی را در این درخت پیاده بکنید.
```console
            A
          /   \
         B      C
        /     /   \
       D     E     F
              \   / \
               G H   I
```
پاسخ: ابتدا پیشوندی بعد میانوندی و در نهایت پسوندی را می نویسیم:
- preorder (VLR): A B D C E G F H I
- inorder (LVR): D B A E G C H F I
- postorder (LRV): D B G E H I F C A
## پیاده سازی پیمایش ها
پیمایش پیشوندی به صورت زیر نوشته می شود: preOrder VLR
```java
public static void preOrder(Node root) {
    if (root == null) {}
    else {
        System.out.println(root.m_data);
        preOrder(root.left);
        preOrder(root.right);
    }
}
```
پیمایش میانوندی نیز به صورت زیر است: inOrder LVR
```java
public static void inOrder(Node root) {
    if (root == null) {}
    else {
        inOrder(root.left);
        System.out.println(root.m_data);
        inOrder(root.right);
    }
}
```
و در آخر پسوندی به صورت زیر نوشته می شود: postOrder LRV
```java
public static void postOrder(Node root) {
    if (root == null) {}
    else {
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.m_data);
    }
}
```
دقت کنید که تنها جای پرینت عوض می شود و بقیه دستورات به همان شکل می باشد.

## پیمایش میانوندی به صورت غیر بازگشتی
ترتیب دستورات به صورت زیر است:
1. اول یک استک `stack` خالی درست می کنیم.
2. یک متغیر `current` می سازیم و مقدار اولیه `root` می دهیم.
3. متغیر `current` را به استک `push` می کنیم و مقدارش را برابر `current = current.left` می دهیم. این کار را تا زمانیکه `current` برابر با `null` نشده باشد تکرار می کنیم.
4. وقتی مقدار `current` برابر با `null` شد و استک ما خالی نیست دستورات زیر را انجام می دهیم:
   1. متغیر `x` را از استک `pop` می کنیم.
   2. آن را چاپ می کنیم و مقدار `current` را برابر با `current = x.right` قرار می دهیم.
   3. دوباره مرحله سوم را تکرار می کنیم.
4. اگر مقدار `current` برابر با `null` شده و استک ما خالی شده باشد کار ما به اتمام رسیده است.

به صورت زیر می توانیم این دستورات را پیاده سازی کنیم:
```java
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
```

## الگوریتم پیمایش به ترتیب سطح (Level Order)
گره ها را از بالا به پایین و در هر سطح از سمت چپ به راست ملاقات می کنیم. به طور مثال داریم:
```console
            A
          /   \
         B      C
        /     /   \
       D     E     F
              \   / \
               G H   I
```
که به صورت زیر گره ها ملاقات می شوند:
```console
A B C D E F G H I
```
و پیاده سازی آن به صورت زیر می باشد:
```java
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
```

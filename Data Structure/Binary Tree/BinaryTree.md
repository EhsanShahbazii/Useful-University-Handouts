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

## مشخص کردن گره های تک فرزندی
می توان با استفاده از دو پیمایش `preorder` و `postorder` یک درخت دودویی، گره های تک فرزندی را پیدا کرد. به این صورت که در `preorder` از سمت چپ به راست حرکت کرده و زوج مرتب هایی که معکوس آن در`postorder` آمده است را می نویسیم. اولین گره در این زوج ها گره های تک فرزندی است.
```console
     1
    / \
   2   3
  / \
 4   5
    /
   6
```
به طور مثال پیمایش های ما به صورت زیر است:
```console
preorder: 1 2 4 5 6 3
postorder: 4 6 5 2 3 1
```
اگر دقت کنیم زوج مرتب های `(5,6)` وجود دارد که معکوس آن در `postorder` آمده است `(6,5)`. پس گره `5` تک فرزند است.

## رسم درخت دودویی با پیمایش پیشوندی و پسوندی
در این حالت ما می توانیم بگوییم کدام گره ها تک فرزند هستند ولی نمی توانیم موقعیت آن ها را به دست بیاوریم. یعنی اگر `k` تعداد گره های تک فرزندی ما باشد، به تعداد `n = 2^k` تا حالت برای رسم درخت خواهیم داشت.

## بررسی یکسان بودن دو درخت دودویی
دو درخت زمانیکه مقادیر گره ها با هم برابر باشند و ترتیب آنها نیز با هم یکسان باشد، این دو درخت با هم برابر می شوند. پیاده سازی آن به صورت زیر است:
```java
public static boolean identical(Node root1, Node root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 != null && root2 != null)
        return root1.m_data == root2.m_data && identical(root1.left, root2.left) && identical(root1.right, root2.right);
    return false;
}
```

## بررسی کامل بودن یک درخت دودویی
درخت دودویی کامل در هر سطح به جز احتمالا آخرین سطح، کاملا پر شده است و همه گره ها تا جایی که ممکن است در چپ درخت قرار می گیرند. پیاده سازی به صورت زیر است:
```java
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
```
## تعیین سطح یک گره در درخت دودویی
برای یافتن سطح یک گره در یک درخت دودویی به صورت زیر کد می نوسیم. دقت کنید که پارامتر `level` همان لول شروعی می باشد.
```java
public static int findLevel(Node root, Node item, int level) {
    if (root == null) return 0;
    if (root == item) return level;

    int index;
    index = findLevel(root.left, item, level + 1);
    if (index != 0) return index;
    index = findLevel(root.right, item, level + 1);

    return index;
}
```

## تعیین اجداد یک گره در یک درخت دودویی
برای یافتن اجداد یک گره می توان به صورت زیر نوشت:
```java
public static boolean ancestor(Node root, Node item) {
    if (root == null) return false;
    if (root == item) return true;

    if (ancestor(root.left, item) || ancestor(root.right, item)) {
        System.out.println(root.m_data);
        return true;
    }
    return false;
}
```

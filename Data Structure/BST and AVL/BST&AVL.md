## درخت جستجوی دودویی (BST)
درختی را جستجوی دودویی (Binary Search Tree) می نامند که:
1. عناصر زیر درخت چپ، کوچک تر از ریشه باشند.
2. عناصر زیر درخت راست، بزرگ تر از ریشه باشند.
3. زیر درختان چپ و راست، درختان جستجوی دودویی باشند.
4. گره یا عنصر تکراری نداشته باشد.
```console
      9
     / \
    5   12
   / \    \
  3   8    20
```
ارتفاع درخت متغیر بوده و به صورت : `lg(n) <= h <= n` است.
## پیاده سازی درج کردن
برای درج کردن یک گره به درخت به صورت زیر انجام می شود:
```java
public static Node insert(Node root, int value) {
    if (root == null) return new Node(value);

    if (value < root.m_data)
        root.left = insert(root.left, value);
    else
        root.right = insert(root.right, value);

    return root;
}
```

## جستجوی یک عنصر در BST
مقدار کلید عنصر مورد نظر با ریشه مقایسه می شود:
1. اگر برابر باشد به نتیجه رسیده ایم.
2. اگر کمتر از مقدار ریشه باشد، زیر درخت `راست` را به صورت بازگشتی جستجو می نماییم.
3. اگر بزرگتر از مقدار ریشه باشد، زیر درخت `چپ` را به صورت بازگشتی جستجو می نماییم.

## پیاده سازی جستجوی دودویی (بازگشتی)
کافی است منطق و دستورات بالا را پیاده کنیم که به صورت زیر می شود:
```java
public static Node search(Node root, int value) {
    if (root == null || root.m_data == value) return root;
        
    if (value < root.m_data)
        return search(root.left, value);
    else 
        return search(root.right, value);
}
```

## پیاده سازی جستجوی دودویی (بدون بازگشتی)
برای اینکه از روش بازگشتی استفاده نکنیم به صورت زیر می نویسیم:
```java
public static boolean search(Node root, int value) {
    while (root != null) {
        if (value > root.m_data)
            root = root.right;
        else if (value < root.m_data)
            root = root.left;
        else
            return true;
    }
    return false;
}
```

## دنباله جستجو
دنباله جستجوی کلید `x` دنباله ای از برچسب های گره های آن درخت است که در یافتن موفق(با ناموفق) ملاقات می شوند.

## حذف یک گره از BST
پس از یافتن گره مورد نظر برای حذف، یکی از سه حالت زیر ممکن است رخ بدهد:
1. اگر گره فاقد فرزند باشد، به سادگی حدف شده و نیازی به تنظیم درخت نمی باشد.
2. اگر گره فقط دارای یک فرزند باشد، فرزند آن به طرف بالا در درخت منتقل می شود.
3. اگر دارای دو فرزند باشد، گره بعدی یا قبلی در پیمایش `inorder` جای آن را می گیرد.

## پیاده سازی حذف یک گره در درخت جستجوی دودویی
برای حذف یک گره از درخت به صورت زیر می نویسیم:
```java
public static Node remove(Node root, int value) {
    if (root == null) return root;
    Node temp;

    if (value < root.m_data)
        root.left = remove(root.left, value);
    else if (value > root.m_data)
        root.right = remove(root.right, value);
    else {
        if (root.left == null) {
            temp = root.right;
            return root;
        } else if (root.right == null) {
            temp = root.left;
            return root;
        }

        temp = min(root.right);
        root.m_data = temp.m_data;
        root.right = remove(root.right, temp.m_data);
    }
    return root;
}
```
برای پیدا کردن چپ ترین گره در یک درخت به صورت زیر می توانیم بنویسیم:
```java
public static Node min(Node root) {
    Node temp = root;

    while (temp.left != null)
        temp = temp.left;

    return temp;
}
```

## مرتب سازی درخت (Tree Sort)
با ایجاد یک درخت جستجوی دودویی با `n` عدد و سپس پیمایش `inorder` درخت، می توان یک لیست مرتب شده از اعداد را بدست آورد.
```console
      9
     / \
    5   12
   / \    \
  3   8    20
```
```console
inOrder: 3, 5, 8, 9, 12, 20
```

## روش های مرتب سازی
ما چند نوع روش برای مرتب کردن داریم که عبارتند از:
1. حبابی (Bubble Sort)
2. انتخابی (Selection Sort)
3. درجی (Insertion Sort)
4. ادغامی (Merge Sort)
5. سریع (Quick Sort)
6. هیپ (Heap Sort)
7. درختی (Tree Sort)

## مرتب سازی حبابی
در این روش عناصر دو به دو مقایسه می شود و در هر فاز حداقل یک عنصر در جای اصلی خود قرار می گیرد.
```java
public static int[] bubbleSort(int[] list) {
    int i, j;
    for (i = 0; i < list.length - 1; i++) {
        for (j = 0; j < list.length - 1 - i; j++) {
            if (list[j] > list[j+1]) {
                int temp = list[j];
                list[j] = list[j+1];
                list[j+1] = temp;
            }
        }
    }
    return list;
}
```
می توانیم این روش مرتب سازی حبابی را اندکی بهینه تر کنیم. به این صورت که این روش در همه حالت ها حتی وقتی آرایه مرتب باشد، پیچیدگی زمانی آن `O(n)` می باشد، می توانیم وقتی دیگر عنصری برای عوض کردن وجود ندارد دیگر مرتب سازی را تمام کنیم. به صورت زیر می توانیم بنویسیم:
```java
public static int[] bubbleSortOp(int[] list) {
    int i, j, temp;
    boolean swapped;
    for (i = 0; i < list.length - 1; i++) {
        swapped = false;
        for (j = 0; j < list.length - 1 - i; j++) {
            if (list[j] > list[j+1]) {
                temp = list[j];
                list[j] = list[j+1];
                list[j+1] = temp;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
    return list;
}
```

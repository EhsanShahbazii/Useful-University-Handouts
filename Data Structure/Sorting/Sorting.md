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
می توانیم این روش مرتب سازی حبابی را اندکی بهینه تر کنیم. به این صورت که این روش در همه حالت ها حتی وقتی آرایه مرتب باشد، پیچیدگی زمانی آن `O(n^2)` می باشد، می توانیم وقتی دیگر عنصری برای عوض کردن وجود ندارد دیگر مرتب سازی را تمام کنیم. به صورت زیر می توانیم بنویسیم:
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
### کاربرد مرتب سازی حبابی
به دلیل سادگی، مرتب‌ سازی حبابی اغلب برای معرفی مفهوم الگوریتم مرتب‌ سازی استفاده می‌ شود.
در گرافیک کامپیوتری، به دلیل توانایی آن برای شناسایی یک خطای کوچک (مانند جابجایی فقط دو عنصر) در آرایه‌های تقریبا مرتب شده و رفع آن فقط با خطی محبوب است.
پیچیدگی `(2n)`.

### مزایا
- مرتب سازی حبابی به راحتی قابل درک و پیاده سازی است.
- به هیچ فضای حافظه اضافی نیاز ندارد.
- سازگار با انواع مختلف داده است.
### معایب
- مرتب‌ سازی حبابی دارای پیچیدگی زمانی `O(n^2)` است که آن را برای مجموعه‌های داده بزرگ بسیار کند می‌کند.
- برای مجموعه داده های بزرگ کارآمد نیست، زیرا نیاز به عبور چندگانه از داده ها دارد.
- این یک الگوریتم مرتب‌ سازی پایدار نیست، به این معنی که عناصر با مقدار کلید یکسان ممکن است نظم نسبی خود را در خروجی مرتب‌شده حفظ نکنند.

### تحلیل پرفورمنس و پیچیدگی الگوریتم 
|پرفورمنس|پیچیدگی|
|------|------|
|Worst-case performance|`O(n^2)` comparisons and swaps|
|Best-case performance|`O(n)` comparisons, `O(1)` swaps|
|Average performance|`O(n^2)`  comparisons and swaps|
|Worst-case space complexity|`O(n)` total, `O(1)` auxiliary|

## مرتب سازی انتخابی
در این روش کوچک ترین عنصر پیدا شده و در سمت چپ قرار می گیرد. در این حالت ما آرایه به دو قسمت سمت چپ(مرتب شده) و سمت راست(نا مرتب) داریم. هر باز کوچک ترین عضو را در قسمت راست پیدا می کنیم و به سمت جپ انتقال می دهیم.
```java
public static int[] insertionSort(int[] list) {
    int i, j, key, min;
    for (i = 0; i < list.length - 1; ++i) {
        key = list[i];
        j = i - 1;
        while (j >= 0 && list[j] > key) {
            list[j + 1] = list[j];
            j = j - 1;
        }
        list[j + 1] = key;
    }
    return list;
}
```
### کاربرد مرتب سازی حبابی
مرتب سازی درج زمانی استفاده می شود که تعداد عناصر کم باشد. همچنین زمانی که آرایه ورودی تقریبا مرتب شده باشد، می تواند مفید باشد، فقط تعداد کمی از عناصر در یک آرایه کامل بزرگ قرار گرفته اند.

### تحلیل پرفورمنس و پیچیدگی الگوریتم
|پرفورمنس|پیچیدگی|
|------|------|
|Worst-case performance|`O(n^2)` comparisons and swaps|
|Best-case performance|`O(n)` comparisons, `O(1)` swaps|
|Average performance|`O(n^2)` comparisons and swaps|
|Worst-case space complexity|`O(n)` total, `O(1)` auxiliary|

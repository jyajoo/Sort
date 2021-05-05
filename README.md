# 정렬

모든 정렬의 결과 : 오름차순 정렬

```java
interface SortMethod {         // 정렬 방법을 설정하는 메서드
    int[] sortArray(int[] A);
}
```



## 1. 버블 정렬(Bubble Sort)

서로 붙어있는 두 개의 숫자들을 차례대로 비교해가며 작은 수를 앞쪽으로 이동시켜가는 알고리즘

입력을 전체적으로 한 번 처리하는 과정인 pass를 할 때마다, 큰 수가 배열의 마지막 원소에 위치하게 된다. ( 반복되며 큰 수부터 차례대로 정렬됨 )

따라서, 총 n - 1 번의 pass가 수행된다.

```java
class BubbleSort implements SortMethod {   // 1. 버블 정렬

    @Override
    public int[] sortArray(int[] A) {

        int n = A.length;
        int tmp;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (A[j] > A[j + 1]) {
                    tmp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = tmp;
                }
        return A;
    }
}
```

- n : 배열의 크기

- n - 1번의 pass가 수행된다.

- 인덱스 0부터 n - pass까지 비교하며 큰 수가 뒤쪽에 위치하도록 해준다.



## 2. 선택 정렬(Selection Sort)

입력 배열 전체에서 가장 작은 값을 선택하여 배열의 앞쪽으로 위치하도록 바꿔가는 알고리즘

앞쪽으로 차례대로 작은 수부터 정렬된다.

```java
class SelectionSort implements SortMethod {   // 2. 선택 정렬

    @Override
    public int[] sortArray(int[] A) {

        int n = A.length;
        int min, tmp;

        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (A[min] > A[j]) {
                    min = j;
                }
            }
            tmp = A[min];
            A[min] = A[i];
            A[i] = tmp;
        }
        return A;
    }
}
```

- 가장 작은 값을 A[ i ]로 두고, 0부터 n - 2까지 가장 작은 값을 찾도록 한다.
- 최소값으로 설정한 A[ i ]보다 작은 값(A[ j ])이 발견되면, 가장 작은 값(A[ i ]의 인덱스(min)를  j로 바꿔주며, 최종적으로 가장 작은 값을 찾아준다.
- 최소값으로 설정한 A[i]와 A[min]을 교환해준다.



## 3. 삽입 정렬(Insertion Sort)

배열을 정렬된 부분(앞부분)과 정렬이 안된 부분(뒷부분)으로 나누어 주며, 정렬이 안된 부분의 숫자를 정렬된 부분에서 적절한 위치에 삽입해주는 알고리즘

```java
class InsertionSort implements SortMethod {   // 3. 삽입 정렬

    @Override
    public int[] sortArray(int[] A) {

        int n = A.length;

        for (int i = 1; i < n; i++) {
            int currentElement = A[i];
            int j = i - 1;
            while (j >= 0 && currentElement < A[j]) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = currentElement;
        }
        return A;
    }
}
```

- currentElement : 정렬이 안된 부분에서의 첫 번째 원소
- j : 정렬된 부분에서 가장 오른쪽에 위치한 원소로부터 왼쪽 방향으로 삽입할 곳이 탐색된다.
- j가 0보다 커야 하며, 정렬된 부분에서의 A[ j ]가 currentElement보다 큰 경우 오른쪽으로 한 칸 옮겨준 후 j를 왼쪽으로 이동시키며 반복적으로 수행한다.
- A[ j + 1]에 currentElement를 저장한다.



## 4. 쉘 정렬(Shell Sort)

버블 정렬에서 작은 값이 앞부분으로 느리게 이동하는 점, 삽입 정렬에서 다른 숫자들이 한 칸씩 오른쪽으로 이동해야하는 점, 이러한 단점을 보완한 것이 쉘 정렬이다.

배열의 뒷부분의 작은 수를 앞부분으로 빠르게 이동시키고, 동시에 앞부분의 큰 수를 뒷부분으로 이동시키며 마지막에는 삽입 정렬을 수행하는 알고리즘

```java
class ShellSort implements SortMethod {   // 4. 쉘 정렬

    @Override
    public int[] sortArray(int[] A) {

        int n = A.length;

        for (int h = n / 2; h > 0; h /= 2) {
            for (int i = h; i < n; i++) {
                int currentElement = A[i];
                int j = i;
                while (j >= h && A[j - h] > currentElement) {
                    A[j] = A[j - h];
                    j = j - h;
                }
                A[j] = currentElement;
            }
        }
        return A;
    }
}
```

- 배열에서 간격별로 그룹을 나누어준다.
- h : 간격, (초깃값 : n / 2)
- 각 그룹별로 삽입 정렬을 수행하며, 간격(h)를 절반으로 줄여가며 반복한다.
- 마지막에는 간격(h)가 1이 되어 수행된다.



### 입력 배열

```java
interface InputData {

    int[] makeData(int n);   // 정렬해줄 데이터를 설정하는 메서드
}
```

#### 

```java
class CreateArray {         // 랜덤 배열 생성

    public int[] create(int n) {

        Random r = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(50);
        }

        return arr;
    }
}
```



#### 1. 랜덤 배열

```java
class RandomData extends CreateArray implements InputData {  // (1) 랜덤한 입력 배열 생성

    @Override
    public int[] makeData(int n) {

        int[] arr = create(n);

        return arr;
    }
}
```

- CreateArray 클래스의 create메서드를 통해 난수를 입력받아 배열을 구성해준다.



#### 2. 어느정도 정렬된 배열

```java
class AlmostSortedData extends CreateArray implements InputData { // (2) 어느정도 정렬된 입력 배열 생성

    @Override
    public int[] makeData(int n) {

        int[] arr = create(n);
        Arrays.sort(arr);      // 오름차순 정렬
        Swap(n, arr);          // 일부분 섞어주기기

        return arr;
    }

    private void Swap(int n, int[] arr) {

        int p = (int) (n * 0.1); // n의 10퍼센트(p)정도
        if (p < 2) p = 2;
        p = p / 2;               // 횟수 p/2번 swap

        Random r = new Random();
        int cnt = 0, tmp, s1, s2;

        while (cnt < p) {
            s1 = r.nextInt(n);
            s2 = r.nextInt(n);
            if (arr[s2] != arr[s1]) {
                tmp = arr[s2];
                arr[s2] = arr[s1];
                arr[s1] = tmp;
                cnt++;
            }
        }
    }
}
```

- 결론적으로 오름차순 정렬을 해줄 것이기 때문에, 오름차순 정렬을 이미 해준다.
- 그 후, 일부분을 섞어주도록 하는데 n의 10퍼센트 정도만 섞어주도록 한다.
- 10퍼센트 정도(p)가 2보다 적을 경우, 2로 설정하여 주었다.
- p / 2의 횟수만큼 Swap해주어 배열의 값을 랜덤으로 섞어주도록 한다.



#### 3. 내림차순 정렬된 배열

```java
class SortedData extends CreateArray implements InputData { //(3) 내림차순 정렬된 입력 배열

    @Override
    public int[] makeData(int n) {

        int[] array = create(n);
        int[] arr = new int[n];

        Arrays.sort(array);             // 오름차순 정렬 후
        for (int i = 0; i < n; i++) {   // 거꾸로 바꿔주어 내림차순으로
            arr[i] = array[n - i - 1];
        }
        return arr;
    }
}
```

- 오름차순으로 정렬되도록 설정해주었으므로, 입력 배열을 내림차순 정렬해주었다.
- 오름차순으로 정렬해준 후, 거꾸로 바꿔주어서 내림차순으로 정렬되도록 한다.



#### main

```java
public class Sort {

    public static void main(String[] args) {

        System.out.println("정렬하고자 하는 데이터 길이(n)을 입력해주세요.");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();                 //정렬하고자 하는 데이터의 길이를 입력받는다.

        System.out.println("(1) 랜덤한 입력 배열");
        RandomData randomData = new RandomData();
        int[] randomArray = randomData.makeData(n);
        printArray(randomArray);
        Data_Sort(randomArray);

        System.out.println("(2) 어느정도 정렬된 입력 배열");
        AlmostSortedData almostSortedData = new AlmostSortedData();
        int[] partSortedArray = almostSortedData.makeData(n);
        printArray(partSortedArray);
        Data_Sort(partSortedArray);

        System.out.println("(3) 내림차순 정렬된 입력 배열");
        SortedData sortedData = new SortedData();
        int[] sortedArray = sortedData.makeData(n);
        printArray(sortedArray);
        Data_Sort(sortedArray);
    }

    private static void Data_Sort(int[] inputArray) {

        System.out.println("[버블 정렬]");
        BubbleSort bubbleSort = new BubbleSort();
        printArray(bubbleSort.sortArray(inputArray));
        System.out.println("[선택 정렬]");
        SelectionSort selectionSort = new SelectionSort();
        printArray(selectionSort.sortArray(inputArray));
        System.out.println("[삽입 정렬]");
        InsertionSort insertionSort = new InsertionSort();
        printArray(insertionSort.sortArray(inputArray));
        System.out.println("[쉘 정렬]");
        ShellSort shellSort = new ShellSort();
        printArray(shellSort.sortArray(inputArray));
        System.out.println();
    }

    private static void printArray(int[] A) {

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
}
```



## 실행 결과

![image-20210505170057963](C:\Users\82106\AppData\Roaming\Typora\typora-user-images\image-20210505170057963.png)



## 성능 평가

#### 정렬별 데이터 비교

![image-20210505190339185](C:\Users\82106\AppData\Roaming\Typora\typora-user-images\image-20210505190339185.png)



#### 데이터별 정렬 비교

![image-20210505190412903](C:\Users\82106\AppData\Roaming\Typora\typora-user-images\image-20210505190412903.png)

1. 버블 정렬

   - 시간 복잡도 : O(n^2)

   - 입력 배열이 랜덤, 어느정도 정렬된, 역방향 배열일 경우, O(n^2)

   - 입력 배열이 이미 모두 정렬이 되어있는 경우(best), O(n)

2. 선택 정렬

   - 시간 복잡도 : O(n^2)

   - 입력 배열이 랜덤, 어느정도 정렬된, 역방향 배열일 경우, O(n^2)

   - 입력에 민감하지 않은 알고리즘. 항상 일정한 시간복잡도를 가진다.

3. 삽입 정렬

   - 시간 복잡도 : O(n^2)

   - 다른 알고리즘들보다 시간이 더 적게 걸린다.

   - 입력 배열이 랜덤, 어느정도 정렬된, 역방향 배열일 경우, O(n^2)

   - 입력 배열이 이미 모두 정렬이 되어있는 경우(best), O(n)

4. 쉘 정렬

   - 시간 복잡도 : O(n^1.5)
   - 입력 배열이 랜덤일 경우(worst), O(n^2)
   - 입력 배열이 이미 모두 정렬이 되어있는 경우(best), O(n)

   

- 삽입 정렬이 다른 알고리즘보다 실행 시간이 더 적게 걸린다.
- 버블 정렬은 다른 알고리즘보다 실행 시간이 더 많이 걸린다.


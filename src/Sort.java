import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

interface InputData {

    int[] makeData(int n);   // 정렬해줄 데이터를 설정하는 메서드
}

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

class RandomData extends CreateArray implements InputData {  // (1) 랜덤한 입력 배열 생성

    @Override
    public int[] makeData(int n) {

        int[] arr = create(n);

        return arr;
    }
}

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

interface SortMethod {         // 정렬 방법을 설정하는 메서드
    int[] sortArray(int[] A);
}

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

class InsertionSort implements SortMethod {   // 3. 삽입 정렬

    @Override
    public int[] sortArray(int[] A) {

        int n = A.length;

        for (int i = 0; i < n; i++) {
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

class ShellSort implements SortMethod {   // 4. 쉘 정렬렬

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

public class Sort {

    public static void main(String[] args) {

        System.out.println("정렬하고자 하는 데이터 길이(n)을 입력해주세요.");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();                 //정렬하고자 하는 데이터의 길이를 입력받는다.

        System.out.println("(1) 랜덤한 입력 배열");
        RandomData randomData = new RandomData();
        int[] randomArray = randomData.makeData(n);
        printArray(randomArray);
        Data_Sort(n, randomArray);

        System.out.println("(2) 어느정도 정렬된 입력 배열");
        AlmostSortedData almostSortedData = new AlmostSortedData();
        int[] partSortedArray = almostSortedData.makeData(n);
        printArray(partSortedArray);
        Data_Sort(n, partSortedArray);

        System.out.println("(3) 내림차순 정렬된 입력 배열");
        SortedData sortedData = new SortedData();
        int[] sortedArray = sortedData.makeData(n);
        printArray(sortedArray);
        Data_Sort(n, sortedArray);
    }

    private static void Data_Sort(int n, int[] inputArray) {

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

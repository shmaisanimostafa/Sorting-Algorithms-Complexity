import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("\n \n"); //Empty Lines

////////////////////////////////////////////////////////////////////////////////////////////
//Introduction
////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Welcome to the sorting algorithms time comlexity reseatch!\n");

////////////////////////////////////////////////////////////////////////////////////////////
//Try the algorithms on an array to make sure all are sorting
////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("First we are going to test the algorithms by giving them 10 elements array input. \n");
        System.out.println("The UnSorted 10 elemtents array is: ");
        int[] arr = {6, 5, 2, 9, 7, 8, 3, 10, 4, 1};
        printArray(arr);
        System.out.println(); //Empty Line

        //Second Array is used to copy the unsorted array, after sorting it in each algorithm, instead of creating same array for every algorithm.
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        
        //Apply the array on all different algorithms
        insertion(arr, arr2);
        merge(arr, arr2);
        heap(arr, arr2);
        quick(arr, arr2);
        bubble(arr, arr2);       
        selection(arr, arr2);
        count(arr, arr2);


////////////////////////////////////////////////////////////////////////////////////////////
//Try a random generated array on different algorithms
////////////////////////////////////////////////////////////////////////////////////////////
        algoWork();
        System.out.println("\n \n \n"); //Empty Lines
    }

////////////////////////////////////////////////////////////////////////////////////////////
//Algorithms Code
////////////////////////////////////////////////////////////////////////////////////////////

//////////////////Insertion Sort//////////////////
    public static void Insertionsort(int arr[])
{
    int n = arr.length;

    for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
//////////////////Merge Sort//////////////////////
    static void merge(int arr[], int l, int m, int r)
{
    int n1 = m - l + 1;
    int n2 = r - m;

    int L[] = new int[n1];
    int R[] = new int[n2];

    for (int i = 0; i < n1; ++i)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; ++j)
        R[j] = arr[m + 1 + j];

    int i = 0, j = 0;

    int k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}
    static void mergSort(int arr[], int l, int r)
{
    if (l < r) {
        int m = l + (r - l) / 2;
        mergSort(arr, l, m);
        mergSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}
///////////////////Heap Sort//////////////////////
    public static void heapSort(int arr[])
{
    int N = arr.length;

    for (int i = N / 2 - 1; i >= 0; i--)
        heapify(arr, N, i);

    for (int i = N - 1; i > 0; i--) {
        // Move current root to end
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        heapify(arr, i, 0);
    }
}
    static void heapify(int arr[], int N, int i)
{
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (l < N && arr[l] > arr[largest])
        largest = l;

    if (r < N && arr[r] > arr[largest])
        largest = r;

    if (largest != i) {
        int swap = arr[i];
        arr[i] = arr[largest];
        arr[largest] = swap;

        heapify(arr, N, largest);
    }
}
///////////////////Quick Sort/////////////////////
    static void swap(int[] arr, int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
    static int quickSortPartition(int[] arr, int low, int high)
{

    int pivot = arr[high];

    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {

        if (arr[j] < pivot) {

            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return (i + 1);
}
    static void quickSort(int[] arr, int low, int high)
{
    if (low < high) {

        int pi = quickSortPartition(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}
////////////////////Bubble Sort///////////////////
    public static void bubbleSort(int arr[])
{
int n = arr.length;
for (int i = 0; i < n - 1; i++)
    for (int j = 0; j < n - i - 1; j++)
        if (arr[j] > arr[j + 1]) {
            // swap arr[j+1] and arr[j]
            int temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
}
///////////////////Selection Sort/////////////////
    static void selectionSort(int arr[])
{
int n = arr.length;

for (int i = 0; i < n-1; i++)
{
    int min_idx = i;
    for (int j = i+1; j < n; j++)
        if (arr[j] < arr[min_idx])
            min_idx = j;

    int temp = arr[min_idx];
    arr[min_idx] = arr[i];
    arr[i] = temp;
}
}
///////////////////Counting Sort//////////////////
    static void countSort(int[] arr)
{
int max = Arrays.stream(arr).max().getAsInt();
int min = Arrays.stream(arr).min().getAsInt();
int range = max - min + 1;
int count[] = new int[range];
int output[] = new int[arr.length];
for (int i = 0; i < arr.length; i++) {
    count[arr[i] - min]++;
}

for (int i = 1; i < count.length; i++) {
    count[i] += count[i - 1];
}

for (int i = arr.length - 1; i >= 0; i--) {
    output[count[arr[i] - min] - 1] = arr[i];
    count[arr[i] - min]--;
}

for (int i = 0; i < arr.length; i++) {
    arr[i] = output[i];
}
}

////////////////////////////////////////////////////////////////////////////////////////////
//Other methods
////////////////////////////////////////////////////////////////////////////////////////////
    public static void printArray(int arr[])
{
    int n = arr.length;
    for (int i = 0; i < n; ++i)
        System.out.print(arr[i] + " ");
    System.out.println();
}
    public static int[] arrayGenerator() {
    Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter the number of random elements (make sure positive number) :  ");
        int n = scanner.nextInt();
        int[] randomArray = new int[n];
        for (int i = 0; i < n; i++) {
            randomArray[i] = random.nextInt(98) + 1;
        }
        System.out.println("\nThe numbers are between 1 and 99 \nThe random generated array is: ");
        printArray(randomArray);
        return randomArray;
}
    public static void algoWork() {
    System.out.println("Second we should generate a random array then we apply it on each algorithm to compare their time duration.");
        int[] randomArray = arrayGenerator();
        int[] randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
        boolean tryAlgo = true;


        Scanner scanner = new Scanner(System.in);
        while (tryAlgo) {
            System.out.println("\nSelect on which algorithm you want to apply the array, choose by number (default is all):");
            System.out.println("Note to choose the default choose any other number");
            System.out.println("1)Insertion\n2)Merge\n3)Quick\n4)Counting\n5)Heap\n6)Selection\n7)Bubble\n");
            int nub = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline character (Scanner Problem)
            switch (nub) {
                case 1 :
                    {
                    insertion(randomArray, randomArray2);
                break;    
                }
                    
                case 2 :
                {
                    merge(randomArray, randomArray2);
                    break;
                }   
                case 3 :
                {
                    quick(randomArray, randomArray2);
                    break;
                } 
                case 4 :
                {
                    count(randomArray, randomArray2);
                    break;
                } 
                case 5 :
                {
                    heap(randomArray, randomArray2);
                    break;
                } 
                case 6 :
                {
                    selection(randomArray, randomArray2);
                    break;
                } 
                case 7 :
                {
                   bubble(randomArray, randomArray2);
                    break;
                } 
                default:
                {
                    insertion(randomArray, randomArray2);
                    merge(randomArray, randomArray2);
                    quick(randomArray, randomArray2);
                    count(randomArray, randomArray2);
                    heap(randomArray, randomArray2);
                    selection(randomArray, randomArray2);
                    bubble(randomArray, randomArray2);
                    break;
                }
                    
            }

            System.out.println("Do you want to try on another algorithm? (Y/N)");
            String a = scanner.nextLine();
            if (!a.equalsIgnoreCase("y")) {
              tryAlgo = false;  
            }

        }
        System.out.println("\nHave a Good Day :)");
}

////////////////////////////////////////////////////////////////////////////////////////////
//Algorithms Code with Shown Input and Output and Duration
////////////////////////////////////////////////////////////////////////////////////////////
    public static void insertion(int[] randomArray,int[] randomArray2) {
System.out.println("--------------------------------------------------------------------------");

    System.out.println("\nInsetion Sort:  ");
                System.out.print("\nInput:  ");
                printArray(randomArray);

                long timerStart = System.nanoTime();
                Insertionsort(randomArray2); //Applying Insetion Sort
                long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
                
                System.out.print("\nOutput: ");
                printArray(randomArray2);
                randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
                System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
                System.out.println();//Empty Line
}
    public static void merge(int[] randomArray,int[] randomArray2) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("\nMerge Sort:  ");
    System.out.print("\nInput:  ");
    printArray(randomArray);
    long timerStart = System.nanoTime();
    mergSort(randomArray2, 0, randomArray2.length-1); //Applying Merge Sort
    long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
    System.out.print("\nOutput: ");
    printArray(randomArray2);
    randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
    System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
    System.out.println();//Empty Line
}
    public static void bubble(int[] randomArray,int[] randomArray2) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("\nBubble Sort:  ");
    System.out.print("\nInput:  ");
    printArray(randomArray);
    long timerStart = System.nanoTime();
    bubbleSort(randomArray2); //Applying Bubble Sort
    long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
    System.out.print("\nOutput: ");
    printArray(randomArray2);
    randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
    System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
    System.out.println();//Empty Line
}
    public static void quick(int[] randomArray,int[] randomArray2) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("\nQuick Sort:  ");
    System.out.print("\nInput:  ");
    printArray(randomArray);
    long timerStart = System.nanoTime();
    quickSort(randomArray2, 0, randomArray2.length -1); //Applying Quick Sort
    long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
    System.out.print("\nOutput: ");
    printArray(randomArray2);
    randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
    System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
    System.out.println();//Empty Line
}
    public static void selection(int[] randomArray,int[] randomArray2) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("\nSelection Sort:  ");
                System.out.print("\nInput:  ");
                printArray(randomArray);
                long timerStart = System.nanoTime();
                selectionSort(randomArray2); //Applying Selection Sort
                long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
    System.out.print("\nOutput: ");
    printArray(randomArray2);
    randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
    System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
    System.out.println();//Empty Line
}
    public static void heap(int[] randomArray,int[] randomArray2) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("\nHeap Sort:  ");
                System.out.print("\nInput:  ");
                printArray(randomArray);
                long timerStart = System.nanoTime();
                heapSort(randomArray2); //Applying Heap Sort
                long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
    System.out.print("\nOutput: ");
    printArray(randomArray2);
    randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
    System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
    System.out.println();//Empty Line
}
    public static void count(int[] randomArray,int[] randomArray2) {
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("\nCounting Sort:  ");
    System.out.print("\nInput:  ");
    printArray(randomArray);
    long timerStart = System.nanoTime();
    countSort(randomArray2); //Applying Counting Sort
    long timerEnd = System.nanoTime();
                long timer = (timerEnd - timerStart);
    System.out.print("\nOutput: ");
    printArray(randomArray2);
    randomArray2 = Arrays.copyOf(randomArray, randomArray.length);
    System.out.println("\nDuration Time: " + timer + "ns ~= " + timer /1000000 + "ms ~= " + timer /1000000000 + "s");
    System.out.println();//Empty Line
}
}

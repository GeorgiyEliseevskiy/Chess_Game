package com.company;

public class MyAlgoritme {

    public static int minElement(int[]arr) {
        int min = 0;

        for (int i =1; i< arr.length; i++) {
            if(arr[i] < arr[min]) {
                min = i;
            }
        }
        return arr[min];

    }

    public static boolean isPalindroom(String word) {
        int left = 0;
        int right = word.length() - 1;
        word = word.toLowerCase();
        while (left<right) {
            if(word.charAt(left) == word.charAt(right)) {
                left++;
                right--;
            }
            else {
                return false;
            }
        }
        return true;
    }


    public static void insertionSort(int[]arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > current) {
                arr[j] = arr[j - 1];
                j--;

            }
            arr[j] = current;
        }
    }

    public static void selectionSort(int[]arr) {
        for(int i = 0; i < arr.length; i++) {
            int min_index = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[min_index] > arr[j]) {
                    min_index = j;
                }
            }
            if (min_index != i) {
                int temp = arr[min_index];
                arr[min_index] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void bubbleSort(int[]arr) {
        int length = arr.length;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < length; i++) {
                if (arr[i - 1] > arr[i]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    isSorted = false;
                }
            }
        }
    }

}

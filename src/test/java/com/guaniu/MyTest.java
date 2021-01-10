package com.guaniu;

import java.util.Arrays;

public class MyTest {
    public static void main(String[] args){
//        int[] arr = {1,3,213,13,43123,2,123,32112,4};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.print(arr);
        MyTest test = new MyTest();
        test.multiply("2", "3");
    }

    static void quickSort(int[] arr, int low, int high){
        if (low >= high){
            return;
        }
        int key = arr[low];
        int i = low, j = high;
        while (i < j){
            while (arr[j] > key){
                j--;
            }
            while (i < j && arr[i] <= key){
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[low] = arr[i];
        arr[i] = key;
        quickSort(arr,i + 1, high);
        quickSort(arr, low, i - 1);
    }

    public String multiply(String num1, String num2) {
        int len1 = num1.length(); // 第 1 个乘数的长度
        int len2 = num2.length(); // 第 2 个乘数的长度
        char[][] chars = new char[len1][len2 + 1]; // 一个个位数 * num2 的出的数的长度不会超过 len2 + 1
        for (int i = len1 - 1; i >= 0; i--){
            int jinwei = 0;
            int value; // num1 上个位数和 num2 的乘积 与 进位的和
            for (int j = len2 - 1; j >= 0; j--){
                value = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + jinwei;
                chars[len1 - 1 - i][j + 1] = (char) (value % 10 + '0');
                jinwei = value / 10;
            }
            chars[len1 - 1 - i][0] = (char) (jinwei + '0'); // 第一位
        }

        char[] res = new char[len1 +len2]; // 乘积长度不会超过 len1 + len2
        Arrays.fill(res, '0');
        for (int i = 0; i < len1; i++){
            int start = len1 + len2 - 1 - i;
            int jinwei = 0;
            int value;
            for (int j = len2, pos = start; j >=0; j--, pos--){
                value = res[pos] - '0' + chars[i][j] - '0' + jinwei;
                res[pos] = (char) (value % 10 + '0');
                jinwei = value / 10;
            }
            res[start - len2] = (char) (res[start -len2] + jinwei);
            start--;
        }
        int i = 0;
        while (res[i] == '0'){
            i++;
        }
        return new String(res, i, len1 + len2 - i);
    }
}

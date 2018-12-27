package ru.sidorenko.lesson2;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        final int[] myArr = new int[100];
        final Random random = new Random();
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(myArr));
        main.removeElementArray(myArr,9);
        main.addElementArray(myArr,10,100);

        int foundElement2 = main.fastFindElement2(myArr, 5);
        System.out.println(foundElement2);

        System.out.println(Arrays.toString(sortBubble(myArr)));
        System.out.println(Arrays.toString(selectSort(myArr)));
        System.out.println(Arrays.toString(insertSort(myArr)));
    }

    public void removeElementArray(final int[] inArray, final int element) {
        final int[] outArray = new int[inArray.length - 1];
        int indexDelElement = -1;
        for (int j = 0; j < inArray.length;j++ ) {
            if (inArray[j] == element) {
                indexDelElement = j;
                break;
            }
        }
        if(indexDelElement == -1){
            System.out.println("Element not found");
            return;
        }
        for (int i = 0; i < outArray.length; i++) {
            if(i<indexDelElement){
                outArray[i] = inArray[i];
            }else {
                outArray[i] = inArray[i + 1];
            }
        }
        System.out.println(Arrays.toString(outArray));
    }

    public void addElementArray(int[] input, int index, int element) {
        int[] output = new int[input.length + 1];
        for (int i = 0; i < output.length; i++) {
            if (i > index) {
                output[i] = input[i - 1];
            } else if (i == index) {
                output[i] = element;
            } else {
                output[i] = input[i];
            }
        }
        System.out.println(Arrays.toString(output));
    }

    public int fastFindElement1(int[] input, int element) {
        int now = input.length/2;
        if (input[now] > element) {
            return fastFindElement1(Arrays.copyOf(input,now),element);
        } else if (input[now] == element) {
            return now;
        } else{
            return fastFindElement1(Arrays.copyOfRange(input, now, input.length), element);
        }
    }

    public int fastFindElement2(int[] input, int element){
        int low = 0;
        int height = input.length -1;
        int mid;
        while (low < height) {
            mid = (low + height)/2;
            if (element == input[mid]) {
                return mid;
            } else {
                if (element < input[mid]) {
                    height = mid;
                }else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int[] sortBubble(int[] array) {
        long t = System.currentTimeMillis();
        int out, in;
        for (out = array.length - 1; out >= 1; out--) {
            for (in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    change(array,in, in + 1);
                }
            }

        }
        t = System.currentTimeMillis() - t;
        System.out.println("time = " + t);
        return array;
    }

    private static void change(int[] array,int in, int i) {
        int tmp = array[in];
        array[in] = array[i];
        array[i] = tmp;
    }

    private static int[] selectSort(int[] array) {
        int out, in, mark;
        long t = System.currentTimeMillis();
        for (out = 0; out < array.length; out++) {
            mark = out;
            for (in = out + 1; in < array.length; in++) {
                if (array[in] < array[mark]) {
                    mark = in;
                }
            }
            change(array,out,mark);
        }
        t = System.currentTimeMillis() - t;
        System.out.println("time = " + t);
        return array;
    }

    private static int[] insertSort(int[] array) {
        int in, out;
        for (out = 1; out < array.length; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
        return array;
    }

}

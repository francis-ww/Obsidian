import java.util.Scanner;

public class Main {

    // (a) 计算阶乘的函数
    public static void calFactorial() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个大于0的正整数n:");
        int n = scanner.nextInt();

        // 用一个变量 result 初始为 1，然后用循环从 1 乘到 n
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }

        System.out.println(n + "! = " + result);
    }

    // (b) 计算常数 e 的函数
    public static void calE() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个e的精度accuracy (0.1> accuracy>0.0001)");
        double accuracy = scanner.nextDouble();

        double e = 1.0;      // 初始值，对应公式中最前面的那个 1
        int n = 1;           // 阶乘的基数 n
        long fact = 1;       // 记录 n! 的值
        double term = 1.0;   // 记录每一项 (1/n!) 的值

        // 使用一个死循环，直到满足特定条件时主动退出 (break)
        while (true) {
            term = 1.0 / fact;  // 计算当前这一项的值
            e = e + term;       // 把当前项累加到总和 e 里面

            // 检查：如果当前加上的这一项已经小于你输入的精度，说明计算够精准了，停止循环
            if (term < accuracy) {
                break;
            }

            // 如果还没达到精度，就准备计算下一项
            n++;
            fact = fact * n; // 计算下一个阶乘，比如上一次是 2!，这次就是 2! * 3 = 3!
        }

        System.out.println("e的值为：" + e);
    }

    // (c) 冒泡排序函数
    public static void bubbleSort() {
        int[] arr = {4, 9, 18, 27, 0, 96, 55, 46, 33};

        System.out.println("排序前的数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // 打印完换个行

        // 外层循环控制要走多少趟
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环控制每趟要比较多少次（已经排好的最大的数不需要再比了，所以减去 i）
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];       // 找个临时变量 temp 帮帮忙
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("排序后的数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("姓名：王海天   学号：2024140150106");

        // 依次调用三个写好的方法
        calFactorial();
        calE();
        bubbleSort();
    }
}
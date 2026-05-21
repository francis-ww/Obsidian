package k1;

public class LambdaDemo {
    public static void main(String[] args) {

        ILambdaTest test = Calculator::calculate;

        // 调用接口方法
        int num1 = 10;
        int num2 = 20;
        int result = test.doSomething2(num1, num2);

        // 输出结果
        System.out.println("计算结果是: " + result);
    }
}

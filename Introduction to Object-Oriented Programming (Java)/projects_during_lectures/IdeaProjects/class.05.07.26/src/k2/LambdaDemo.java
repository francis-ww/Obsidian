package k2;

public class LambdaDemo {
    public static void main(String[] args) {
        ILambdaTest test = Calculator::calculate;
        int result = test.doSomething(10,20);
        System.out.println("计算结果是: " + result);
    }
}
